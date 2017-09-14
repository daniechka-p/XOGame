package danbka.xogame.gui;

import javax.swing.*;
import java.awt.*;

//TODO use JInternalFrames or JDesktopPane
public class SettingsWindow extends JFrame {

    public SettingsWindow(JFrame parent){
        super("Settings");

//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //TODO add some settings
        setResizable(false);
        setSize(200, 200);
//        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
    }
}
