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
import Classes.DialogueLine; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Stack; 

public class GameScript {
    private String GameFileScript = "src/Scripts/GAMEFILESCRIPT.txt";
    private ArrayList<Day> days; 
    
    public GameScript(){
        try {
            initialize(); 
            buildGameScript(GameFileScript);
        } catch (FileNotFoundException ex) {
            System.getLogger(GameScript.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void readScript(){
        for(Day day: days){ day.play(); }
    }
    
    // GETTERS
    public Day getDay(int idx){ return days.get(idx); }
    
    
    // SETTERS - ALL BUILT FOR WRITING THE ENTIRE GAME....
    private void initialize(){
        days = new ArrayList<>(); 
    }

    private void buildGameScript(String script) throws FileNotFoundException{
        File filescript = new File(script);
        Scanner sc = new Scanner(filescript);
        while(sc.hasNextLine()){
           String txt = sc.nextLine();
           if (txt.contains("DAY")) writeDay("src/Scripts/"+txt); 
        }
        sc.close();
    }
    
    private void writeDay(String dayScript) throws FileNotFoundException{
        System.out.println("Writing " + dayScript); 
        File dayFile = new File(dayScript);
        Scanner dSC = new Scanner(dayFile);
        Day day = new Day(); 
        ArrayList<String> dayStr = new ArrayList<>(); 
        while(dSC.hasNextLine()){ 
            String txt = dSC.nextLine(); 
            if(!txt.isBlank()) dayStr.add(txt); 
        }
        dSC.close(); 
        
        int open =-1 , close = -1; //opening and closing indexes for making sublists
        for(int i = 0; i<dayStr.size(); i++){
            String str = dayStr.get(i);
            if(str.equals("NEW_INTERACTION") || str.equals("NEW_CALLSHIFT")){ 
                System.out.println("SETTING OPEN -------------------");
                System.out.println(str);
                open = i;
            }
            else if (str.equals("END_INTERACTION") || str.equals("END_CALLSHIFT")){
                System.out.println("SETTING CLOSE -------------------");
                System.out.println(str);
                close = i;
            }
            
            if (open != -1 && close != -1){
                Event event = writeEvent(dayStr.subList(open, close)); 
                day.addEvent(event);
                open = -1; close = -1;
                System.out.println("RESET VALUES\n\nopen: " + open + " close: " + close + "\n\n");
            }
        }
        
        if(days.add(day)) System.out.println("Day added successfully");
    }
    
    private Event writeEvent(List<String> eventScript){
        if (eventScript.get(0).contains("INTERACTION")){
            return writeInteraction(eventScript);
        }
        else if (eventScript.get(0).contains("CALLSHIFT")){
            return writeCallShift(eventScript.get(1));
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
            if(iscr.equals("NEW_INTERACTION") || iscr.equals("END_INTERACTION")) continue;
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
            else interaction = interaction.addLine(iscr);
        }
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
                System.out.println("CHOICELABEL: " + choiceLabel);
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
        System.out.println("Done");
        return choice; 
    }
    
    private CallShift writeCallShift(String settings){
        System.out.println("\n\nyuh\n\n");
        return new CallShift(); 
    } 
}
