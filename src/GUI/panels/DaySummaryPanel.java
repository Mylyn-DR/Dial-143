package GUI.panels;

import GUI.panels.universalComponents.*;
import GUI.panels.InventoryPanel;
import Entities.*;
import Main.GameAPI;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class DaySummaryPanel extends JPanel {

    private final GameAPI       gameAPI;
    private final SettingsPanel settings;
    private final InventoryPanel inventory;

    private BackgroundLayer  bg;
    private TopBarComponents topBar;
    private DaySummaryLayer  summaryLayer;
    private ItemUse itemUse;


    public ItemUse getItemUse() {
        return itemUse;
    }
    
    public DaySummaryPanel(GameAPI gameAPI, SettingsPanel sharedSettings, InventoryPanel inventory) {
        this.gameAPI = gameAPI;
        this.settings  = sharedSettings;
        this.inventory = inventory;
        setPreferredSize(new Dimension(1280, 720));
        setLayout(new OverlayLayout(this));
        initializeLayers();
    }
    
    public void loadSummary(int ppGained, int lpGained, int salaryGained,
                            int callsDone, Runnable onEndDay, Runnable onGoToShop) {
        topBar.setPpValue(gameAPI.getPP());
        
        // Get LP from GameAPI using active character
        int currentLP = 0;
        if (gameAPI.hasActiveRoute()) {
            String activeChar = gameAPI.getActiveCharacter();
            currentLP = gameAPI.getLPForCharacter(activeChar);
        }
        topBar.setLpValue(currentLP);
        topBar.setSalaryValue(gameAPI.getSalary());
        topBar.setDayInfo(gameAPI.getCurrentDay(), "Day Summary");
        topBar.updateForSummary(gameAPI.getCurrentDay());

        summaryLayer.load(
            gameAPI.getCurrentDay(),
            ppGained, lpGained, salaryGained, callsDone,
            gameAPI.getPP(), currentLP, gameAPI.getSalary(),
            onEndDay, onGoToShop
        );
    }

    private void initializeLayers() {
        bg = new BackgroundLayer();
        bg.setBackgroundFromFile("lightBG.jpg");
        itemUse = new ItemUse(gameAPI, topBar, null);

        topBar = new TopBarComponents(gameAPI);
        topBar.setSettingsPanel(settings);
        topBar.setParentScreen("daySummary");
        topBar.setInventoryPanel(inventory);
        
        summaryLayer = new DaySummaryLayer();

        add(topBar);
        add(summaryLayer);
        add(bg);
    }
}