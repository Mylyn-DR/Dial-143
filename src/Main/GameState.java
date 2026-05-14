/*
 * Click nbfs
 * Click nbfs
 */
package Main;

import Entities.Item;
import Entities.Player;
import Storyline.RouteManager;

import java.util.*;

public class GameState {
    private final Player playerProfile;
    private int pp;
    private int salary;
    private int currentDay;
    private int callsCompletedToday;
    private String currentSegment;
    private int dialogueScene;
    private boolean dialogueDone;
    private String currentDayScript = "Day1";  // Which day script is active (e.g., "Day4")
    private int currentSceneIndex;    // Which scene within the script
    
    
    // Route management
    private RouteManager activeRoute;
    private String activeCharacter;
    private RouteManager lpStorage;  // ← This stores LP for ALL characters
    
    // LP storage (backup, but lpStorage handles this)
    private final Map<String, Integer> lpMap;
    
    // Inventory & bonuses
    private final List<Item> inventory;
    private int ppMultiplierBonus;
    private int lpMultiplierDailyBonus;
    private boolean hintAvailable;
    
    public GameState() {
        playerProfile = new Player();
        lpMap = new HashMap<>();
        inventory = new ArrayList<>();
        lpStorage = new RouteManager();  // ← Initialize lpStorage
        activeRoute = null;
        reset();
    }
    
    public void reset() {
        pp = 0;
        salary = 0;
        currentDay = 1;
        callsCompletedToday = 0;
        currentDayScript = "Day1";
        currentSegment = "MORNING";
        dialogueScene = 0;
        dialogueDone = false;
        activeRoute = null;
        activeCharacter = null;
        lpMap.clear();
        inventory.clear();
        ppMultiplierBonus = 0;
        lpMultiplierDailyBonus = 0;
        hintAvailable = false;
        playerProfile.set("", "", "");
        if (lpStorage != null) lpStorage.reset();  // ← Reset lpStorage
    }
    
    // ==================== PLAYER IDENTITY ====================
    public void setPlayerIdentity(String name, String gender, String pronoun) {
        playerProfile.set(name, gender, pronoun);
    }
    
    public Player getPlayerProfile() { return playerProfile; }
    public String getPlayerName() { return playerProfile.getName(); }
    public String getPlayerGender() { return playerProfile.getGender(); }
    public String getPlayerPronoun() { return playerProfile.getPronoun(); }
    
    // ==================== STATS ====================
    public int getPP() { return pp; }
    public void setPP(int val) { pp = val; }
    public void addPP(int amount) { pp += amount; }
    
    public int getSalary() { return salary; }
    public void setSalary(int val) { salary = val; }
    public void addSalary(int amount) { salary += amount; }
    
    // ==================== LP STORAGE (delegates to lpStorage) ====================
    public RouteManager getLpStorage() { return lpStorage; }
    
    // LP for a specific character
    public int getLPForCharacter(String character) {
        return lpStorage.getLPForCharacter(character);
    }
    
    public void setLPForCharacter(String character, int value) {
        int current = lpStorage.getLPForCharacter(character);
        int diff = value - current;
        if (diff != 0) lpStorage.addCharacterLP(character, diff);
    }
    
    public void addLPForCharacter(String character, int amount) {
        lpStorage.addCharacterLP(character, amount);
    }
    
    // Active route LP
    public int getLP() {
        if (hasActiveRoute()) return lpStorage.getActiveLP();
        return 0;
    }
    
    public void setLP(int v) {
        if (hasActiveRoute()) {
            String activeChar = lpStorage.getActiveCharacter();
            int diff = v - lpStorage.getLPForCharacter(activeChar);
            if (diff != 0) lpStorage.addCharacterLP(activeChar, diff);
        }
    }
    
    public void addLP(int amount) {
        if (hasActiveRoute()) {
            lpStorage.addCharacterLP(lpStorage.getActiveCharacter(), amount);
        }
    }
    
    public void addLPToActive(int amount) {
        if (activeCharacter != null) {
            lpStorage.addCharacterLP(activeCharacter, amount);
        }
    }
    
    public int getActiveLP() {
        return lpStorage.getActiveLP();
    }
    
    // Route unlock checks
    public boolean isRouteUnlocked(String character) {
        return lpStorage.isRouteUnlocked(character);
    }
    
