/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Dell
 */
import java.util.ArrayList; 
public class CallDialogue {
    private String desc; 
    private boolean continueFlag; 
    private ArrayList<DialogueLine> dialogues; 
    public CallDialogue(String desc){ this.desc = desc; dialogues = new ArrayList<>(); }
    public void setContinueFlag(boolean flag){ continueFlag = flag; }
    public void addDialougeLine(String line){ 
        String[] dialogueLine = line.split(": ");
        dialogues.add(new DialogueLine(dialogueLine[0], dialogueLine[1]));
    } 
    public ArrayList<DialogueLine> getDialogues(){ return dialogues; }
    public String getDesc(){ return desc; }
}
