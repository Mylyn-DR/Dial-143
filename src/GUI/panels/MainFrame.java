package GUI.panels;

import java.awt.*;
import java.awt.CardLayout;
import javax.swing.*;
import Entities.*;
import GUI.panels.universalComponents.TransitionLayer;
import GUI.panels.InventoryPanel;
import GUI.panels.shiftComponents.CallCreationTimer;
import GUI.panels.universalComponents.TopBarComponents;
import Main.GameAPI;

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
    private TopBarComponents topBar;
    private CallCreationTimer callBox;
    
    private ItemUse          itemUse; 
    
    private GameAPI gameAPI;
    
    public MainFrame(GameAPI gameAPI) {
        setTitle("Dial 143");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(1300, 731));
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/GUI/resources/icons/gameTitle.png"));
        
        this.gameAPI = gameAPI;
        resetStats(); 
        
        panelObjects();
        setUpLayeredContent();

        pack();
        setLocationRelativeTo(null);
        cardLayout.show(mainContainer, "title");
    }
    
    private void resetStats(){ 
        gameAPI.resetStats();
        if (inventory != null) inventory.clear();
    }
    
    public void addItemToInventory(Item item) {
        if (inventory != null) {
            inventory.addItem(item);
        }
    }
    
    public void playEndingMusic() {
        AudioPlayer.Track track = switch (gameAPI.getEndingType()) {
            case GOOD    -> AudioPlayer.Track.GOOD_ENDING;
            case BAD     -> AudioPlayer.Track.BAD_ENDING;
            case NEUTRAL -> AudioPlayer.Track.NEUTRAL_ENDING;
        };
        AudioPlayer.getInstance().crossfadeTo(track, 1200);
    }

    // ── Game flow ─────────────────────────────────────────────────────────────
    public void onMorningComplete(){ 
        GameAPI.Screen state = gameAPI.onMorningComplete(); 
        if (state == GameAPI.Screen.SHIFT){ showScreen("shift"); } 
        else if (state == GameAPI.Screen.ENDING){ onGameComplete(); return; }
        else{ showScreen("dialogue"); } 
        AudioPlayer.getInstance().crossfadeTo(AudioPlayer.Track.GAMEPLAY, 800); 
    }
    public void onAfternoonComplete(){
        gameAPI.onAfternoonComplete();
        showScreen("dialogue");
    }
    public void onEveningComplete(){ 
        showScreen("summary"); 
    }
    public void onEndDay(){
        gameAPI.onEndDay();
        AudioPlayer.getInstance().crossfadeTo(AudioPlayer.Track.INTRO, 800);
        showScreen("dialogue");
    }
    public void onGameComplete(){ 
        resetStats(); 
        AudioPlayer.getInstance().stop(); 
        showScreen("title");   }
    
    public void onShopComplete(){ showScreen("summary"); }
    
    // ── Screen routing ────────────────────────────────────────────────────────
    public void showScreen(String screenName) {
//        System.out.println("===== DEBUG =====");
//        System.out.println("showScreen: " + screenName);
//        System.out.println("Current Day: " + gameAPI.getCurrentDay());
//        System.out.println("Current Segment: " + gameAPI.getCurrentSegment());
//        System.out.println("Active Route: " + (gameAPI.getActiveRoute() != null ? gameAPI.getActiveRoute().getRouteName() : "null"));
//        System.out.println("lpStorage.hasActiveRoute(): " + gameAPI.getLpStorage().hasActiveRoute());
//        System.out.println("Active Character (lpStorage): " + gameAPI.getLpStorage().getActiveCharacter());
//        System.out.println("================");
        
        if (transitionLayer.isTransitioning()) return;

        transitionLayer.fadeOut(() -> {
            cardLayout.show(mainContainer, screenName);
            SwingUtilities.invokeLater(() -> {
                switch (screenName) {
                    case "title"    -> AudioPlayer.getInstance().play(AudioPlayer.Track.INTRO);
                    case "dialogue" -> dialoguePanel.loadContent(); 
                    case "shift"    -> shiftPanel.loadCall();
                    case "shop"     -> shopPanel.loadShop();
                    case "save"     -> { }
                    case "summary"  -> daySummary.loadSummary(() -> onEndDay(), () -> showScreen("shop"));
                }
                Timer timer = new Timer(50, e -> transitionLayer.fadeIn());
                timer.setRepeats(false);
                timer.start();
            });
        });
    }
    
    public CreditsPanel getCreditsPanel() { return creditsPanel; }
    public SavePanel getSavePanel() { return savePanel; }
    
    // ── Constructor ──────────────────────────────────────────────────────────

    private void panelObjects() {
          cardLayout    = new CardLayout();
          mainContainer = new JPanel(cardLayout);
          topBar = new TopBarComponents(this);

          itemUse = new ItemUse(this, gameAPI, topBar);
          titlePanel    = new TitleScreenPanel(this, gameAPI);
          settingsPanel = new SettingsPanel(this);
          inventory = new InventoryPanel(this, gameAPI);
          dialoguePanel = new InteractionPanel(this, gameAPI, itemUse, settingsPanel, inventory);
          shiftPanel    = new ShiftPanel(this, gameAPI, topBar, itemUse, settingsPanel, inventory);
          shopPanel     = new ShopPanel(this, gameAPI, settingsPanel, inventory);
          daySummary    = new DaySummaryPanel(this, gameAPI, settingsPanel, inventory);
          savePanel     = new SavePanel(this, gameAPI);
          creditsPanel = new CreditsPanel(this);

          mainContainer.add(titlePanel,    "title");
          mainContainer.add(dialoguePanel, "dialogue");
          mainContainer.add(shiftPanel,    "shift");
          mainContainer.add(shopPanel,     "shop");
          mainContainer.add(daySummary,    "summary");
          mainContainer.add(savePanel,     "save");
    
        inventory.setOnItemEffect((effectType, effectValue) -> {

            ItemUse itemUse = getCurrentItemUse();

            if (itemUse != null) {
                // Let ItemUse handle all effects, but with screen restrictions
                if (effectType == Item.EffectType.TIMER_BOOST && gameAPI.getCurrentSegment() != GameAPI.Segment.MORNING) {
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
        // Find the currently visible panel that has an ItemUse
        for (Component comp : mainContainer.getComponents()) {
            if (comp.isVisible()) {
                if (comp instanceof ShiftPanel) {
                    return ((ShiftPanel) comp).getItemUse();
                } else if (comp instanceof InteractionPanel) {
                    return ((InteractionPanel) comp).getItemUse();
                }
            }
        }
        return null;
    }

    private void setUpLayeredContent() {
        transitionLayer = new TransitionLayer();

        JLayeredPane layered = new JLayeredPane();
        layered.setPreferredSize(new Dimension(1280, 720));

        mainContainer.setBounds(0, 0, 1280, 720);
        transitionLayer.setBounds(0, 0, 1280, 720);

        layered.add(mainContainer,   JLayeredPane.DEFAULT_LAYER);
        layered.add(transitionLayer, JLayeredPane.PALETTE_LAYER);

        setContentPane(layered);
    }

    public void showSave(String returnScreen) {
        dialoguePanel.saveScene();
        transitionLayer.fadeOut(() -> {
            System.out.println("returning from main");
            cardLayout.show(mainContainer, "save");
            savePanel.loadSave(returnScreen);
            transitionLayer.fadeIn();
        });
    }
}