package Main;

import GUI.panels.MainFrame;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameAPI gameAPI = new GameAPI();
            MainFrame frame = new MainFrame(gameAPI);
            frame.setVisible(true);
        });
    }
}