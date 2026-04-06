/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 * Terminologies/Explanation of the functions:
 * 
 * 
 */
import java.util.ArrayList;
public class Interaction extends Event{
    private ArrayList<DialogueLine> dialogueLines; 
    private ArrayList<String> locations;
    private ArrayList<String> systemEvents;
    private ArrayList<Integer> trackScript; 
    // easier way of tracking the situation inside the script
    // 1 - interaction, 2 - choice, 3 - location change, 4 - system (for GUI)
    private ArrayList<Choice> choices;
    private int tscrIndex; 
    
    public Interaction(){ this("MAIN", null); }
    public Interaction(String interactionTitle, Choice choiceOrigin)
    {   super(interactionTitle); 
        this.choiceOrigin = choiceOrigin;  
        prepareArrayLists(); 
    }
    
    private void prepareArrayLists(){
        dialogueLines = new ArrayList<>(); 
        locations = new ArrayList<>(); 
        choices = new ArrayList<>(); 
        systemEvents = new ArrayList<>();
        trackScript = new ArrayList<>(); 
        tscrIndex = 0; //<<--- use this for writiing the save file like 0-1-3 kaldjflkadjflkad
    }
    
    
    @Override //ABSTRACT FUNCTIONS FROM EVENT CLASS
    public void playEvent(){ readInteraction(); }
    //public int getNext(){ return -1; }
    
    //GETTERS
    public String getInteractionID(){ return super.getEventName(); } 
    public DialogueLine getDialogueLine(int idx){ return dialogueLines.get(idx); }
    public String getLocation(int idx){ return locations.get(idx); }
    public String getSystemEvent(int idx){ return systemEvents.get(idx); }
    public Choice getChoice(int idx){ return choices.get(idx); }
    private Choice choiceOrigin; // this is to help Interaction return to original choice
    public Choice closeChoicePath(){ return choiceOrigin; } // use only when Interaction is inside a Choice!
    
    
    //ADDING LINES TO INTERACTION  
    public Interaction addLine(String line){ 
        if (line.contains("SETTINGS")){
            trackScript.add(3);
            String[] parts = line.replace("SETTINGS[", "").replace("]", "").split(", ");
            super.setEventTime(parts[0]);
            locations.add(parts[1]);
        }
        else if (line.contains("SYSTEM")){
            trackScript.add(4);
            systemEvents.add(line.split(": ")[1]);
        }
        else {
            trackScript.add(1); 
            String speaker = line.split("\\(")[0];
            String expression = line.split("\\(")[1].split("\\)")[0];
            String dialogue = line.split("\\): ")[1];
            dialogueLines.add(new DialogueLine(speaker, expression, dialogue));
        }
        return this; }
    
    public Choice addChoice(String choicePrompt){ 
        choices.add(new Choice(choicePrompt, this)); 
        trackScript.add(2); 
        return choices.get(choices.size()-1); } // merged setter & geter, allows one to modify choice directly
    
    public void addChoice(Choice choice){
        trackScript.add(2);
        choice.setChoiceOrigin(this);
        choices.add(choice);
    }
    
    //FOR DEBUGGING
    protected void readTrackScript(){ System.out.println(trackScript); } 
    public void readInteraction(){
        int dialogueIndex = 0, choicesIndex = 0, locationIndex = 0, systemIndex = 0;
        //System.out.println(super.getEventName());
        for(Integer i : trackScript){
            switch(i){
                case 1: 
                    System.out.println(
                            dialogueLines.get(i).getSpeaker() + " ("  +
                            dialogueLines.get(i).getExpression() + "): "  + 
                            dialogueLines.get(i).getDialogue()
                            );
                    dialogueIndex++; 
                break;
                case 2:
                     getChoice(choicesIndex++).readAllChoicePath();
                break;
                case 3:
                    System.out.println("Location Change: " + getLocation(locationIndex++));
                break;
                case 4:
                    System.out.println("System: " + getSystemEvent(systemIndex++));
                break;
            }
        }
    }
}
