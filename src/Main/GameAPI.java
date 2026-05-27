/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Entities.AudioPlayer;
import Entities.Call;
import Entities.Item;
import Entities.Player;
import GUI.panels.InventoryPanel;
import GUI.panels.SavePanel;
import Storyline.AmayaRoute.AmayaRoute;
import Storyline.CeleresRoute.CeleresRoute;
import Storyline.ClomaRoute.ClomaRoute;
import Storyline.Day1;
import Storyline.Day2;
import Storyline.Day3;
import Storyline.Day6;
import Storyline.DayInterface;
import Storyline.RosarioRoute.RosarioRoute;
import Storyline.RouteManager;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dell
 */
public class GameAPI {
    
    public GameAPI() {
        loadAllRoutes();
        playerInventory = new HashMap<>();
        callLoader = new CallLoader();
    }
    
    private HashMap<Integer, DayInterface> allDays;
    private HashMap<String, RouteManager> allRoutes;
    private HashMap<String, Integer> playerInventory;
    private RouteManager lpStorage;      
    private RouteManager activeRoute;
    private CallLoader callLoader;
    
    private Player playerProfile = new Player();
    
    private int ppAtShiftStart;
    private int lpAtShiftStart;
    private int salaryAtShiftStart;
    
    public enum EndingType { GOOD, BAD, NEUTRAL }
    private EndingType endingType = EndingType.NEUTRAL;
    
    public enum Segment { MORNING, AFTERNOON, EVENING, ENDING }
    private Segment currentSegment = Segment.MORNING;
    
    private int     dialogueScene = 0;
    private boolean dialogueDone  = false;
    private int     subSceneIndex = 0;
    private String  choiceID = "null";
    private String  chosenChoiceID = "null";
    
    private int currentDay                = 1;
    private int callsCompletedToday       = 0;
    public static final int CALLS_PER_DAY = 3;
    public static final int TOTAL_DAYS    = 7;
    
    private int sharedPP     = 0;
    private int sharedSalary = 0;
    
    private DayInterface currentDayScript;
    
    public void resetStats() {
        System.out.println("-----------RESETTING STATS------------");
        sharedPP            = 0;
        sharedSalary        = 0;
        currentDay          = 1;
        callsCompletedToday = 0;
        dialogueScene       = 0;
        subSceneIndex       = 0;
        choiceID            = "null";
        chosenChoiceID      = "null";
        dialogueDone        = false;
        currentSegment      = Segment.MORNING;
        playerProfile       = new Player();
        ppAtShiftStart      = 0;
        lpAtShiftStart      = 0;
        salaryAtShiftStart  = 0;
        if (activeRoute != null) activeRoute.reset();
        activeRoute = null;
        playerInventory.clear();
        lpStorage.reset();
        callLoader.shuffleCalls();
        currentDayScript    = scriptForDay(currentDay);
    }
    
    private void loadAllRoutes(){
        allRoutes = new HashMap<>();
        allDays = new HashMap<>();
        lpStorage = new RouteManager();   // ← This stores LP
        activeRoute = null;               // ← No route yet

        allRoutes.put("Amaya", new AmayaRoute());
        allRoutes.put("Celeres", new CeleresRoute());
        allRoutes.put("Cloma", new ClomaRoute());
        allRoutes.put("Rosario", new RosarioRoute());
        
        allDays.put(1, new Day1());
        allDays.put(2, new Day2());
        allDays.put(3, new Day3());
        allDays.put(6, new Day6());
    }
    
    public Call[] getCallShift(){ return callLoader.getCallShift(currentDay); }
    public RouteManager getCharacterRoute(String character){ return allRoutes.get(character); }
    public RouteManager getLpStorage() { return lpStorage; }
    public RouteManager getActiveRoute() { return activeRoute; }
    public void setActiveRoute(String characterId) { activeRoute = allRoutes.get(characterId); }
    
    public int getPpGained() { return ppAtShiftStart; }
    public int getLpGained() { return lpAtShiftStart; }
    public int getSalaryGained() { return salaryAtShiftStart; }
    public void setPpGained(int pp){ ppAtShiftStart=pp; }
    public void setSalaryGained(int salary){ salaryAtShiftStart=salary; }
    public void addPpGained(int pp){ ppAtShiftStart+=pp; }
    public void addSalaryGained(int salary){ salaryAtShiftStart+=salary; }
    
    public Player  getPlayerProfile() { return playerProfile; }
    public String  getPlayerName()    { return playerProfile.getName(); }
    public String  getPlayerGender()  { return playerProfile.getGender(); }
    public String  getPlayerPronoun() { return playerProfile.getPronoun(); }

    public void setPlayerIdentity(String name, String gender, String pronoun) {
        playerProfile.set(name, gender, pronoun);
    }
    
    public void setEndingType(EndingType t) { this.endingType = t; }
    public EndingType getEndingType()       { return endingType; }
    
