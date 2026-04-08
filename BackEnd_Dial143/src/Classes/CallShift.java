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
public class CallShift extends Event{
    private ArrayList<Call> calls; 
    private int callCount, maxCalls; 
    public CallShift(int num){ 
        calls = new ArrayList<>(); 
        maxCalls = num;
        callCount = 0; super(""); 
    }
    public void addCall(Call call){ 
        System.out.println("callCount: " + callCount);
        System.out.println("maxCalls: " + maxCalls);
        if(callCount < maxCalls){ calls.add(call); callCount++; }
        else{ System.out.println("Cannot add more calls."); }
    }
    @Override
    public void playEvent(){ 
        for(Call call: calls){ 
            System.out.println(call.getCaller().getCallerName());
            call.readConversation(); 
        } 
    } 
}