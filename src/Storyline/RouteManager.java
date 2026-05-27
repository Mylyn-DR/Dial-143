package Storyline;

import Main.GameAPI.Segment;
import Entities.Character;

/**
 * Base class for all character routes.
 * Manages per-character LP, route state, and route-specific logic.
 */
public class RouteManager {

    public static final int ROUTE_LP_THRESHOLD = 15; // minimum LP to unlock a route option

    private final String routeName;
    private final int lpThresholdGood;
    private final int lpThresholdNeutral;
    private final Character characterLP = new Character();
    private RouteManager activeRoute = null;
    private String activeCharacter = null;
    public RouteManager(){ 
        routeName = ""; lpThresholdGood = 0; lpThresholdNeutral = 0;
    }    
    
    public RouteManager(String routeName, int lpThresholdGood, int lpThresholdNeutral) {
        this.routeName = routeName;
        this.lpThresholdGood = lpThresholdGood;
        this.lpThresholdNeutral = lpThresholdNeutral;
    }
    
    public String getRouteName() { return routeName; }
    public int getLpThresholdGood() { return lpThresholdGood; }
    public int getLpThresholdNeutral() { return lpThresholdNeutral; }
    public DayInterface getDayScript(int day){ return null; }
    public int getTotalDays(){ return -1; }
    public SceneEntry[] getEndingScene(int finalLP){ return null; }
    public boolean isRouteStarted(int day, Segment segment) {
        return day >= 3 && segment == Segment.EVENING;
    }
    public Character getCharacterLP() { return characterLP; }
    public void addCharacterLP(String character, int amount) { characterLP.add(character, amount); }
    public void setCharacter(String character, int amount){ characterLP.setLp(character, amount); }
    public int getLPForCharacter(String character) { return characterLP.get(character); }
    public int getActiveLP() { return activeCharacter != null ? characterLP.get(activeCharacter) : 0; }
    
    public boolean hasActiveRoute() { return activeRoute != null; }
    public RouteManager getActiveRoute() { return activeRoute; }
    public String getActiveCharacter() { return activeCharacter; }

    public DayInterface selectRoute(RouteManager route, String characterName) {
        this.activeRoute = route;
        this.activeCharacter = characterName;
        return route.getDayScript(3);
    }
    
    public void clearRoute() { activeRoute = null; activeCharacter = null; }
    public boolean isRouteUnlocked(String character) { return characterLP.meetsThreshold(character, ROUTE_LP_THRESHOLD); }
    public boolean allRoutesLocked() { return !characterLP.anyMeetsThreshold(ROUTE_LP_THRESHOLD); }
    public void reset() {
        characterLP.reset();
        activeRoute = null;
        activeCharacter = null;
    }
}