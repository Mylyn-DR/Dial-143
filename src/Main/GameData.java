/*
 * Click nbfs
 * Click nbfs
 */
package Main;

import Entities.Call;
import Entities.Item;
import Storyline.RouteManager;
import Storyline.AmayaRoute.AmayaRoute;
import Storyline.CeleresRoute.CeleresRoute;
import Storyline.ClomaRoute.ClomaRoute;
import Storyline.RosarioRoute.RosarioRoute;

import java.util.*;

public class GameData {
    private final Map<String, RouteManager> allRoutes;
    private final Map<String, Item> itemCatalog;
    private final List<Item> shopItems;
    private final Map<String, String> spriteMappings;
    private final List<String> backgroundFiles;
    public static final int BASE_CALL_TIMER_SECONDS = 5;
    public static final int TOTAL_DAYS = 7;
    public static final int CALLS_PER_DAY = 5;
    public static final int ROUTE_LP_THRESHOLD = 10;
    private final CallLoader callLoader; 
    
    public GameData() {
        allRoutes = new HashMap<>();
        itemCatalog = new HashMap<>();
        shopItems = new ArrayList<>();
        spriteMappings = new HashMap<>();
        backgroundFiles = new ArrayList<>();
        callLoader = new CallLoader(); 

        loadRoutes();
        loadItems();
        loadShopItems();
        loadSprites();
        loadBackgrounds();
    }
    private void loadRoutes() {
        allRoutes.put("Amaya", new AmayaRoute());
        allRoutes.put("Celeres", new CeleresRoute());
        allRoutes.put("Cloma", new ClomaRoute());
        allRoutes.put("Rosario", new RosarioRoute());
    }
    private void loadItems() {
        Item coffee = new Item(
            "Coffee",
            "A strong brew to sharpen focus.\n+10% PP earned for one full day.",
            "/GUI/resources/icons/coffee.png",
            0, 80,
            Item.EffectType.PP_MULTIPLIER, 10
        );
        itemCatalog.put("Coffee", coffee);
        Item stickyNote = new Item(
            "Sticky Note",
            "A handy reminder on your desk.\nHighlights the best answer for one call.",
            "/GUI/resources/icons/stickynotes.png",
            0, 120,
            Item.EffectType.HINT_PER_CALL, 1
        );
        itemCatalog.put("StickyNote", stickyNote);
        Item clock = new Item(
            "Clock",
            "A trusty desk clock to buy you time.\n+10 seconds added to one call timer.",
            "/GUI/resources/icons/clock.png",
            0, 150,
            Item.EffectType.TIMER_BOOST, 10
        );
        itemCatalog.put("Clock", clock);
        Item chocolate = new Item(
            "Chocolate",
            "A sweet treat for someone special.\nGrants +5 Love Points instantly.",
            "/GUI/resources/icons/chocolit.png",
            0, 200,
            Item.EffectType.LP_FLAT, 5
        );
        itemCatalog.put("Chocolate", chocolate);
        Item deskPlant = new Item(
            "Desk Plant",
            "A little green companion for their desk.\nPassively grants +5% LP each day.",
            "/GUI/resources/icons/plant.png",
            0, 350,
            Item.EffectType.LP_MULTIPLIER_DAILY, 5
        );
        itemCatalog.put("DeskPlant", deskPlant);
        Item book = new Item(
            "Book",
            "A thoughtful read picked just for them.\nGrants +10 Love Points instantly.",
            "/GUI/resources/icons/book.png",
            0, 300,
            Item.EffectType.LP_FLAT, 10
        );
        itemCatalog.put("Book", book);
    }
    private void loadShopItems() {
        shopItems.add(itemCatalog.get("Coffee"));
        shopItems.add(itemCatalog.get("StickyNote"));
        shopItems.add(itemCatalog.get("Clock"));
        shopItems.add(itemCatalog.get("Chocolate"));
        shopItems.add(itemCatalog.get("DeskPlant"));
        shopItems.add(itemCatalog.get("Book"));
    }
    
