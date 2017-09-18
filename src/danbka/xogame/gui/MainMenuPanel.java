package danbka.xogame.gui;

import danbka.xogame.logic.GameType;
import danbka.xogame.logic.players.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private Font titleFont = new Font("arial", Font.BOLD, 37);
    private Font buttonFont = new Font("arial", Font.BOLD, 20);
    private JLabel titleLabel = new JLabel("Play XO!");
    //singleton instance
    private static MainMenuPanel mainMenuPanel = new MainMenuPanel();

    public static MainMenuPanel getInstance() {
        return mainMenuPanel;
    }

    private MainMenuPanel() {
        setPreferredSize(new Dimension(Gui.FIELD_SIZE, Gui.FIELD_SIZE));
        setLayout(new GridLayout(5, 1, 50, 5));
        setBackground(Gui.BACK_COLOR);
        titleLabel.setForeground(Gui.X_COLOR);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);
        addButton("Person vs Person", event -> startPvpGame());
        addButton("Person vs Non-Person", event -> startPvnGame());
        addButton("Non-Person vs Non-Person", event -> startNvnGame());
        add(new JLabel(" "));
    }

    private void startPvpGame() {
        MainWindow.getInstance().hideMainMenuPanel();
        repaint();
        MainWindow.getInstance().newGame(GameType.PVP);
    }

    private void startPvnGame() {
        MainWindow.getInstance().hideMainMenuPanel();
        repaint();
        MainWindow.getInstance().newGame(GameType.PVN);
    }

    private void startNvnGame() {
        MainWindow.getInstance().hideMainMenuPanel();
        repaint();
        MainWindow.getInstance().newGame(GameType.NVN);
    }

    private void addButton(String title, ActionListener listener) {
        JButton button = new JButton(title);
        button.setBackground(Gui.GRID_COLOR);
        button.setForeground(Gui.O_COLOR);
        button.setFont(buttonFont);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(listener);
        add(button);
    }
}
