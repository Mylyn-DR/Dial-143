package Storyline.ClomaRoute;

import Storyline.*;
import Entities.Character;
import java.util.ArrayList;

public class ClomaRoute extends RouteManager {
    
    private static final String ROUTE_NAME = "Cloma";
    private static final int THRESHOLD_GOOD = 60;
    private static final int THRESHOLD_NEUTRAL = 40;
    private ArrayList<DayInterface> days; 
    
    public ClomaRoute() {
        super(ROUTE_NAME, THRESHOLD_GOOD, THRESHOLD_NEUTRAL);
        days = new ArrayList<>();
        days.add(new Day3Cloma());
        days.add(new Day4Cloma()); 
        days.add(new Day5Cloma()); 
        days.add(new Day6Cloma()); 
        days.add(new Day7Cloma());
    }
    
    @Override
    public DayInterface getDayScript(int day) {
        if (day>7) return null;
        return days.get(day-3); //<-- because index starts with 0
    }

    
    @Override
    public SceneEntry[] getEndingScene(int finalLP) {
        if (finalLP >= THRESHOLD_GOOD) {
            return Day7Cloma.getGoodEnding();
        } else if (finalLP >= THRESHOLD_NEUTRAL) {
            return Day7Cloma.getGoodEnding();
        } else {
            return Day7Cloma.getBadEnding();
        }
    }
}