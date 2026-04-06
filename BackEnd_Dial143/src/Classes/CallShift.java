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
    public CallShift(String label){ super(label); calls = new ArrayList<>(5); }
    public CallShift(){ this(""); }
    
    @Override
    public void playEvent(){ System.out.println("This is a callshift event"); }
    //@Override
    //public int getNext(){ return -1; }
}
