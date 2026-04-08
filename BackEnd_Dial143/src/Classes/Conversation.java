/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Dell
 */
import java.util.HashMap; 
import java.util.ArrayList; 
public class Conversation { 
    private int convOrder;
    private ArrayList<DialogueLine> openingDialogues; 
    private HashMap<String, CallDialogue> optionDialogues; 
    public Conversation(int order){
        convOrder = order;
        openingDialogues = new ArrayList<>();
        optionDialogues = new HashMap<>(); 
        optionDialogues.put("BEST", null);
        optionDialogues.put("GOOD", null);
        optionDialogues.put("BAD", null);
        optionDialogues.put("NOANSWER", null);
    }
    public void addOpeningDialogue(String line){   
        String[] dl = line.split(": ");
        openingDialogues.add(new DialogueLine(dl[0], dl[1])); 
    }
    public void setOptionDialogue(String type, CallDialogue callDialogue)
    {   optionDialogues.put(type, callDialogue); }
    public int getConvOrder(){ return convOrder; }
    public void read(){
        if (openingDialogues != null){ 
            for(DialogueLine dl : openingDialogues)
               System.out.println(dl.getSpeaker() + ": " + dl.getDialogue()); 
        } 
        for(String cd : optionDialogues.keySet()){
            System.out.println(cd + "[" + optionDialogues.get(cd).getDesc() + "]"); 
            ArrayList<DialogueLine> dialogues = optionDialogues.get(cd).getDialogues(); 
            for(DialogueLine dl : dialogues){
                System.out.println(dl.getSpeaker() + ": " + dl.getDialogue()); 
            } 
        }
    }
}
