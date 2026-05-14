package GUI.panels;

import GUI.panels.universalComponents.BackgroundLayer;
import GUI.panels.universalComponents.TopBarComponents;
import GUI.panels.shopComponents.ShopLayer;
import Entities.Item;
import Entities.ItemUse;
import Main.GameAPI;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.JOptionPane;

public class ShopPanel extends JPanel {

    private final GameAPI     gameAPI;
    private final MainFrame   mainFrame;
    private final SettingsPanel settings;
    private final InventoryPanel inventory;
    private ItemUse itemUse;   
    private BackgroundLayer  bg;
    private TopBarComponents topBar;
    private ShopLayer        shopBox;
    
    public ItemUse getItemUse() {
        return itemUse;
    }
    
    public ShopPanel(GameAPI gameAPI, MainFrame mainFrame, SettingsPanel sharedSettings, InventoryPanel inventory) {
        this.gameAPI = gameAPI;
        this.mainFrame = mainFrame;
        this.settings  = sharedSettings;
        this.inventory = inventory;
        setPreferredSize(new Dimension(1280, 720));
        setLayout(new OverlayLayout(this));
        initializeLayers();
    }

    public void loadShop() {
        topBar.updateForShop(gameAPI.getCurrentDay());
        topBar.setPpValue(gameAPI.getPP());
        
        // Get LP from GameAPI using active character
        int currentLP = 0;
        if (gameAPI.hasActiveRoute()) {
            String activeChar = gameAPI.getActiveCharacter();
            currentLP = gameAPI.getLPForCharacter(activeChar);
        }
        topBar.setLpValue(currentLP);
        topBar.setSalaryValue(gameAPI.getSalary());
        
        shopBox.load();
        shopBox.setPlayerFunds(gameAPI.getSalary());
        shopBox.setNavButtonImages("back.png", "next.png");

        shopBox.setOnPurchase((item, cost) -> {
            topBar.setSalaryValue(shopBox.getPlayerFunds());
            item.setQuantity(1);   
            inventory.addItem(item);
            topBar.refreshInventoryButton();
        });

        shopBox.setOnExit(() -> {
            gameAPI.setSalary(shopBox.getPlayerFunds());  
            saveStats();
            mainFrame.onShopComplete();
        });
    }
    
    private void saveStats() {
        gameAPI.setPP(topBar.getCurrentPpValue());
        gameAPI.setSalary(shopBox.getPlayerFunds());
        
        // Save LP if active route exists
        if (gameAPI.hasActiveRoute()) {
            String activeChar = gameAPI.getActiveCharacter();
            gameAPI.setLPForCharacter(activeChar, topBar.getCurrentLpValue());
        }
    }

    private void initializeLayers() {
        bg = new BackgroundLayer();
        bg.setBackgroundFromFile("ConvenienceStore.jpg");

        topBar = new TopBarComponents(gameAPI);
        topBar.setSettingsPanel(settings);
        topBar.setParentScreen("shop");
        topBar.setInventoryPanel(inventory);
        shopBox = new ShopLayer();

        itemUse = new ItemUse(gameAPI, topBar, null);

        add(topBar);
        add(shopBox);
        add(bg);
    }
    
    @Override public Dimension getMinimumSize()   { return new Dimension(1280, 720); }
    @Override public Dimension getMaximumSize()   { return new Dimension(1280, 720); }
    @Override public Dimension getPreferredSize() { return new Dimension(1280, 720); }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new javax.swing.OverlayLayout(this));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
