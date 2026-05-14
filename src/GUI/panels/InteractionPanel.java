package GUI.panels;
 
import GUI.panels.universalComponents.BackgroundLayer;
import GUI.panels.universalComponents.TopBarComponents;
import GUI.panels.dialogueComponents.*;
import GUI.panels.InventoryPanel;
import Storyline.*;
import Storyline.AmayaRoute.AmayaRoute;
import Storyline.CeleresRoute.CeleresRoute;
import Storyline.ClomaRoute.ClomaRoute; 
import Storyline.RosarioRoute.RosarioRoute;
import Entities.Character;
import Entities.*;
import Main.GameAPI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class InteractionPanel extends JPanel implements SceneManager.SceneManagerDelegate {
 
    private GameAPI               gameAPI;
    private MainFrame             mainFrame;  // Keep for screen navigation only
    private BackgroundLayer       bg;
    private SpriteLayer           sprite;
    private DialogueBoxLayer      dialogueBox;
    private TopBarComponents      uiComponents;
    private ChoiceButtonLayer     choices;
    private SettingsPanel         settings;
    private InventoryPanel        inventory;
    private IdentityCreationLayer identityPopup;
    private ItemUse               itemUse;
 
    private boolean showingIdentityCreation = false;
    private boolean showingRouteSelection   = false;
    private boolean dialogueAdvanceLock     = false;  
 
    private Thread       spriteThread;
    private Thread       bgThread;
    private SceneManager sceneManager;
 
    public InteractionPanel(GameAPI gameAPI, MainFrame mainFrame, SettingsPanel sharedSettings, InventoryPanel inventory) {
        this.gameAPI = gameAPI;
        this.mainFrame = mainFrame;
        this.settings  = sharedSettings;
        this.inventory = inventory;
        initComponents();
        setPreferredSize(new java.awt.Dimension(1280, 720));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new javax.swing.OverlayLayout(this));
        initializeLayers();
        sceneManager = new SceneManager(gameAPI, new Day1(), this, 
                                        gameAPI.getActiveRoute(), 
                                        gameAPI.getLpStorage());
    }
 
    public ItemUse getItemUse() { 
        return itemUse; 
    }
 
    // ── SceneManagerDelegate ──────────────────────────────────────────────────
 
    @Override
    public void waitForPreload() {
        try {
            if (spriteThread != null) spriteThread.join();
            if (bgThread     != null) bgThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
 
    @Override
    public void showBackground(String filename) {
        if (filename != null && !filename.isEmpty()) {
            bg.setBackgroundFromFile(filename);
        } else {
            bg.setBackgroundColor(new Color(20, 30, 40));
        }
    }
 
    @Override
    public void showSprite(String spriteSpec) {
        if (spriteSpec == null || spriteSpec.equals("none")) {
            sprite.hideAllSprites();
        } else if (spriteSpec.startsWith("single:")) {
            String spriteName = spriteSpec.substring(7);
            sprite.showSingleSprite(spriteName);
        } else if (spriteSpec.startsWith("double:")) {
            String rest = spriteSpec.substring(7);
            String[] p  = rest.split(":");
            if (p.length >= 2) {
                System.out.println("  Double sprite: " + p[0] + ", " + p[1]);
                sprite.showTwoSprites(p[0], p[1]);
            }
        } else {
            sprite.showSingleSprite(spriteSpec);
        }
        revalidate();
        repaint();
    }
 
    @Override
    public void showDialogue(String speaker, String text) {
        dialogueAdvanceLock = false;
        choices.hideChoices();
        dialogueBox.onDialogueShown();
        dialogueBox.setSpeaker(resolvePlaceholders(speaker));
        dialogueBox.setDialogue(resolvePlaceholders(text));
    }
 
    @Override
    public void showNarrator(String text) {
        dialogueAdvanceLock = false;
        choices.hideChoices();
        dialogueBox.onDialogueShown();
        dialogueBox.setSpeaker("Narrator");
        dialogueBox.setDialogue(resolvePlaceholders(text));
    }
 
    @Override
    public void showChoices(ChoiceEntry[] choiceEntries, Runnable onChosen) {
        for (int i = 0; i < choiceEntries.length; i++) {
            System.out.println("  Choice " + (i+1) + ": " + choiceEntries[i].label);
        }
        
        dialogueAdvanceLock = true;
        dialogueBox.onChoicesShown();
        choices.clearChoices();
        
        for (ChoiceEntry entry : choiceEntries) {
            choices.addChoice(entry.label, entry.label, true);
        }
        
        choices.setChoiceListener((choiceText, nextNode) -> {
            dialogueAdvanceLock = false;
            for (ChoiceEntry entry : choiceEntries) {
                if (entry.label.equals(choiceText)) {
                    choices.hideChoices();
                    sceneManager.onChoicePicked(entry);
                    break;
                }
            }
        });
        choices.showChoices();
    }
 
    @Override
    public void showRouteSelection() {
        dialogueAdvanceLock = true;
        dialogueBox.onChoicesShown();
        showingRouteSelection = true;
        RouteManager rm = gameAPI.getLpStorage();
 
        boolean amayaOk   = rm.isRouteUnlocked(Character.AMAYA);
        boolean celeresOk = rm.isRouteUnlocked(Character.CELERES);
        boolean clomaOk   = rm.isRouteUnlocked(Character.CLOMA);
        boolean rosarioOk = rm.isRouteUnlocked(Character.ROSARIO);
        boolean allLocked = rm.allRoutesLocked();
        
        choices.clearChoices();
        if (allLocked) {
            choices.addChoice("No one - Walk alone", "none", true);
        } else {
            choices.addChoice("Amaya",   Character.AMAYA,   amayaOk);
            choices.addChoice("Celeres", Character.CELERES, celeresOk);
            choices.addChoice("Cloma",   Character.CLOMA,   clomaOk);
            choices.addChoice("Rosario", Character.ROSARIO, rosarioOk);
        }
        
        choices.setChoiceListener((choiceText, characterId) -> {
            showingRouteSelection = false;
            dialogueAdvanceLock = false;
            choices.hideChoices();
            handleRouteChoice(characterId);
        });
        choices.showChoices();
    }
    
    @Override
    public int getPlayerPP() {
        return gameAPI.getPP();
    }
 
    @Override
    public void showIdentityCreation(Runnable onComplete) {
        dialogueAdvanceLock = true;
        dialogueBox.onChoicesShown();
        showingIdentityCreation = true;
        
        SwingUtilities.invokeLater(() -> {
            identityPopup.reset();
            identityPopup.setOnComplete(() -> {
                showingIdentityCreation = false;
                dialogueAdvanceLock = false;
                dialogueBox.onDialogueShown();
                onComplete.run();
            });
            identityPopup.showAsPopup();
        });
    }
 
    @Override
    public void addPP(int amount) {
        uiComponents.addPpPoints(amount);
        gameAPI.setPP(gameAPI.getPP() + amount);
    }
 
    @Override
    public void addLP(int amount, String lpCharacter) {
        System.out.println("DEBUG: addLP called with " + amount + " for " + lpCharacter);
        RouteManager lp = gameAPI.getLpStorage();

        if (lpCharacter != null) {
            int before = lp.getLPForCharacter(lpCharacter);
            lp.addCharacterLP(lpCharacter, amount);
            int after = lp.getLPForCharacter(lpCharacter);
            System.out.println("DEBUG: LP for " + lpCharacter + " changed from " + before + " to " + after);
            // Update UI if this is the active character
            if (lpCharacter.equals(gameAPI.getActiveCharacter())) {
                uiComponents.addLpPoints(amount);
            }
        } else if (gameAPI.hasActiveRoute()) {
            lp.addCharacterLP(gameAPI.getActiveCharacter(), amount);
            uiComponents.addLpPoints(amount);
        }
    }
 
    @Override
    public void onMorningComplete() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
        saveStats();
        mainFrame.onMorningComplete();  // Keep - screen navigation
    }
 
    @Override
    public void onAfternoonComplete() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
        saveStats();
        mainFrame.onAfternoonComplete();  // Keep - screen navigation
    }
    
    @Override
    public void onEveningComplete() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
        saveStats();
        mainFrame.onEveningComplete();  // Keep - screen navigation
    }
 
    @Override
    public void onSceneEnd() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
    }
 
    public void loadContent() {
        dialogueAdvanceLock = false;
        dialogueBox.onDialogueShown();
        uiComponents.setPpValue(gameAPI.getPP());
        uiComponents.setSalaryValue(gameAPI.getSalary());
        
        // Get LP from GameAPI using active character
        int currentLP = 0;
        if (gameAPI.hasActiveRoute()) {
            String activeChar = gameAPI.getActiveCharacter();
            currentLP = gameAPI.getLPForCharacter(activeChar);
            System.out.println("loadContent - activeChar: " + activeChar + ", LP: " + currentLP);
        } else {
            System.out.println("loadContent - No active route");
        }
        uiComponents.setLpValue(currentLP);
        uiComponents.updateDayLabel(gameAPI.getCurrentDay(), gameAPI.getCurrentSegment());
 
        // ── Audio ─────────────────────────────────────────────────────────────
        AudioPlayer audio = AudioPlayer.getInstance();
        switch (gameAPI.getCurrentSegment()) {
            case MORNING -> {
                if (!audio.isPlaying(AudioPlayer.Track.GAMEPLAY)) {
                    audio.crossfadeTo(AudioPlayer.Track.GAMEPLAY, 800);
                }
            }
            case EVENING -> {
                if (!audio.isPlaying(AudioPlayer.Track.GAMEPLAY)) {
                    audio.crossfadeTo(AudioPlayer.Track.GAMEPLAY, 800);
                }
            }
            case ENDING -> {
                AudioPlayer.Track track = switch (gameAPI.getEndingType()) {
                    case GOOD -> AudioPlayer.Track.GOOD_ENDING;
                    case BAD -> AudioPlayer.Track.BAD_ENDING;
                    case NEUTRAL -> AudioPlayer.Track.NEUTRAL_ENDING;
                };
                audio.crossfadeTo(track, 1200);
            }
            default -> {}
        }
        // ─────────────────────────────────────────────────────────────────────
 
        sceneManager.start(
            convertToSceneManagerSegment(gameAPI.getCurrentSegment()), 
            gameAPI.getDialogueScene()
        );
    }
    
    private SceneManager.Segment convertToSceneManagerSegment(GameAPI.Segment seg) {
        return switch (seg) {
            case MORNING -> SceneManager.Segment.MORNING;
            case AFTERNOON -> SceneManager.Segment.AFTERNOON;
            case EVENING -> SceneManager.Segment.EVENING;
            case ENDING -> {
                sceneManager.setActiveRoute(gameAPI.getActiveRoute());
                yield SceneManager.Segment.ENDING;
            }
        };
    }
    
    @Override
    public void onEndingComplete() {
        mainFrame.onGameComplete();  // Keep - screen navigation
    }
    
    public void setDayScript(DayInterface script) { 
        sceneManager.setDayScript(script); 
    }
    
    public void setRouteManager(RouteManager rm) {
        sceneManager.setRouteManager(rm); 
    }
 
    // ── Internal helpers ──────────────────────────────────────────────────────
 
    private void handleRouteChoice(String characterId) {
        if ("none".equals(characterId)) {
            System.out.println("  Player chose to walk alone — BAD ENDING");

            ChoiceEntry walkAloneEnding = new ChoiceEntry("No one", 0, 0,
                SceneEntry.dialogue("{name}", "No. I shouldn't be crushing on anyone.", "none", "StreetEvening.jpg"),
                SceneEntry.dialogue("{name}", "Focus. That's all that matters.", "none", "StreetEvening.jpg"),
                SceneEntry.narrator("You walk home alone.", "StreetEvening.jpg"),
                SceneEntry.narrator("The night feels colder than usual.", "StreetEvening.jpg"),
                SceneEntry.narrator("Some calls... are meant to be unanswered.", "blackBG.jpg"),
                SceneEntry.narrator("★ WALK ALONE ENDING ★", "blackBG.jpg")
            );

            sceneManager.onChoicePicked(walkAloneEnding);

            Timer restartTimer = new Timer(4000, e -> {
                mainFrame.onGameComplete();
            });
            restartTimer.setRepeats(false);
            restartTimer.start();
            return;
        }

        RouteManager chosenRoute = routeForCharacter(characterId);
        gameAPI.setActiveRoute(characterId);
        DayInterface dayScript = chosenRoute.getDayScript(3);
        int accLP = gameAPI.getLPForCharacter(characterId);
        
        uiComponents.setLpValue(accLP);
        uiComponents.updateLoveMeterVisibility(gameAPI.getCurrentDay(), GameAPI.Segment.EVENING);

        sceneManager.setDayScript(dayScript);
        sceneManager.start(SceneManager.Segment.EVENING, 0);
    }
 
    private RouteManager routeForCharacter(String name) {
        return switch (name) {
            case Character.AMAYA -> new AmayaRoute();
            case Character.CELERES -> new CeleresRoute();
            case Character.CLOMA -> new ClomaRoute();
            case Character.ROSARIO -> new RosarioRoute();
            default -> null;
        };
    }
 
    private String resolvePlaceholders(String text) {
        if (text == null) return "";
        String name    = gameAPI.getPlayerName();
        String pronoun = gameAPI.getPlayerPronoun();
        String subject = "they", possessive = "their", objective = "them";
        
        if (pronoun != null && !pronoun.isEmpty()) {
            String[] p = pronoun.split("/");
            subject    = p.length > 0 ? p[0] : "they";
            possessive = p.length > 1 ? p[1] : "their";
            objective  = p.length > 2 ? p[2] : "them";
        }
        
        return text
            .replace("{name}",               name != null ? name : "")
            .replace("{pronoun_subject}",    subject)
            .replace("{pronoun_possessive}", possessive)
            .replace("{pronoun_objective}",  objective);
    }
 
    private void saveStats() {
        gameAPI.setPP(uiComponents.getCurrentPpValue());
        gameAPI.setSalary(uiComponents.getCurrentSalaryValue());
    }
 
    private void initializeLayers() {
        bg          = new BackgroundLayer();
        sprite      = new SpriteLayer();
        dialogueBox = new DialogueBoxLayer(gameAPI);  // ← Pass GameAPI
 
        itemUse = new ItemUse(gameAPI, uiComponents, null);
        dialogueBox.setOnAdvanceRequest(() -> {
            if (!choices.isVisible() && !showingIdentityCreation && !dialogueAdvanceLock) {
                sceneManager.advanceScene();
            }
        });
 
        uiComponents = new TopBarComponents(gameAPI);  // ← Pass GameAPI
        uiComponents.setSettingsPanel(settings);
        uiComponents.setParentScreen("dialogue");
        uiComponents.setInventoryPanel(inventory);
        uiComponents.onSettingsClosed(() -> {
            requestFocusInWindow();
        });
 
        identityPopup = new IdentityCreationLayer(gameAPI);  // ← Pass GameAPI
        choices       = new ChoiceButtonLayer();
        
        add(choices);
        add(uiComponents);
        add(dialogueBox);
        add(sprite);
        add(bg);
    
        spriteThread = new Thread(() -> {
            System.out.println("  Loading sprites...");
            sprite.addSprite("Amaya_Smile",      "Amaya_Smile.png");
            sprite.addSprite("Amaya_Casual",     "Amaya_Casual.png");
            sprite.addSprite("Amaya_Sad",        "Amaya_Sad.png");
            sprite.addSprite("Amaya_Mad",        "Amaya_Mad.png");
            sprite.addSprite("Amaya_Blushing",   "Amaya_Blushing.png");
            sprite.addSprite("Rosario_Smile",    "Rosario_Smile.png");
            sprite.addSprite("Rosario_Casual",   "Rosario_Casual.png");
            sprite.addSprite("Rosario_Sad",      "Rosario_Sad.png");
            sprite.addSprite("Rosario_Mad",      "Rosario_Mad.png");
            sprite.addSprite("Rosario_Blushing", "Rosario_Blushing.png");
            sprite.addSprite("Celeres_Smile",    "Celeres_Smile.png");
            sprite.addSprite("Celeres_Casual",   "Celeres_Casual.png");
            sprite.addSprite("Celeres_Sad",      "Celeres_Sad.png");
            sprite.addSprite("Celeres_Mad",      "Celeres_Mad.png");
            sprite.addSprite("Celeres_Blushing", "Celeres_Blushing.png");
            sprite.addSprite("Cloma_Smile",      "Cloma_Smile.png");
            sprite.addSprite("Cloma_Casual",     "Cloma_Casual.png");
            sprite.addSprite("Cloma_Sad",        "Cloma_Sad.png");
            sprite.addSprite("Cloma_Mad",        "Cloma_Mad.png");
            sprite.addSprite("Cloma_Blushing",   "Cloma_Blushing.png");
            sprite.addSprite("Khai_EyesClosed",  "Khai_EyesClosed.png");
            sprite.addSprite("Khai_EyesOpen",  "Khai_EyesOpen.png");
            sprite.addSprite("CoworkerA",         "CoworkerA.png");
            sprite.addSprite("CoworkerB",         "CoworkerB.png");
            sprite.addSprite("CoworkerC",         "CoworkerC.png");
        }, "sprite-preload");
 
        bgThread = new Thread(() -> {
            bg.preload("MorningOffice.jpg",     "EveningOffice.jpg",    "BuildingEvening.jpg",
                        "BuildingMorning.jpg",   "ConvenienceStore.jpg", "EventStage.jpg",
                        "StreetMorning.jpg",     "StreetEvening.jpg",    "MorningElevator.jpg",
                        "BreakRoom.jpg",         "blackBG.jpg",
                        "AMAYA_GOOD.png",        "AMAYA_NEUTRAL.png",    "AMAYA_BAD.png",
                        "CELERES_GOOD.png",      "CELERES_NEUTRAL.png",  "CELERES_BAD.png",
                        "CLOMA_GOOD.png",        "CLOMA_NEUTRAL.png",    "CLOMA_BAD.png",
                        "ROSARIO_GOOD.png",      "ROSARIO_NEUTRAL.png",  "ROSARIO_BAD.png",
                        "Bedroom.jpg",           "EventStage.jpg",       "ITDepartment.jpg",
                        "IntRestaurant.jpg",     "ParkingNIght.jpg",     "ExtRestaurant.jpg",
                        "ParkingArea.jpg",       "StreetEvening_Rain.jpg", "INT_CAR.jpg",
                        "Alley.jpg",             "ParkMorning.jpg"
                        );
             System.out.println("  Background preload complete");
        }, "bg-preload");
 
        spriteThread.setDaemon(true);
        bgThread.setDaemon(true);
        spriteThread.start();
        bgThread.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1280, 720));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new javax.swing.OverlayLayout(this));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
            if (!choices.isVisible() && !showingIdentityCreation) {
                sceneManager.advanceScene();
            }
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
