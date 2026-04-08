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
public class Ending{
    private HashMap<String, Event> endings;
    public Ending(){ 
        endings = new HashMap<>(); 
        endings.put("GOOD", null);
        endings.put("NEUTRAL", null);
        endings.put("BAD", null);
    }
    public void setEnding(String endingType, Event endingEvent){ endings.put(endingType, endingEvent); } 
    public Event getEnding(String ending){ return endings.get(ending); }
    public void playAllEndings(){ for(String key : endings.keySet()) endings.get(key).playEvent(); }
    public void play(String ending){ getEnding(ending).playEvent(); }
}
