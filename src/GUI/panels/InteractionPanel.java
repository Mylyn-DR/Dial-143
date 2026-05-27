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
import Main.GameAPI.Segment;
import Storyline.SceneManager.SceneManagerDelegate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class InteractionPanel extends JPanel implements SceneManagerDelegate {
 
    private MainFrame             mainPanel;
    private final GameAPI         gameAPI;
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
 
    public InteractionPanel(MainFrame mainPanel, GameAPI gameAPI, ItemUse itemUse, SettingsPanel sharedSettings, InventoryPanel inventory) {
        this.mainPanel = mainPanel;
        this.gameAPI = gameAPI;
        this.settings  = sharedSettings;
        this.inventory = inventory;
        this.itemUse = itemUse; 
        sceneManager = new SceneManager(this, gameAPI.getActiveRoute(), gameAPI.getLpStorage());
        waitForPreload();
        initComponents();
        initializeLayers();
    }
 
    public ItemUse getItemUse() { 
        return itemUse; 
    }
    
    public void saveScene(){
        System.out.println("Saving Scene from Interaction Panel");
        gameAPI.setChoiceID(sceneManager.getChoiceID());
        gameAPI.setChosenChoiceID(sceneManager.getChosenChoiceID());
        gameAPI.setCurrentSegment(sceneManager.getCurrentSegment());
        gameAPI.setSubSceneIndex(sceneManager.getSubSceneIndex());
        gameAPI.setDialogueScene(sceneManager.getCurrentSceneIndex()-1);
        
        System.out.println("Current Day Script: " + gameAPI.getCurrentDayScript());
        System.out.println("Current Day Segment: " + gameAPI.getCurrentSegment());
        System.out.println("Current Day Scene Index: " + gameAPI.getDialogueScene());
        System.out.println("Current Day SubScene Index: " + gameAPI.getSubSceneIndex());
        System.out.println("Current Day ChoiceID: " + gameAPI.getChoiceID());
        System.out.println("Current Day ChosenChoiceID: " + gameAPI.getChosenChoiceID());
    }
    
    public void setScene(){
        System.out.println("Setting Scene from Interaction Panel");
        sceneManager.clear();
        sceneManager.setDayScript(gameAPI.getCurrentDayScript());
        sceneManager.setCurrentSegment(gameAPI.getCurrentSegment());
        sceneManager.setCurrentSceneIndex(gameAPI.getDialogueScene());
        sceneManager.setSubSceneIndex(gameAPI.getSubSceneIndex());
        sceneManager.setChoiceID(gameAPI.getChoiceID());
        sceneManager.setChosenChoiceID(gameAPI.getChosenChoiceID());
        sceneManager.setActiveRoute(gameAPI.getActiveRoute());
        sceneManager.setLPStorage(gameAPI.getLpStorage());
        
        System.out.println("Current Day Script: " + gameAPI.getCurrentDayScript());
        System.out.println("Current Day Segment: " + gameAPI.getCurrentSegment());
        System.out.println("Current Day Scene Index: " + gameAPI.getDialogueScene());
        System.out.println("Current Day SubScene Index: " + gameAPI.getSubSceneIndex());
        System.out.println("Current Day ChoiceID: " + gameAPI.getChoiceID());
        System.out.println("Current Day ChosenChoiceID: " + gameAPI.getChosenChoiceID());
    }
    
    public void loadContent() {
        setScene();
        
        dialogueAdvanceLock = false;
        dialogueBox.onDialogueShown();
        uiComponents.setPpValue(gameAPI.getPP());
        uiComponents.setSalaryValue(gameAPI.getSalary());
        uiComponents.setLpValue(gameAPI.getLP());
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
                mainPanel.playEndingMusic();
            }
        }
        // ─────────────────────────────────────────────────────────────────────
 
        Segment seg = switch (gameAPI.getCurrentSegment()) {
            case MORNING -> {  yield Segment.MORNING; }
            case AFTERNOON -> { yield Segment.AFTERNOON; }
            case EVENING -> {  yield Segment.EVENING; }
            case ENDING -> { 
                sceneManager.setActiveRoute(gameAPI.getActiveRoute()); 
                yield Segment.ENDING; }
        };
        
        //sceneManager.start(seg, gameAPI.getDialogueScene());
        sceneManager.start();
    }
 
    // ── SceneManagerDelegate ──────────────────────────────────────────────────
 
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
        gameAPI.setPpGained(amount);
        uiComponents.setPpValue(getPlayerPP());
    }
 
    @Override
    public void addLP(int amount, String lpCharacter) {
        if (gameAPI.addLPForCharacter(lpCharacter, amount))
            uiComponents.setLpValue(gameAPI.getLP());
    }
 
    @Override
    public void onMorningComplete() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
        saveStats();
        mainPanel.onMorningComplete();
    }
 
    @Override
    public void onAfternoonComplete() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
        saveStats();
        mainPanel.onAfternoonComplete();
    }
    
    @Override
    public void onEveningComplete() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
        saveStats();
        mainPanel.onEveningComplete();
    }
 
    @Override
    public void onSceneEnd() {
        dialogueAdvanceLock = false;
        dialogueBox.onSceneEnd();
    }
    
    @Override
    public void onEndingComplete() {
        mainPanel.onGameComplete();
    }
    
    // ── Internal helpers ──────────────────────────────────────────────────────
 
    private void handleRouteChoice(String characterId) {
        if ("none".equals(characterId)) {
            System.out.println("  Player chose to walk alone — BAD ENDING");

            // Play the walk alone ending scene
            ChoiceEntry walkAloneEnding = new ChoiceEntry("No one", 0, 0,
                SceneEntry.dialogue("{name}", "No. I shouldn't be crushing on anyone.", "none", "StreetEvening.jpg"),
                SceneEntry.dialogue("{name}", "Focus. That's all that matters.", "none", "StreetEvening.jpg"),
                SceneEntry.narrator("You walk home alone.", "StreetEvening.jpg"),
                SceneEntry.narrator("The night feels colder than usual.", "StreetEvening.jpg"),
                SceneEntry.narrator("Some calls... are meant to be unanswered.", "blackBG.jpg"),
                SceneEntry.narrator("★ WALK ALONE ENDING ★", "blackBG.jpg")
            );

            sceneManager.onChoicePicked(walkAloneEnding);

            // After the ending, restart the game
            Timer restartTimer = new Timer(4000, e -> {
                mainPanel.onGameComplete();
            });
            restartTimer.setRepeats(false);
            restartTimer.start();
            return;
        }

        // Get the chosen route (AmayaRoute, CeleresRoute, etc.)
        RouteManager chosenRoute = routeForCharacter(characterId);
        gameAPI.setActiveRoute(characterId);
        gameAPI.getLpStorage().selectRoute(chosenRoute, characterId);
        DayInterface dayScript = chosenRoute.getDayScript(3);
        int accLP = gameAPI.getLpStorage().getLPForCharacter(characterId);
        // Update UI
        uiComponents.setLpValue(accLP);
        uiComponents.updateLoveMeterVisibility(gameAPI.getCurrentDay(), GameAPI.Segment.EVENING);

        // Load the Day 3 evening scenes for this route
        sceneManager.setDayScript(dayScript);
        sceneManager.setCurrentSegment(Segment.EVENING);
        sceneManager.setCurrentSceneIndex(0);
        sceneManager.start();
    }
 
    private RouteManager routeForCharacter(String name) {
        return switch (name) {
            case Character.AMAYA -> {
                yield new AmayaRoute();
            }
            case Character.CELERES -> {
                yield new CeleresRoute();
            }
            case Character.CLOMA -> {
                yield new ClomaRoute();
            }
            case Character.ROSARIO -> {
                yield new RosarioRoute();
            }
            default -> { yield null; }
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
        
        String resolved = text
            .replace("{name}",               name != null ? name : "")
            .replace("{pronoun_subject}",    subject)
            .replace("{pronoun_possessive}", possessive)
            .replace("{pronoun_objective}",  objective);
            
        if (resolved.contains("{name}") || resolved.contains("{pronoun_")) {
        }
        
        return resolved;
    }
 
    private void saveStats() {
//        gameAPI.setPP(uiComponents.getCurrentPpValue());
//        gameAPI.setSalary(uiComponents.getCurrentSalaryValue());
    }
 
    private void initializeLayers() {
        bg          = new BackgroundLayer();
        sprite      = new SpriteLayer();
        dialogueBox = new DialogueBoxLayer(mainPanel, gameAPI);

        dialogueBox.setOnAdvanceRequest(() -> {
            if (!choices.isVisible() && !showingIdentityCreation && !dialogueAdvanceLock) {
                sceneManager.advanceScene();
            } else if (dialogueAdvanceLock) {
            }
        });
 
        uiComponents = new TopBarComponents(mainPanel);
        uiComponents.setSettingsPanel(settings);
        uiComponents.setParentScreen("dialogue");
        uiComponents.setInventoryPanel(inventory);
        uiComponents.onSettingsClosed(() -> {
            requestFocusInWindow();
        });
 
        identityPopup = new IdentityCreationLayer(mainPanel, gameAPI);
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

    //public void loadContent() {
    //        setDayScript(gameAPI.getCurrentDayScript());
    //        
    //        dialogueAdvanceLock = false;
    //        dialogueBox.onDialogueShown();
    //        uiComponents.setPpValue(gameAPI.getPP());
    //        uiComponents.setSalaryValue(gameAPI.getSalary());
    //        
    //        
    //        RouteManager rm = gameAPI.getLpStorage();
    //        int currentLP = rm.hasActiveRoute() ? rm.getActiveLP() : 0;
    //        uiComponents.setLpValue(currentLP);
    //        uiComponents.updateDayLabel(gameAPI.getCurrentDay(), gameAPI.getCurrentSegment());
    // 
    //        // ── Audio ─────────────────────────────────────────────────────────────
    //        AudioPlayer audio = AudioPlayer.getInstance();
    //        switch (gameAPI.getCurrentSegment()) {
    //            case MORNING -> {
    //                if (!audio.isPlaying(AudioPlayer.Track.GAMEPLAY)) {
    //                    audio.crossfadeTo(AudioPlayer.Track.GAMEPLAY, 800);
    //                }
    //            }
    //            case EVENING -> {
    //                if (!audio.isPlaying(AudioPlayer.Track.GAMEPLAY)) {
    //                    audio.crossfadeTo(AudioPlayer.Track.GAMEPLAY, 800);
    //                }
    //            }
    //            case ENDING -> {
    //                mainPanel.playEndingMusic();
    //            }
    //        }
    //        // ─────────────────────────────────────────────────────────────────────
    // 
    //        SceneManager.Segment seg = switch (gameAPI.getCurrentSegment()) {
    //            case MORNING -> {  yield SceneManager.Segment.MORNING; }
    //            case AFTERNOON -> { yield SceneManager.Segment.AFTERNOON; }
    //            case EVENING -> {  yield SceneManager.Segment.EVENING; }
    //            case ENDING -> { 
    //                sceneManager.setActiveRoute(gameAPI.getActiveRoute()); 
    //                yield SceneManager.Segment.ENDING; }
    //        };
    //        
    //        sceneManager.start(seg, gameAPI.getDialogueScene());
    //    }