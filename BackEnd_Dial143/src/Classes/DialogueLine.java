/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Dell
 */
public class DialogueLine {
    private String speaker, expression, dialogue; 
    public DialogueLine(String speaker, String expression, String dialogue)
    { this.speaker = speaker; this.expression = expression; this.dialogue = dialogue; }
    public String getSpeaker(){ return speaker; }
    public String getExpression(){ return expression; }
    public String getDialogue(){ return dialogue; }
}