    private void loadSprites() {
        spriteMappings.put("Amaya_Smile", "Amaya_Smile.png");
        spriteMappings.put("Amaya_Casual", "Amaya_Casual.png");
        spriteMappings.put("Amaya_Sad", "Amaya_Sad.png");
        spriteMappings.put("Amaya_Mad", "Amaya_Mad.png");
        spriteMappings.put("Amaya_Blushing", "Amaya_Blushing.png");
        spriteMappings.put("Rosario_Smile", "Rosario_Smile.png");
        spriteMappings.put("Rosario_Casual", "Rosario_Casual.png");
        spriteMappings.put("Rosario_Sad", "Rosario_Sad.png");
        spriteMappings.put("Rosario_Mad", "Rosario_Mad.png");
        spriteMappings.put("Rosario_Blushing", "Rosario_Blushing.png");
        spriteMappings.put("Celeres_Smile", "Celeres_Smile.png");
        spriteMappings.put("Celeres_Casual", "Celeres_Casual.png");
        spriteMappings.put("Celeres_Sad", "Celeres_Sad.png");
        spriteMappings.put("Celeres_Mad", "Celeres_Mad.png");
        spriteMappings.put("Celeres_Blushing", "Celeres_Blushing.png");
        spriteMappings.put("Cloma_Smile", "Cloma_Smile.png");
        spriteMappings.put("Cloma_Casual", "Cloma_Casual.png");
        spriteMappings.put("Cloma_Sad", "Cloma_Sad.png");
        spriteMappings.put("Cloma_Mad", "Cloma_Mad.png");
        spriteMappings.put("Cloma_Blushing", "Cloma_Blushing.png");
        spriteMappings.put("Khai_EyesClosed", "Khai_EyesClosed.png");
        spriteMappings.put("Khai_EyesOpen", "Khai_EyesOpen.png");
    }
    
    private void loadBackgrounds() {
        backgroundFiles.addAll(Arrays.asList(
            "MorningOffice.jpg", "EveningOffice.jpg", "BuildingEvening.jpg",
            "BuildingMorning.jpg", "ConvenienceStore.jpg", "EventStage.jpg",
            "StreetMorning.jpg", "StreetEvening.jpg", "MorningElevator.jpg",
            "BreakRoom.jpg", "blackBG.jpg",
            "AMAYA_GOOD.png", "AMAYA_NEUTRAL.png", "AMAYA_BAD.png",
            "CELERES_GOOD.png", "CELERES_NEUTRAL.png", "CELERES_BAD.png",
            "CLOMA_GOOD.png", "CLOMA_NEUTRAL.png", "CLOMA_BAD.png",
            "ROSARIO_GOOD.png", "ROSARIO_NEUTRAL.png", "ROSARIO_BAD.png"
        ));
    }
    
    public Map<String, RouteManager> getAllRoutes() { return allRoutes; }
    public Map<String, Item> getItemCatalog() { return itemCatalog; }
    public List<Item> getShopItems() { return shopItems; }
    public Map<String, String> getSpriteMappings() { return spriteMappings; }
    public List<String> getBackgroundFiles() { return backgroundFiles; }
    public CallLoader getCallLoader(){ return callLoader; }
    
    public static String resolvePlaceholders(String text, String playerName, String pronoun) {
        if (text == null) return "";
        
        String subject = "they";
        String possessive = "their";
        String objective = "them";
        
        if (pronoun != null && !pronoun.isEmpty()) {
            String[] p = pronoun.split("/");
            if (p.length > 0) subject = p[0];
            if (p.length > 1) possessive = p[1];
            if (p.length > 2) objective = p[2];
        }
        
        return text
            .replace("{name}", playerName != null ? playerName : "")
            .replace("{pronoun_subject}", subject)
            .replace("{pronoun_possessive}", possessive)
            .replace("{pronoun_objective}", objective);
    }
}
