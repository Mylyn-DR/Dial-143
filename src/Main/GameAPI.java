/*
 * Click nbfs
 * Click nbfs
 */
package Main;

import Entities.AudioPlayer;
import Entities.Item;
import Entities.Player;
import Storyline.DayInterface;
import Storyline.RouteManager;

import java.util.List;
import java.util.Map;

public class GameAPI {
    
    private final GameData gameData;
    private final GameState gameState;
    private final SaveManager saveManager; 
    
    public GameAPI() {
        gameData = new GameData();
        gameState = new GameState();
        saveManager = new SaveManager(this);
    }
    
    // ==================== ACCESS TO INTERNAL OBJECTS (use sparingly) ====================
    public GameData getGameData() { return gameData; }
    public GameState getGameState() { return gameState; }
    
    // ==================== PLAYER IDENTITY ====================
    public String getPlayerName() { return gameState.getPlayerName(); }
    public String getPlayerGender() { return gameState.getPlayerGender(); }
    public String getPlayerPronoun() { return gameState.getPlayerPronoun(); }
    public Player getPlayerProfile() { return gameState.getPlayerProfile(); }
    
    public void setPlayerIdentity(String name, String gender, String pronoun) {
        gameState.setPlayerIdentity(name, gender, pronoun);
    }
    
    // ==================== STATS ====================
    public int getPP() { return gameState.getPP(); }
    public void setPP(int val) { gameState.setPP(val); }
    public void addPP(int amount) { gameState.addPP(amount); }
    
    public int getSalary() { return gameState.getSalary(); }
    public void setSalary(int val) { gameState.setSalary(val); }
    public void addSalary(int amount) { gameState.addSalary(amount); }
    
    // ==================== LP MANAGEMENT ====================
    public int getLP() { return gameState.getLP(); }
    public void setLP(int val) { gameState.setLP(val); }
    public void addLP(int amount) { gameState.addLP(amount); }
    
    public int getLPForCharacter(String character) { 
        return gameState.getLPForCharacter(character); 
    }
    
    public void setLPForCharacter(String character, int value) { 
        gameState.setLPForCharacter(character, value); 
    }
    
    public void addLPForCharacter(String character, int amount) { 
        gameState.addLPForCharacter(character, amount); 
    }
    
    public void addLPToActive(int amount) { 
        gameState.addLPToActive(amount); 
    }
    
    public int getActiveLP() { 
        return gameState.getActiveLP(); 
    }
    
    // Route unlock checks
    public boolean isRouteUnlocked(String character) { 
        return gameState.isRouteUnlocked(character); 
    }
    
    public boolean allRoutesLocked() { 
        return gameState.allRoutesLocked(); 
    }
    
    // ==================== ROUTE MANAGEMENT ====================
    public RouteManager getActiveRoute() { 
        return gameState.getActiveRoute(); 
    }
    
    public void setActiveRoute(String characterId) {
        RouteManager route = gameData.getAllRoutes().get(characterId);
        gameState.setActiveRoute(route);
        gameState.setActiveCharacter(characterId);
    }
    
    public String getActiveCharacter() { 
        return gameState.getActiveCharacter(); 
    }
    
    public boolean hasActiveRoute() { 
        return gameState.hasActiveRoute(); 
    }
    
    public void clearRoute() { 
        gameState.clearRoute(); 
    }
    
    // Direct access to RouteManager (for advanced operations)
    public RouteManager getLpStorage() { 
        return gameState.getLpStorage(); 
    }
    
    // ==================== DAY / PROGRESS ====================
    public int getCurrentDay() { return gameState.getCurrentDay(); }
    public void setCurrentDay(int day) { gameState.setCurrentDay(day); }
    public void incrementDay() { gameState.incrementDay(); }
    
    public int getCallsCompletedToday() { return gameState.getCallsCompletedToday(); }
    public void setCallsCompletedToday(int val) { gameState.setCallsCompletedToday(val); }
    public void incrementCallsCompleted() { gameState.incrementCallsCompleted(); }
    public void resetCallsCompleted() { gameState.resetCallsCompleted(); }
    
    public enum EndingType { GOOD, BAD, NEUTRAL }
    private EndingType endingType = EndingType.NEUTRAL;

    public void setEndingType(EndingType t) { this.endingType = t; }
    public EndingType getEndingType() { return endingType; }
    
    // ==================== SEGMENT ====================
    public enum Segment { 
        MORNING, AFTERNOON, EVENING, ENDING;
        public String toString() {
            return switch (this) {
                case MORNING -> "Morning";
                case AFTERNOON -> "Afternoon";
                case EVENING -> "Evening";
                case ENDING -> "Ending";
            };
        }
        public static Segment fromString(String text) {
            if (text == null) return MORNING;
            return switch (text.toUpperCase()) {
                case "MORNING" -> MORNING;
                case "AFTERNOON" -> AFTERNOON;
                case "EVENING" -> EVENING;
                case "ENDING" -> ENDING;
                default -> MORNING;
            };
        }
    }
    
