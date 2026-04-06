/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Dell
 */
public class TempMain {
    public static void main(String[] args) {
          System.out.println("Hello world");
//        printDayInteraction();
//        Event interaction = new Interaction(); 
//        Event workshift = new WorkShift(); 
//        checkEvent(interaction);
//        checkEvent(workshift);
//        
//        Event test1 = returnEvent(interaction);
//        Event test2 = returnEvent(workshift);
//        checkEvent(test1);
//        checkEvent(test2);
    }

    public static Event returnEvent(Event event){ return event; }
    
    public static void printDayInteraction() {
        Interaction testing = new Interaction();
        testing.setEventName("Intercton 1");
        testing.addLine("testing 1")
                .addLine("testing 2")
                .addLine("testing 3")
                .addChoice("Choice Description 1: ")
                    .addChoicePath("choice 1")
                        .addLine("1Inside choice 1")
                        .addLine("2Inside choice 1")
                    .closeChoicePath()
                    .addChoicePath("choice 2")
                        .addLine("1Inside choice 2")
                        .addLine("2Inside choice 2")
                    .closeChoicePath()
                .closeChoice()
                .addLine("testing 4");
        testing.playEvent();
    }
}
