/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Dell
 */
import Classes.Day; 
import Classes.Event; 
import Classes.Interaction;
import Classes.Choice; 
import Classes.CallShift;
import Classes.Call; 
import Classes.CallDialogue;
import Classes.Caller;
import Classes.Conversation; 
import Classes.Ending; 
import Classes.DialogueLine; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Random;
import java.util.Stack; 

public class GameScript {
    private String GameFileScript = "src/Scripts/GAMEFILESCRIPT.txt";
    private ArrayList<Day> days; 
    private HashMap<String, Ending> endings;
    private HashMap<String, ArrayList<Day>> routes; 
    private ArrayList<String> loveInterests;
    private ArrayList<CallShift> callShifts; 
    private ArrayList<Caller> callers;
    //randomly assigns different callers per shift
    //this is in shifts, not in calls.
    
    public GameScript(){
        try {
            initialize(); 
            buildGameScript(GameFileScript);
        } catch (FileNotFoundException ex) {
            System.getLogger(GameScript.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void readScript(){ for(Day day: days){ day.play(); } }
    public void readRoute(String routeName){ 
        ArrayList<Day> routeDays = routes.get(routeName); 
        for(Day day: routeDays){ day.play(); }
        endings.get(routeName).playAllEndings();
    }
    public void readCallShift(){
        for(CallShift cs: callShifts){ cs.playEvent(); }
    }
    
    // GETTERS
    public Day getDay(int idx){ return days.get(idx); }
    public CallShift getCallShift(int idx){
        callShifts.add(assignCallShift()); 
        return callShifts.get(idx); 
    }
    
    // SETTERS - ALL BUILT FOR WRITING THE ENTIRE GAME....
    private void initialize(){
        days = new ArrayList<>(); 
        routes = new HashMap<>();
        endings = new HashMap<>();
        callShifts = new ArrayList<>();
        callers = new ArrayList<>();
 
        routes.put("AMAYA", new ArrayList<>());
        routes.put("CELERES", new ArrayList<>());
        routes.put("CLOMA", new ArrayList<>());
        routes.put("ROSARIO", new ArrayList<>());
        
        endings.put("AMAYA", null);
        endings.put("CELERES", null);
        endings.put("CLOMA", null);
        endings.put("ROSARIO", null);
    }
    
    //OPENS FILES AND CREATES EVERYTHING IN HERE.
    private void buildGameScript(String script) throws FileNotFoundException{
        File filescript = new File(script);
        Scanner sc = new Scanner(filescript);
        while(sc.hasNextLine()){
           String txt = sc.nextLine();
           String filePath = "src/Scripts/"+txt;
           if (txt.contains("CALLSHIFT_SCRIPTS")){ writeCallShift(filePath); }
           else if (txt.contains("DEFAULT_DAY")) days.add(writeDay(filePath));
           else if (txt.contains("DAY")){ 
               String routeName = txt.substring(0, txt.indexOf("_")); 
               //add some edgecase para dili maerror
               routes.get(routeName).add(writeDay(filePath));
           }
           else if (txt.contains("ENDINGS")){
               String routeName = txt.substring(0, txt.indexOf("_")); 
               //add some edgecase para dili maerror
               endings.put(routeName, writeEnding(filePath)); 
           }
        }
        sc.close();
    }
    
    //HELPER
    private ArrayList<String> convertToArrayList(String script) throws FileNotFoundException{
        File encodedFile = new File(script);
        Scanner eSC = new Scanner(encodedFile);
        ArrayList<String>encodedScript = new ArrayList<>();
        while(eSC.hasNextLine()){
            String txt = eSC.nextLine(); 
            if(!txt.isBlank()){ 
                encodedScript.add(txt); 
                //System.out.println(txt); //TERMINAL CHECK
            }
        }
        eSC.close();
        return encodedScript;
    }
    
    //THE ACTUAL BUILDING IS ALL FOUND BELOW 
    
    private Ending writeEnding(String endingScript) throws FileNotFoundException{
        ArrayList<String> endStr = convertToArrayList(endingScript);
        System.out.println("WRITING ENDING");
        Ending ending = new Ending(); 
        System.out.println("CHECK: " + endStr.get(endStr.indexOf("NEW_GOOD")));
        System.out.println(endStr.get(endStr.indexOf("END_GOOD")));
        ending.setEnding("GOOD", writeInteraction(endStr.subList(endStr.indexOf("NEW_GOOD"), endStr.indexOf("END_GOOD"))));
        ending.setEnding("NEUTRAL", writeInteraction(endStr.subList(endStr.indexOf("NEW_NEUTRAL"), endStr.indexOf("END_NEUTRAL"))));
        ending.setEnding("BAD", writeInteraction(endStr.subList(endStr.indexOf("NEW_BAD"), endStr.indexOf("END_BAD"))));
        return ending; 
    }
    
    private Day writeDay(String dayScript) throws FileNotFoundException{
        ArrayList<String> dayStr = convertToArrayList(dayScript);
        Day day = new Day();
        int open =-1 , close = -1; //opening and closing indexes for making sublists
        //rewrite this to indexOf(); 
        for(int i = 0; i<dayStr.size(); i++){
            String str = dayStr.get(i);
            if(str.equals("NEW_INTERACTION") || str.equals("NEW_CALLSHIFT")){ 
                System.out.println("CREATING EVENT -------------------");
                System.out.println(str);
                open = i;
            }
            else if (str.equals("END_INTERACTION") || str.equals("END_CALLSHIFT")){
                System.out.println(str);
                System.out.println("CLOSING EVENT -------------------");
                close = i;
            }
            
            if (open != -1 && close != -1){
                Event event = writeEvent(dayStr.subList(open, close)); 
                day.addEvent(event);
                open = -1; close = -1;
                System.out.println("RESET VALUES\nopen: " + open + " close: " + close + "\n\n");
            }
        }
        
        return day; 
    }
    
    private Event writeEvent(List<String> eventScript){
        if (eventScript.get(0).contains("INTERACTION")){
            return writeInteraction(eventScript);
        }
        else if (eventScript.get(0).contains("CALLSHIFT")){
            return assignCallShift();
        }
        else return null; 
        
//        System.out.println("\n\nREADING EVENT: \n\n");
//        for(String scr : eventScript) System.out.println(scr);
//        System.out.println("\n");
    }
    
    private Interaction writeInteraction(List<String> interactionScript){
        Interaction interaction = new Interaction();  
        String iscr = "";
        for(int i = 0; i < interactionScript.size() && !interactionScript.get(i).equals("END_INTERACTION"); ){
            iscr = interactionScript.get(i++);
            //System.out.println("FROM WRITE INTERACTION: " + iscr); // TERMINAL CHECK 
            if(iscr.equals("NEW_CHOICE")){
                ArrayList<String> choiceScript = new ArrayList<>();
                Stack<String> choicePaths = new Stack<>();
                choicePaths.push(iscr); choiceScript.add(iscr);
                while(!choicePaths.empty()){
                    iscr = interactionScript.get(i++);
                    if(iscr.equals("NEW_CHOICE")) choicePaths.push(iscr);
                    else if(iscr.equals("END_CHOICE") && choicePaths.peek().equals("NEW_CHOICE")) choicePaths.pop();
                    choiceScript.add(iscr);                    
                }
                Choice choice = writeChoice(choiceScript);
                interaction.addChoice(choice);
                interaction = choice.closeChoice();
            }
            else if (iscr.contains("NEW_") || iscr.contains("END_")) continue;
            else interaction.addLine(iscr);
        }
        System.out.println("DONE WITH INTERACTION");
        return interaction; 
        
//      System.out.println("\nREADING INTERACTION: \n\n"); 
//      for(String iscr : interactionScript) System.out.println(iscr);
//      System.out.println("\n\n"); 
    }
    
    private Choice writeChoice(List<String> choiceScript){
        //System.out.println("CHECK");
        Choice choice = new Choice(); 
        String cscr = choiceScript.get(1);
        for(int i = 1; !choiceScript.get(i).equals("END_CHOICE");){
            if(cscr.startsWith("CHOICE")){
                int firstQuote = cscr.indexOf("\"");
                int lastQuote = cscr.lastIndexOf("\"");
                String choiceLabel = cscr.substring(firstQuote + 1, lastQuote);
                System.out.println("CHOICELABEL: " + choiceLabel); // TERMINAL CHECK 
                ArrayList<String> choiceLines = new ArrayList<>();
                cscr = choiceScript.get(++i);
                for(; !cscr.startsWith("CHOICE") && i<choiceScript.size()-1; ){
                    //System.out.println(cscr);
                    choiceLines.add(cscr);
                    cscr = choiceScript.get(++i); 
                }
                choice.addChoicePath(choiceLabel, writeInteraction(choiceLines)); 
            }
            cscr = choiceScript.get(i);
        } 
        System.out.println("Done");// TERMINAL CHECK 
        return choice; 
    }
    
    private CallShift assignCallShift(){
        System.out.println("Assigning callers...");
        CallShift callshift = new CallShift(3); 
        Random random = new Random(); 
        for(int i = 0; i<3; ){ // 3 kay 3 ra max calls for now
            Caller caller = callers.get(random.nextInt(callers.size()));
            Call call = caller.getCall(); 
            if (call != null){ 
                callshift.addCall(call); i++; 
                System.out.println("Assigned " + call.getCaller().getCallerName());
            }
        }
        
        return callshift; 
    } 
    
    //1. makes calls first
    //2. assigns calls to callshift after
    
    private void writeCallShift(String callshiftScript) throws FileNotFoundException{
        ArrayList<String> encodedCallShift = convertToArrayList(callshiftScript);
        int open = -1, close = -1;
        for(int i = 0; i<encodedCallShift.size(); i++){
            String str = encodedCallShift.get(i);
            if(str.contains("NEW_CALLER")){ 
                System.out.println("CREATING CALLER -------------------");
                System.out.println(str);
                open = i;
            }
            else if (str.contains("END_CALLER")){
                System.out.println(str);
                System.out.println("CLOSING CALLER -------------------");
                close = i;
            }
            if (open != -1 && close != -1){
                callers.add(writeCaller(encodedCallShift.subList(open, close))); 
                open = close = -1;
                System.out.println("RESET VALUES from CALLER\nopen: " + open + " close: " + close + "\n\n");
            }
        }
    }
    
    private Caller writeCaller(List<String> callerScript){
        Caller caller = new Caller(callerScript.get(1)); 
        System.out.println("MAKING CALLER RIGHT NOW: " + caller.getCallerName() + "\n\n");
        int open = -1, close = -1;
        for(int i = 0; i<callerScript.size(); i++){
            String str = callerScript.get(i);
            if(str.contains("NEW_CALLSESSION")){ 
                System.out.println("CREATING CALLSESSION-------------------");
                System.out.println(str);
                open = i;
            }
            else if (str.contains("END_CALLSESSION")){
                System.out.println(str);
                System.out.println("CLOSING CALLSESSION-------------------");
                close = i;
            }
            if (open != -1 && close != -1){
                System.out.println("CURRENT VALUES\nopen: " + open + " close: " + close + "\n");
                caller.addCall(writeCall(callerScript.subList(open, close))); 
                open = close = -1;
                System.out.println("RESET VALUES\nopen: " + open + " close: " + close + "\n\n");
            }
        }
        return caller;
    }
    
    private Call writeCall(List<String> callScript){
        System.out.println("\nWRITING CALL");
        System.out.println("CONV1 index: " + callScript.indexOf("CONVERSATION_1"));
        System.out.println("CONV2 index: " + callScript.indexOf("CONVERSATION_2"));
        System.out.println("Size index: " + callScript.size());
        
        Call call = new Call(callScript.get(1));
        call.addConversation(0, writeCallConversation(callScript.subList(callScript.indexOf("CONVERSATION_1"), callScript.indexOf("CONVERSATION_2"))));
        call.addConversation(1, writeCallConversation(callScript.subList(callScript.indexOf("CONVERSATION_2"), callScript.size())));
        System.out.println("CLOSE CALL\n");
        return call;
    }
    
    private Conversation writeCallConversation(List<String> convScript){
        System.out.println("\nREADING CONVERSATION");
        System.out.println(convScript.get(0).substring(convScript.get(0).lastIndexOf("_") + 1));
        Conversation conv = new Conversation(1);
        boolean flag = true;

        for (int i = 1; i < convScript.size() && flag; i++) {
            //System.out.println("1. " + convScript.get(i));

            if (convScript.get(i).contains("OPTIONS")) {
                i++; 
                while (i < convScript.size()) {
                    String currentLine = convScript.get(i);
                    if (currentLine.startsWith("OP_")) {
                        String[] parts = currentLine.split("_", 3);
                        String optionType = parts[1];  // "NOANSWER"
                        String optionText = parts.length > 2 ? parts[2] : "";  // Handle NOANSWER case
                        CallDialogue cd = new CallDialogue(optionText);
                        i++;
                        while (i < convScript.size()) {
                            String dl = convScript.get(i);
                            if (dl.equals("CONTINUE")) { cd.setContinueFlag(true); i++; break; }
                            else if (dl.startsWith("OP_")) { break; }
                            else { cd.addDialougeLine(dl); i++; }
                        }
                        conv.setOptionDialogue(optionType, cd);
                    }
                    else { i++; }
                    // Not an OP_ line, move forward
                } flag = false;
            } else { conv.addOpeningDialogue(convScript.get(i)); }
        }
        
        System.out.println("CLOSE CONVERSATION\n");
        return conv;
    }

}

