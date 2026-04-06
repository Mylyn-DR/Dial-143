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
public class Day {
   ArrayList<Event> events; 
   public Day(){ events = new ArrayList<>(); }
   public void addEvent(Event event){ events.add(event); } 
   public Event getEvent(int idx){ return events.get(idx); }
   public void play(){ for(Event event: events) event.playEvent(); }
}