    public Segment getCurrentSegment(){ return Segment.fromString(gameState.getCurrentSegment());}
    public void setCurrentSegment(Segment segment) {gameState.setCurrentSegment(segment.name());}
    
    // ==================== DIALOGUE PROGRESS ====================
    public int getDialogueScene() { return gameState.getDialogueScene(); }
    public void setDialogueScene(int scene) { gameState.setDialogueScene(scene); }
    public boolean isDialogueDone() { return gameState.isDialogueDone(); }
    public void setDialogueDone(boolean done) { gameState.setDialogueDone(done); }
    
    // ==================== INVENTORY ====================
    public List<Item> getInventory() { return gameState.getInventory(); }
    
    public void addItemToInventory(Item item) { 
        gameState.addItem(item); 
    }
    
    public boolean removeItemFromInventory(Item item) { 
        return gameState.removeItem(item); 
    }
    
    public boolean hasItemInInventory(String itemName) { 
        return gameState.hasItem(itemName); 
    }
    
    public void clearInventory() { 
        gameState.clearInventory(); 
    }
    
    // ==================== SHOP ====================
    public List<Item> getShopItems() { 
        return gameData.getShopItems(); 
    }
    
    // ==================== STATIC DATA ACCESS ====================
    public Map<String, RouteManager> getAllRoutes() { 
        return gameData.getAllRoutes(); 
    }
    
    public Map<String, Item> getItemCatalog() { 
        return gameData.getItemCatalog(); 
    }
    
    public Map<String, String> getSpriteMappings() { 
        return gameData.getSpriteMappings(); 
    }
    
    public List<String> getBackgroundFiles() { 
        return gameData.getBackgroundFiles(); 
    }
    
    public CallLoader getCallLoader() { 
        return gameData.getCallLoader(); 
    }
    
    // ==================== ITEM EFFECT BONUSES ====================
    public int getPPMultiplierBonus() { 
        return gameState.getPPMultiplierBonus(); 
    }
    
    public void addPPMultiplierBonus(int amount) { 
        gameState.addPPMultiplierBonus(amount); 
    }
    
    public int getLPMultiplierDailyBonus() { 
        return gameState.getLPMultiplierDailyBonus(); 
    }
    
    public void addLPMultiplierDailyBonus(int amount) { 
        gameState.addLPMultiplierDailyBonus(amount); 
    }
    
    public boolean isHintAvailable() { 
        return gameState.isHintAvailable(); 
    }
    
    public void setHintAvailable(boolean available) { 
        gameState.setHintAvailable(available); 
    }
    
    public void resetPerCallBonuses() { 
        gameState.resetPerCallBonuses(); 
    }
    
    public void resetPerDayBonuses() { 
        gameState.resetPerDayBonuses(); 
    }
    
    public int applyPPMultiplier(int rawPP) { 
        return gameState.applyPPMultiplier(rawPP); 
    }
    
    // ==================== CONSTANTS (from GameData) ====================
    public int getBaseCallTimerSeconds() { return GameData.BASE_CALL_TIMER_SECONDS; }
    public int getTotalDays() { return GameData.TOTAL_DAYS; }
    public int getCallsPerDay() { return GameData.CALLS_PER_DAY; }
    public int getRouteLPThreshold() { return GameData.ROUTE_LP_THRESHOLD; }
    
    // ==================== UTILITY ====================
    public String resolvePlaceholders(String text) {
        return GameData.resolvePlaceholders(
            text, 
            getPlayerName(), 
            getPlayerPronoun()
        );
    }
    
    // ==================== RESET ====================
    public void resetGame() {
        gameState.reset();
    }
    
    // ==================== DAY SCRIPT ====================
    public DayInterface scriptForDay(int day) {
        if (day == 1) return new Storyline.Day1();
        if (day == 2) return new Storyline.Day2();
        if (day == 3) return new Storyline.Day3();
        if (hasActiveRoute()) {
            return getActiveRoute().getDayScript(day);
        }
        return new Storyline.Day3();
    }
    
    // ==================== SAVE MANAGER ====================
    public void saveGame(int slot) { saveManager.saveGame(gameState, slot); }
    public boolean loadGame(int slot) {return saveManager.loadGame(gameState, slot);}
    public boolean saveExists(int slot) {return saveManager.saveExists(slot);}
    public String getSaveInfo(int slot) {return saveManager.getSaveInfo(slot);}
    public void deleteSave(int slot) {saveManager.deleteSave(slot);}
    
    public boolean isChoiceConsumed(String choiceId) {
    return gameState.isChoiceConsumed(choiceId);
}

public void consumeChoice(String choiceId) {
    gameState.consumeChoice(choiceId);
}
}