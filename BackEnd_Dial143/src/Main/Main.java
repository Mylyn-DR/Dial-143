/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import GUI_Packages.GUI_MainFrame;
import java.awt.EventQueue;
import java.util.Scanner; 
/**
 *
 * @author Dell
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hello world");
        GameScript gs = new GameScript();
        System.out.println("\n\n\n\n\n\n\n\n\n");
        gs.getCallShift(0).playEvent();
        //gs.readScript();
        //gs.readCallShift(); 
//        System.out.print("Select a route to read: AMAYA\nCELERES\nCLOMA\nROSARIO\nEnter in capital letters: ");
//        Scanner sc = new Scanner(System.in);
//        gs.readRoute(sc.nextLine());
//        sc.close(); 
        //EventQueue.invokeLater(() -> {
        //    GUI_MainFrame mf = new GUI_MainFrame(null); 
        //    mf.setVisible(true);
        //}); 
    }
}
