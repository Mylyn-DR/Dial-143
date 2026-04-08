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
public class Caller {
    private String callerName; 
    private ArrayList<Call> calls; 
    public Caller(String callerName){
        this.callerName = callerName; 
        calls = new ArrayList<>();
        orderOfCall = 0;
    }
    public void addCall(Call call){ call.setCaller(this); calls.add(call); }
    public String getCallerName(){ return callerName; }
    private int orderOfCall; 
    public Call getCall(){ 
        if(orderOfCall<calls.size()){ return calls.get(orderOfCall++); }
        else return null; 
    } 
    public void readAllCalls(){ 
        for(Call c: calls){ 
            System.out.println("Order of call: " + calls.indexOf(c));
            c.readConversation(); 
            System.out.println("\n------------\n");
        } 
    }
}
