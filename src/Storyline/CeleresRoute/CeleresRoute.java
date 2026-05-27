package Storyline.CeleresRoute;

import Storyline.*;
import Entities.Character;
import java.util.ArrayList;

public class CeleresRoute extends RouteManager {
    
    private static final String ROUTE_NAME = "Celeres";
    private static final int THRESHOLD_GOOD = 60;
    private static final int THRESHOLD_NEUTRAL = 40;
    private ArrayList<DayInterface> days; 
    
    public CeleresRoute() {
        super(ROUTE_NAME, THRESHOLD_GOOD, THRESHOLD_NEUTRAL);
        days = new ArrayList<>();
        days.add(new Day3Celeres());
        days.add(new Day4Celeres()); 
        days.add(new Day5Celeres()); 
        days.add(new Day6Celeres()); 
        days.add(new Day7Celeres());
    }
    
    @Override
    public DayInterface getDayScript(int day) {
        if (day>7) return null;
        return days.get(day-3); //<-- because index starts with 0
    }
    
    @Override
    public int getTotalDays() {
        return 7;
    }
    
    @Override
    public SceneEntry[] getEndingScene(int finalLP) {
        if (finalLP >= THRESHOLD_GOOD) {
            return Day7Celeres.getGoodEnding();
        } else if (finalLP >= THRESHOLD_NEUTRAL) {
            return Day7Celeres.getGoodEnding();
        } else {
            return Day7Celeres.getBadEnding();
        }
    }
}