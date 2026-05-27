package Storyline.RosarioRoute;

import Storyline.*;
import Entities.Character;
import java.util.ArrayList;

public class RosarioRoute extends RouteManager {
    
    private static final String ROUTE_NAME = "Rosario";
    private static final int THRESHOLD_GOOD = 60;
    private static final int THRESHOLD_NEUTRAL = 40;
    private ArrayList<DayInterface> days; 
    
    public RosarioRoute() {
        super(ROUTE_NAME, THRESHOLD_GOOD, THRESHOLD_NEUTRAL);
        days = new ArrayList<>();
        days.add(new Day3Rosario());
        days.add(new Day4Rosario()); 
        days.add(new Day5Rosario()); 
        days.add(new Day6Rosario()); 
        days.add(new Day7Rosario());
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
            return Day7Rosario.getGoodEnding();
        } else if (finalLP >= THRESHOLD_NEUTRAL) {
            return Day7Rosario.getGoodEnding();
        } else {
            return Day7Rosario.getBadEnding();
        }
    }
}