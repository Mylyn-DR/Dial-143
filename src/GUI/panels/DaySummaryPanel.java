package GUI.panels;

import GUI.panels.universalComponents.*;
import GUI.panels.InventoryPanel;
import Main.GameAPI;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class DaySummaryPanel extends JPanel {

    private final MainFrame mainPanel;
    private GameAPI gameAPI; 

    private final SettingsPanel settings;
    private final InventoryPanel inventory;

    private BackgroundLayer  bg;
    private TopBarComponents topBar;
    private DaySummaryLayer  summaryLayer;
    
    public DaySummaryPanel(MainFrame mainPanel, GameAPI gameAPI, SettingsPanel sharedSettings, InventoryPanel inventory) {
        this.mainPanel = mainPanel;
        this.gameAPI = gameAPI;
        this.settings  = sharedSettings;
        this.inventory = inventory;
        setPreferredSize(new Dimension(1280, 720));
        setLayout(new OverlayLayout(this));
        initializeLayers();
    }
    
    //edit this one to fetch from gameAPI/gamestate
    public void loadSummary(Runnable onEndDay, Runnable onGoToShop) {
        topBar.setPpValue(gameAPI.getPP());
        topBar.setLpValue(gameAPI.getLP());
        topBar.setSalaryValue(gameAPI.getSalary());
        topBar.setDayInfo(gameAPI.getCurrentDay(), "Day Summary");
        topBar.updateForSummary(gameAPI.getCurrentDay());

        summaryLayer.load(
            gameAPI.getCurrentDay(),
            gameAPI.getPpGained(), gameAPI.getLpGained(), gameAPI.getSalaryGained(), 
            gameAPI.getCallsCompleted(), gameAPI.getPP(), gameAPI.getLP(), gameAPI.getSalary(),
            onEndDay, onGoToShop
        );
    }

    private void initializeLayers() {
        bg = new BackgroundLayer();
        bg.setBackgroundFromFile("lightBG.jpg");

        topBar = new TopBarComponents(mainPanel);
        topBar.setSettingsPanel(settings);
        topBar.setParentScreen("daySummary");
        topBar.setInventoryPanel(inventory);
        

        summaryLayer = new DaySummaryLayer();

        add(topBar);
        add(summaryLayer);
        add(bg);
    }
}