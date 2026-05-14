package GUI.panels;
 
import GUI.panels.universalComponents.BackgroundLayer;
import GUI.panels.universalComponents.TopBarComponents;
import GUI.panels.shiftComponents.CallCreationTimer;
import GUI.panels.InventoryPanel;
import Entities.Item;
import Entities.ItemUse;
import Main.GameAPI;
import javax.swing.*;
import java.awt.*;
 
public class ShiftPanel extends JPanel {
 
    private final GameAPI        gameAPI;
    private final MainFrame      mainFrame;
    private final SettingsPanel  settings;
    private final InventoryPanel inventory;
    private BackgroundLayer      bg;
    private CallCreationTimer    callBox;
    private TopBarComponents     topBar;
    private ItemUse              itemEffect;

    public ShiftPanel(GameAPI gameAPI, MainFrame mainFrame, SettingsPanel sharedSettings, InventoryPanel inventory) {
        this.gameAPI = gameAPI;
        this.mainFrame = mainFrame;
        this.settings  = sharedSettings;
        this.inventory = inventory;
        setPreferredSize(new Dimension(1280, 720));
        setLayout(new OverlayLayout(this));
        initializeLayers();
    }
 
    private void initializeLayers() {
        bg = new BackgroundLayer();
        bg.setBackgroundFromFile("MorningOffice.jpg");
 
        callBox = new CallCreationTimer(gameAPI);
        topBar = new TopBarComponents(gameAPI);
        
        itemEffect = new ItemUse(gameAPI, topBar, callBox);
 
        callBox.onPointsAwarded((pp, salary) -> {
            int originalPP = pp;
            int boostedPP = itemEffect.applyPPMultiplier(pp);

            topBar.addPpPoints(boostedPP);
            topBar.addSalaryPoints(salary);
        });

        callBox.onCallComplete(() -> {
            itemEffect.resetPerCall();
 
            if (callBox.hasMoreCalls()) {
                Timer t = new Timer(100, e -> {
                    callBox.loadTest();
                    
                    if (itemEffect.isHintAvailable()) {
                        callBox.activateHint();
                    }
                });
                t.setRepeats(false);
                t.start();
            } else {
                saveStats();
                Timer t = new Timer(100, e -> mainFrame.onCallComplete());
                t.setRepeats(false);
                t.start();
            }
        });
 
        topBar.setSettingsPanel(settings);
        topBar.setParentScreen("shift");
        topBar.setInventoryPanel(inventory);
 
        topBar.onSettingsOpening(() -> callBox.pauseTimer());
        topBar.onSettingsClosed(() -> {
            saveStats();
            callBox.resumeTimer();
        });
 
        topBar.onInventoryOpening(() -> callBox.pauseTimer());
        topBar.onInventoryClosed(() -> {
            saveStats();
            callBox.resumeTimer();
            if (itemEffect.isHintAvailable()) { 
                callBox.activateHint();
            }
        });
        
        inventory.setOnItemEffect((effectType, effectValue) -> {
            itemEffect.handleItemEffect(effectType, effectValue);
        });
 
        add(topBar);
        add(callBox);
        add(bg);
    }
    
    public ItemUse getItemUse() {
        return itemEffect;
    }
 
    public void loadCall() {
        topBar.updateForShift(gameAPI.getCurrentDay());
        topBar.setPpValue(gameAPI.getPP());
        
        // Get LP from GameAPI using active character
        int currentLP = 0;
        if (gameAPI.hasActiveRoute()) {
            String activeChar = gameAPI.getActiveCharacter();
            currentLP = gameAPI.getLPForCharacter(activeChar);
        }
        topBar.setLpValue(currentLP);
        topBar.setSalaryValue(gameAPI.getSalary());
        callBox.resetRemainingCalls();
        callBox.loadTest();
        
        if (itemEffect.isHintAvailable()) {  
            callBox.activateHint();
        }
    }
 
    private void saveStats() {
        gameAPI.setPP(topBar.getCurrentPpValue());
        gameAPI.setSalary(topBar.getCurrentSalaryValue());
        
        // Save LP if active route exists
        if (gameAPI.hasActiveRoute()) {
            String activeChar = gameAPI.getActiveCharacter();
            gameAPI.setLPForCharacter(activeChar, topBar.getCurrentLpValue());
        }
    }
 
    public void pauseTimer()       { callBox.pauseTimer(); }
    public void resumeTimer()      { callBox.resumeTimer(); }
    public boolean isTimerPaused() { return callBox.isTimerPaused(); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new javax.swing.OverlayLayout(this));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
