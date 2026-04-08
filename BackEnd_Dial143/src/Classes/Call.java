/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Dell
 */
public class Call {
    private String difficulty;
    private boolean hasCalled;
    private Caller caller; 
    private Conversation[] conversations; 
    public Call(String diff)
    { difficulty = diff; 
      conversations = new Conversation[2]; 
      hasCalled = false; 
      caller = null; 
    }
    public void addConversation(int idx, Conversation conv)
    { conversations[idx] = conv; }
    public void readConversation(){
        conversations[0].read();
        conversations[1].read();
    }
    public void setCaller(Caller caller){ this.caller = caller; }
    public Caller getCaller(){ return caller; }
}
