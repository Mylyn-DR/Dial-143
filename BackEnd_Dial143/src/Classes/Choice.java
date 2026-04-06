/*
 * Click nbfs://nbhost/SystemFileSystem/Interactionemplates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Interactionemplates/Classes/Class.java to edit this template
 */
package Classes;


/**
 * Some terminologies to help you understand the code:
 * Choice is a subclass of Interaction, an extension. 
 * It contains a list of Interactions called as choicePaths. 
 * ChoicePaths are different interactions that plays depending on what the player chooses.
 * Ex:
 *  Player chooses option A. 
 *  ChoicePath A will play.
 * 
 * ChoicePrompt vs ChoiceOption
 * ChoicePrompt refers to the main event. Basically the main question.
 * ChoiceLabel are the labels of each choices. 
 * Ex:
 *  Player: What should I do?  --- ChoicePrompt
 *  Choice A: Dance with him. --- ChoiceLabel
 *  Choice B: Run away. --- ChoiceLabel
 * 
 **/

import java.util.HashMap;
import java.util.Scanner; 

public class Choice{
    private String choicePrompt; 
    private Interaction interactionOrigin; // this is to help choices return to the main interaction
    private HashMap<String, Interaction> choicePaths; 
    
    // will clean this up later, but i think having different constructors will help me in the near future. 
    public Choice(){ choicePaths = new HashMap<>(); }
    public Choice(Interaction origin){ interactionOrigin = origin; this(); }
    public Choice(String choicePrompt, Interaction origin){ this.choicePrompt = choicePrompt; this(origin); }
    
    
    //GETTERS & SETTERS
    public void setChoicePrompt(String choicePrompt){ this.choicePrompt = choicePrompt; }
    public void setChoiceOrigin(Interaction origin){ interactionOrigin = origin; }
    public String getChoicePrompt(){ return choicePrompt; }
    public Interaction closeChoice(){ return interactionOrigin; }
    public String getChoiceID(String key){ return choicePaths.get(key).getInteractionID(); }
    
    //ADDING CHOICES
    public Interaction addChoicePath(String choiceLabel)
    { choicePaths.put(choiceLabel, new Interaction(choiceLabel, this)); return choicePaths.get(choiceLabel); }
    public void addChoicePath(String choiceLabel, Interaction choiceInteraction)
    {   choicePaths.put(choiceLabel, choiceInteraction); }
    
    
    //FOR DEBUGGING 
    public Interaction getChoicePath(String choiceLabel){ return choicePaths.get(choiceLabel); }
    public void readAllChoicePath(){
        System.out.println("CHOICE EVENT");
        int i = 0;
        for(String key : choicePaths.keySet()){
            System.out.println("CHOICE LABEL: " + key); 
            choicePaths.get(key).readInteraction(); 
        }
    }
    public void readChoicePath(){
        System.out.println(choicePrompt);
        int i = 0;
        for(String key : choicePaths.keySet()) System.out.println((i++) + " " + choicePaths.get(key)); 
        System.out.print("Pick a choice (enter the String of your choice): ");
        Scanner sc = new Scanner(System.in);
        choicePaths.get(sc.nextLine()).readInteraction();
        sc.close();
    }
    
    @Override
    public String toString(){ return choicePrompt; }
}
