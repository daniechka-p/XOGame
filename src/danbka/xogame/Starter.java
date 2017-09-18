package danbka.xogame;

import danbka.xogame.gui.MainWindow;

import javax.swing.*;

public class Starter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::getInstance);
    }
}