    public int  getPP()          { return sharedPP; }
    public int  getSalary()      { return sharedSalary; }
    public void setPP(int v)     { sharedPP = v; }
    public void setSalary(int v) { sharedSalary = v; }
    public int getLP() {
        if (lpStorage.hasActiveRoute()) return lpStorage.getActiveLP();
        return 0;
    }
    public void setLP(int v) {
        if (lpStorage.hasActiveRoute()) {
            String activeChar = lpStorage.getActiveCharacter();
            setLPForCharacter(activeChar, v);
        }
    }
    public void addPP(int v){ sharedPP += v; ppAtShiftStart += v; }
    public void addSalary(int v){ sharedSalary += v; salaryAtShiftStart += v; }
    public void addLP(int amount){
        if (lpStorage.hasActiveRoute())
            addLPForCharacter(lpStorage.getActiveCharacter(), amount); 
    }

    public int getLPForCharacter(String character) { return lpStorage.getLPForCharacter(character); }
    public void setLPForCharacter(String character, int value) { lpStorage.setCharacter(character, value); }
    public boolean addLPForCharacter(String character, int value){
        lpStorage.addCharacterLP(character, value);
        System.out.println("AMAYA: " + lpStorage.getLPForCharacter("Amaya")); 
        System.out.println("CELERES: " + lpStorage.getLPForCharacter("Celeres")); 
        System.out.println("CLOMA: " + lpStorage.getLPForCharacter("Cloma")); 
        System.out.println("ROSARIO: " + lpStorage.getLPForCharacter("Rosario")); 
        if(lpStorage.hasActiveRoute()){ lpAtShiftStart += value; return true; } 
        return false;
    }

    public int  getCurrentDay()           { return currentDay; }
    public void setCurrentDay(int day)    { this.currentDay = day; }
    public void setCallsCompleted(int num){ callsCompletedToday = num; }
    public int  getCallsCompleted()       { return callsCompletedToday; }

    public int     getDialogueScene()          { return dialogueScene; }
    public void    setDialogueScene(int scene) { dialogueScene = scene; }
    public boolean isDialogueDone()            { return dialogueDone; }
    public void    setDialogueDone(boolean v)  { dialogueDone = v; }
    public int     getSubSceneIndex()          { return subSceneIndex; }
    public void    setSubSceneIndex(int idx)   { subSceneIndex = idx; }
    public String  getChoiceID()               { return choiceID; }
    public void    setChoiceID(String id)      { choiceID = id; }
    public String  getChosenChoiceID()         { return chosenChoiceID; }
    public void    setChosenChoiceID(String id){ chosenChoiceID = id; }

    public Segment  getCurrentSegment()          { return currentSegment; }
    public void     setCurrentSegment(Segment s) { currentSegment = s; }

    public void setCurrentDayScript(DayInterface currentDayScript){ this.currentDayScript = currentDayScript; }
    public DayInterface getCurrentDayScript(){ return currentDayScript; }
    
    public DayInterface scriptForDay(int day) {
        if (activeRoute != null) { return activeRoute.getDayScript(day); } 
        return allDays.get(day);
    }
    
    public enum Screen{ DIALOGUE, SHIFT, SUMMARY, GAMECOMPLETE, ENDING; }
    public Screen onMorningComplete() {
        System.out.println("ON MORNING COMPLETE");
        dialogueScene = 0;
        subSceneIndex = 0;
        choiceID = "null";
        chosenChoiceID = "null";
        dialogueDone = false;
        
        // Check if this is Day 7 — play ending instead of CallShift
        if (currentDay >= TOTAL_DAYS) { 
            currentSegment = GameAPI.Segment.ENDING;             
            return Screen.ENDING;
        }

        // SPECIAL CASE: Day 6 afternoon (company event)
        if (currentDay == 6 && activeRoute != null) {
            DayInterface day6 = allDays.get(6);
            ((Day6)day6).setPlayerPP(getPP());
            setCurrentDayScript(day6);
            currentSegment = GameAPI.Segment.AFTERNOON;
            return Screen.DIALOGUE;
        }

        // Normal CallShift
        return Screen.SHIFT;
    }

    public Screen onAfternoonComplete() {
        System.out.println("ON AFTERNOON COMPLETE");
        if (currentSegment == Segment.ENDING) return Screen.GAMECOMPLETE;
        if (currentDay == 6 && activeRoute != null) 
            setCurrentDayScript(activeRoute.getDayScript(6));
        currentSegment = Segment.EVENING;
        dialogueScene = 0;
        subSceneIndex = 0;
        choiceID = "null";
        chosenChoiceID = "null";
        dialogueDone = false;
        return Screen.DIALOGUE; 
    }

    public Screen onEndDay() {
        System.out.println("ON EVENING COMPLETE");
        dialogueScene = 0;
        subSceneIndex = 0;
        choiceID = "null";
        chosenChoiceID = "null";
        dialogueDone = false;
        currentDay++;
        callsCompletedToday = 0;
        ppAtShiftStart = 0;
        lpAtShiftStart = 0;
        salaryAtShiftStart = 0;
        
        if (currentDay == TOTAL_DAYS){ currentSegment = GameAPI.Segment.ENDING; }
        else { 
            currentSegment = GameAPI.Segment.MORNING; 
            setCurrentDayScript(scriptForDay(currentDay)); 
        }
        return Screen.DIALOGUE;
    }
}
