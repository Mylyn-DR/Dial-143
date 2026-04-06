/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Dell
 */

import GUI_Packages.GUI_MainFrame;
import Classes.DialogueLine; 
import Classes.Event;

public class GameEngine {
    private final static GameScript mainScript = new GameScript();
    private GameState currentGameState;
    public GameEngine(){
        currentGameState = new GameState(); 
        GUI_MainFrame frame = new GUI_MainFrame(this);
        frame.setVisible(true);
    }
    
    //TEMPORARY STATIC VARIABLES
    private int currentDay = 0; 
    private int currentEvent = 0;
    private String currentEventID = "MAIN"; 
    //maguse ug string para sa interaction, like asa na dapit
    public void setGameState(){
       currentGameState.setCurrentDay(mainScript.getDay(currentDay));
       currentGameState.setCurrentEvent(currentEvent);
       currentGameState.setCurrentEventLine(currentEventID);
    }
}
