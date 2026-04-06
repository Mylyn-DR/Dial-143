/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Dell
 */
import Classes.Event;
import Classes.Interaction; 
import Classes.CallShift; 
import Classes.Choice;
import Classes.Day; 
import Classes.DialogueLine; 

public class GameState{
    
    private Day currentDay; 
    private Event currentEvent;
    
    public GameState(){}
    public void setCurrentDay(Day day){ currentDay = day; }
    public void setCurrentEvent(int idx){ currentEvent = currentDay.getEvent(idx);}
    public void setCurrentEventLine(String line){ 
        
    }
}
