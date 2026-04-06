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

public abstract class Event {
    private String eventName; 
    private String eventLocation; 
    private String eventTime; 
    public Event(String name){ eventName = name; eventLocation = ""; eventTime = ""; }
    public String getEventName(){ return eventName; }
    public String getEventLocation(){ return eventLocation; } 
    public String getEventTime(){ return eventTime; }
    public void setEventName(String name){ eventName = name; }
    public void setEventTime(String time){ eventTime = time; } 
    public void setEventLocation(String location){ eventLocation = location; }
    public abstract void playEvent(); 
    //public abstract int getNext();
    @Override
    public String toString(){ return eventName; } 
}
