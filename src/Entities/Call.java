package Entities;

import java.util.HashMap;

public class Call {
    private String callerName; 
    private Difficulty callDifficulty;
    private enum Difficulty {
        EASY (2), MEDIUM (5), HARD (10); 
        public final int multiplier;
        Difficulty(int multiplier) { this.multiplier = multiplier; }
    }
    private enum OptionScore {
        BEST (10, 50), GOOD (5, 25), BAD (-5, -5), NOANSWER (0, 0); 
        public final int pp, salary;
        OptionScore(int pp, int salary){ this.pp = pp; this.salary = salary; }
    }
    private String callDescription;
    private HashMap<OptionScore, String[]> optionCalls;
    private OptionScore selectedAnswer; 
    
    public Call(String name, String diff){
        callerName = name;
        if(diff.equals("EASY")){ callDifficulty = Difficulty.EASY; } 
        else if(diff.equals("MEDIUM")){ callDifficulty = Difficulty.MEDIUM; } 
        else if(diff.equals("HARD")){ callDifficulty = Difficulty.HARD; }
        else callDifficulty = Difficulty.EASY; 
        
        optionCalls = new HashMap<>();
        optionCalls.put(OptionScore.BEST, new String[3]);
        optionCalls.put(OptionScore.GOOD, new String[3]);
        optionCalls.put(OptionScore.BAD, new String[3]);
        optionCalls.put(OptionScore.NOANSWER, new String[2]);
        
        // Index 0 = option description (button text)
        // Index 1 = caller reply
        // Index 2 = player reply
        // NOANSWER has no player reply, only optionDesc and caller reply
    } 
    
    // SETTERS
    public void setCallDescription(String description){ callDescription = description; }
    public void setOptionDialogue(String option, String desc, String[] dialogue) {
        setCallOption(option, desc);
        setCallerReply(option, dialogue[1]);
        setPlayerReply(option, dialogue[0]);  }
    public void setCallOption(String option, String optionDesc) 
    { optionCalls.get(OPTION(option))[0] = optionDesc; }
    public void setCallerReply(String option, String reply) 
    { optionCalls.get(OPTION(option))[1] = reply; }
    public void setPlayerReply(String option, String reply) 
    { optionCalls.get(OPTION(option))[2] = reply; }
    public void selectAnswer(String answer) 
    { selectedAnswer = (answer == null ? null : OPTION(answer)); }
    
    // GETTERS
    public String getCallerName(){ return callerName; }
    public String getCallDescription(){ return callDescription; }
    public String getCallOption(String option) {  return optionCalls.get(OPTION(option))[0]; }
    public String[] getAllCallOptions() 
    { return new String[]{ getCallOption("BEST"), getCallOption("GOOD"), getCallOption("BAD")}; }
    public String getPlayerReply() { return optionCalls.get(selectedAnswer)[2]; }
    public String getCallerReply() { return optionCalls.get(selectedAnswer)[1]; }
    public int getPerformancePoints() {
        if (selectedAnswer == null) return 0;
        return selectedAnswer.pp * callDifficulty.multiplier;
    }
    public int getSalary() {
        if (selectedAnswer == null) return 0;
        return selectedAnswer.salary * callDifficulty.multiplier;
    }
    
    // HELPER
    private OptionScore OPTION(String option) {
        if(option.equalsIgnoreCase("BEST")) return OptionScore.BEST; 
        if(option.equalsIgnoreCase("GOOD")) return OptionScore.GOOD; 
        if(option.equalsIgnoreCase("BAD")) return OptionScore.BAD; 
        if(option.equalsIgnoreCase("NOANSWER")) return OptionScore.NOANSWER; 
        return OptionScore.NOANSWER;
    }
}