    public boolean allRoutesLocked() {
        return lpStorage.allRoutesLocked();
    }
    
    // ==================== DAY / CALL PROGRESS ====================
    public String getCurrentDayScript() { return currentDayScript; }
    public void setCurrentDayScript(String script) { this.currentDayScript = script; }
    
    public int getCurrentDay() { return currentDay; }
    public void setCurrentDay(int day) { currentDay = day; currentDayScript = "Day" + currentDay; }
    public void incrementDay() { currentDay++; currentDayScript = "Day" + currentDay; }
    
    public int getCallsCompletedToday() { return callsCompletedToday; }
    public void setCallsCompletedToday(int val) { callsCompletedToday = val; }
    public void incrementCallsCompleted() { callsCompletedToday++; }
    public void resetCallsCompleted() { callsCompletedToday = 0; }
    
    // ==================== SEGMENT ====================
    public String getCurrentSegment() { return currentSegment; }
    public void setCurrentSegment(String segment) { currentSegment = segment; }
    
    // ==================== DIALOGUE PROGRESS ====================
    public int getDialogueScene() { return dialogueScene; }
    public void setDialogueScene(int scene) { dialogueScene = scene; }
    public boolean isDialogueDone() { return dialogueDone; }
    public void setDialogueDone(boolean done) { dialogueDone = done; }
    
    // ==================== ROUTE MANAGEMENT ====================
    public RouteManager getActiveRoute() { return activeRoute; }
    public void setActiveRoute(RouteManager route) { activeRoute = route; }
    
    public String getActiveCharacter() { return activeCharacter; }
    public void setActiveCharacter(String character) { 
        activeCharacter = character;
    }
    
    public boolean hasActiveRoute() { 
        return activeRoute != null && activeCharacter != null; 
    }
    
    public void clearRoute() {
        activeRoute = null;
        activeCharacter = null;
        if (lpStorage != null) lpStorage.clearRoute();
    }
    
    public enum EndingType { GOOD, BAD, NEUTRAL }
    private EndingType endingType = EndingType.NEUTRAL;

    public EndingType getEndingType() { return endingType; }
    public void setEndingType(EndingType type) { this.endingType = type; }
    
    
    // ==================== INVENTORY ====================
    public List<Item> getInventory() { return inventory; }
    
    public void addItem(Item item) {
        for (Item existing : inventory) {
            if (existing.getName().equals(item.getName())) {
                existing.addQuantity(1);
                return;
            }
        }
        Item copy = new Item(
            item.getName(), item.getDescription(), item.getIconPath(),
            1, item.getPrice(), item.getEffectType(), item.getEffectValue()
        );
        inventory.add(copy);
    }
    
    public boolean removeItem(Item item) {
        if (item.use()) {
            if (item.getQuantity() == 0) {
                inventory.remove(item);
            }
            return true;
        }
        return false;
    }
    
    public boolean hasItem(String itemName) {
        for (Item i : inventory) {
            if (i.getName().equals(itemName) && i.hasStock()) {
                return true;
            }
        }
        return false;
    }
    
    public void clearInventory() { inventory.clear(); }
    
    // ==================== ITEM EFFECT BONUSES ====================
    public int getPPMultiplierBonus() { return ppMultiplierBonus; }
    public void addPPMultiplierBonus(int amount) { ppMultiplierBonus += amount; }
    public void resetPPMultiplier() { ppMultiplierBonus = 0; }
    
    public int getLPMultiplierDailyBonus() { return lpMultiplierDailyBonus; }
    public void addLPMultiplierDailyBonus(int amount) { lpMultiplierDailyBonus += amount; }
    public void resetLPMultiplier() { lpMultiplierDailyBonus = 0; }
    
    public boolean isHintAvailable() { return hintAvailable; }
    public void setHintAvailable(boolean available) { hintAvailable = available; }
    
    public void resetPerCallBonuses() { hintAvailable = false; }
    
    public void resetPerDayBonuses() {
        ppMultiplierBonus = 0;
        lpMultiplierDailyBonus = 0;
        hintAvailable = false;
    }
    
    public int applyPPMultiplier(int rawPP) {
        if (ppMultiplierBonus <= 0) return rawPP;
        int bonus = Math.round(rawPP * ppMultiplierBonus / 100f);
        return rawPP + bonus;
    }
}