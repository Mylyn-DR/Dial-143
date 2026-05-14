package Storyline.AmayaRoute;

import Storyline.*;
import Storyline.DayInterface;
import java.util.ArrayList;

public class AmayaRoute extends RouteManager {
    
    private static final String ROUTE_NAME = "Amaya";
    private static final int THRESHOLD_GOOD = 60;
    private static final int THRESHOLD_NEUTRAL = 40;
    private ArrayList<DayInterface> days; 
    
    public AmayaRoute() {
        super(ROUTE_NAME, THRESHOLD_GOOD, THRESHOLD_NEUTRAL);
        days = new ArrayList<>();
        days.add(new Day3Amaya());
        days.add(new Day4Amaya()); 
        days.add(new Day5Amaya()); 
        days.add(new Day6Amaya()); 
        days.add(new Day7Amaya());
    }
    
    @Override
    public DayInterface getDayScript(int day) {
        if (day>7) return null;
        return days.get(day-3); //<-- because index starts with 0
    }

    @Override
    public SceneEntry[] getEndingScene(int finalLP) {
        if (finalLP >= THRESHOLD_GOOD) {
            return Day7Amaya.getGoodEnding();
        } else if (finalLP >= THRESHOLD_NEUTRAL) {
            return Day7Amaya.getGoodEnding();
        } else {
            return Day7Amaya.getBadEnding();
        }
    }
}