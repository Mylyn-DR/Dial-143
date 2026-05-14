package GUI.panels;

import java.awt.*;
import java.awt.CardLayout;
import javax.swing.*;
import Entities.*;
import GUI.panels.universalComponents.TransitionLayer;
import GUI.panels.InventoryPanel;
import Main.GameAPI;
import Storyline.Day6;
import Storyline.DayInterface;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel     mainContainer;

    private TitleScreenPanel titlePanel;
    private InteractionPanel dialoguePanel;
    private SettingsPanel    settingsPanel;
    private ShiftPanel       shiftPanel;
    private ShopPanel        shopPanel;
    private DaySummaryPanel  daySummary;
    private SavePanel        savePanel;
    private TransitionLayer  transitionLayer;
    private InventoryPanel   inventory;
    private CreditsPanel     creditsPanel;
    
    private final GameAPI gameAPI;
    
    // UI temporary variables (NOT saved, NOT in GameState)
    private int ppAtShiftStart;
    private int lpAtShiftStart;
    private int salaryAtShiftStart;
    
    public MainFrame() {
        setTitle("Dial 143");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(1300, 731));
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/GUI/resources/icons/gameTitle.png"));
        
        gameAPI = new GameAPI(); 
        
        panelObjects();
        setupLayeredContent();
        
        pack();
        setLocationRelativeTo(null);
        cardLayout.show(mainContainer, "title");
    }
    
    // ==================== SCREEN ROUTING ====================
    
    public CreditsPanel getCreditsPanel() { return creditsPanel; }
    public SavePanel getSavePanel() { return savePanel; }
    
    public void resetStats() {
        gameAPI.resetGame();
        ppAtShiftStart = 0;
        lpAtShiftStart = 0;
        salaryAtShiftStart = 0;
    }
    
    public void showScreen(String screenName) {
        if (transitionLayer.isTransitioning()) return;
        
        transitionLayer.fadeOut(() -> {
            cardLayout.show(mainContainer, screenName);
            SwingUtilities.invokeLater(() -> {
                switch (screenName) {
                    case "title"    -> AudioPlayer.getInstance().play(AudioPlayer.Track.INTRO);
                    case "dialogue" -> dialoguePanel.loadContent();
                    case "shift"    -> shiftPanel.loadCall();
                    case "shop"     -> shopPanel.loadShop();
                    case "save"     -> {}
                    case "summary"  -> daySummary.loadSummary(
                        gameAPI.getPP() - ppAtShiftStart,
                        gameAPI.getLP() - lpAtShiftStart,
                        gameAPI.getSalary() - salaryAtShiftStart,
                        gameAPI.getCallsCompletedToday(),
                        () -> onEndDay(),
                        () -> showScreen("shop")
                    );
                }
                Timer timer = new Timer(50, e -> transitionLayer.fadeIn());
                timer.setRepeats(false);
                timer.start();
            });
        });
    }
    
    public void showSave(String returnScreen) {
        transitionLayer.fadeOut(() -> {
            cardLayout.show(mainContainer, "save");
            savePanel.loadSave(returnScreen, () -> showScreen(returnScreen));  // Pass callback
            transitionLayer.fadeIn();
        });
    }
    
    // ==================== GAME FLOW (delegates to GameAPI) ====================
    private Day6 day6 = new Day6();
    
    public void onMorningComplete() {
        ppAtShiftStart = gameAPI.getPP();
        lpAtShiftStart = gameAPI.getLP();
        salaryAtShiftStart = gameAPI.getSalary();
        gameAPI.resetCallsCompleted();
        
        // Check if this is Day 7 — play ending instead of CallShift
        if (gameAPI.getCurrentDay() >= gameAPI.getTotalDays()) {
            gameAPI.setCurrentSegment(GameAPI.Segment.ENDING);
            gameAPI.setDialogueScene(0);
            gameAPI.setDialogueDone(false);
            showScreen("dialogue");
            return;
        }
        
        // SPECIAL CASE: Day 6 afternoon (company event)
//        if (gameAPI.getCurrentDay() == 6 && gameAPI.hasActiveRoute()) {
//            DayInterface sharedAfternoon = day6;
//            gameAPI.setCurrentSegment(GameAPI.Segment.AFTERNOON);
//            gameAPI.setDialogueScene(0);
//            gameAPI.setDialogueDone(false);
//            dialoguePanel.setDayScript(sharedAfternoon);
//            showScreen("dialogue");
//            return;
//        }
        
        // Check for afternoon scenes
        DayInterface todayScript = gameAPI.scriptForDay(gameAPI.getCurrentDay());
        if (todayScript.getAfternoonScenes().length > 0) {
            gameAPI.setCurrentSegment(GameAPI.Segment.AFTERNOON);
            gameAPI.setDialogueScene(0);
            gameAPI.setDialogueDone(false);
            dialoguePanel.setDayScript(todayScript);
            showScreen("");
            return;
        }
        
        // Normal CallShift
        AudioPlayer.getInstance().crossfadeTo(AudioPlayer.Track.GAMEPLAY, 800);
        showScreen("shift");
    }
    
    public void onAfternoonComplete() {
        if (gameAPI.getCurrentDay() == 6 && gameAPI.hasActiveRoute()) {
            DayInterface routeEvening = gameAPI.getActiveRoute().getDayScript(6);
        }
        
        gameAPI.setCurrentSegment(GameAPI.Segment.EVENING);
        gameAPI.setDialogueScene(0);
        gameAPI.setDialogueDone(false);
        showScreen("dialogue");
    }
    
    public void onCallComplete() {
        gameAPI.setCurrentSegment(GameAPI.Segment.EVENING);
        gameAPI.setDialogueScene(0);
        gameAPI.setDialogueDone(false);
        System.out.println("ON CALL COMPLETE");
        showScreen("dialogue");
    }
    
    public void onEveningComplete() {
        System.out.println("ON EVENING COMPLETE");
        showScreen("summary");
    }
    
    public void onShopComplete() {
        showScreen("summary");
    }
    
    public void onEndDay() {
        System.out.println("===== onEndDay =====");
        System.out.println("currentDay before: " + gameAPI.getCurrentDay());
        
        gameAPI.setDialogueScene(0);
        gameAPI.setDialogueDone(false);
        gameAPI.incrementDay();
        gameAPI.resetCallsCompleted();
        ppAtShiftStart = gameAPI.getPP();
        lpAtShiftStart = gameAPI.getLP();
        salaryAtShiftStart = gameAPI.getSalary();
        
        if (gameAPI.getCurrentDay() == gameAPI.getTotalDays()) {
            gameAPI.setCurrentSegment(GameAPI.Segment.ENDING);
        } else if (gameAPI.getCurrentDay() > gameAPI.getTotalDays()) {
            onGameComplete();
            return;
        } else {
            gameAPI.setCurrentSegment(GameAPI.Segment.MORNING);
            System.out.println("currentDay after increment: " + gameAPI.getCurrentDay());
            DayInterface nextDayScript = gameAPI.scriptForDay(gameAPI.getCurrentDay());
            System.out.println("Next day script: " + nextDayScript.getClass().getSimpleName());
            dialoguePanel.setDayScript(nextDayScript);
        }
        
        AudioPlayer.getInstance().crossfadeTo(AudioPlayer.Track.INTRO, 800);
        showScreen("dialogue");
    }
    
    public void onGameComplete() {
        resetStats();
        AudioPlayer.getInstance().stop();
        showScreen("title");
    }
    
    // ==================== PRIVATE HELPERS ====================
    
    private void panelObjects() {
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        
        titlePanel = new TitleScreenPanel(this);
        settingsPanel = new SettingsPanel(this);
        inventory = new InventoryPanel(this);
        dialoguePanel = new InteractionPanel(this, gameAPI, settingsPanel, inventory);
        shiftPanel = new ShiftPanel(this, gameAPI, settingsPanel, inventory);
        shopPanel = new ShopPanel(this, gameAPI, settingsPanel, inventory);
        daySummary = new DaySummaryPanel(gameAPI, settingsPanel, inventory);
        savePanel = new SavePanel(gameAPI);
        creditsPanel = new CreditsPanel(this);
        
        mainContainer.add(titlePanel, "title");
        mainContainer.add(dialoguePanel, "dialogue");
        mainContainer.add(shiftPanel, "shift");
        mainContainer.add(shopPanel, "shop");
        mainContainer.add(daySummary, "summary");
        mainContainer.add(savePanel, "save");
        
        inventory.setOnItemEffect((effectType, effectValue) -> {
            ItemUse itemUse = getCurrentItemUse();
            
            if (itemUse != null) {
                if (effectType == Item.EffectType.TIMER_BOOST && 
                    !gameAPI.getCurrentSegment().equals("MORNING")) {
                    JOptionPane.showMessageDialog(null,
                        "Clock can only be used during work hours!",
                        "Cannot Use Now",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    itemUse.handleItemEffect(effectType, effectValue);
                }
            }
        });
    }
    
    private ItemUse getCurrentItemUse() {
        for (Component comp : mainContainer.getComponents()) {
            if (comp.isVisible()) {
                if (comp instanceof ShiftPanel) {
                    return ((ShiftPanel) comp).getItemUse();
                } else if (comp instanceof InteractionPanel) {
                    return ((InteractionPanel) comp).getItemUse();
                } else if (comp instanceof DaySummaryPanel) {
                    return ((DaySummaryPanel) comp).getItemUse();
                }
            }
        }
        return null;
    }
    
    private void setupLayeredContent() {
        transitionLayer = new TransitionLayer();
        
        JLayeredPane layered = new JLayeredPane();
        layered.setPreferredSize(new Dimension(1280, 720));
        
        mainContainer.setBounds(0, 0, 1280, 720);
        transitionLayer.setBounds(0, 0, 1280, 720);
        
        layered.add(mainContainer, JLayeredPane.DEFAULT_LAYER);
        layered.add(transitionLayer, JLayeredPane.PALETTE_LAYER);
        
        setContentPane(layered);
    }
}