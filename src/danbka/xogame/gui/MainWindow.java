package danbka.xogame.gui;

import danbka.xogame.logic.Mark;
import danbka.xogame.logic.Game;
import danbka.xogame.logic.players.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private GamePanel gamePanel;
    private BattleXOMouseListener mouseListener;
    private JTextField infoLine;
    private SettingsWindow settingsWindow;
    public Game xogame;
    //singleton instance
    public static MainWindow mainWindow = new MainWindow();

    //TODO using more singletons
    public static MainWindow getInstance() {
        return mainWindow;
    }

    private MainWindow() {
        super("XO-Game!");
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        setSize(550, 550);
        setBackground(Gui.BACK_COLOR);
        showMainPanel();
        showMenuBar();
        showInfoLine();
        showMainMenuPanel();
        setResizable(false);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
        mainPanel.setBackground(Gui.BACK_COLOR);
    }

    private void showMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        addMenuItem(gameMenu, "Main menu", event -> showMainMenuPanel());
        addMenuItem(gameMenu, "Restart", event -> restart());
        addMenuItem(gameMenu, "Settings", event -> openSettings());
        addMenuItem(gameMenu, "Quit", event -> quitGame());
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu parent, String title, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(listener);
        parent.add(menuItem);
    }

    private void openSettings() {
        if (settingsWindow == null) {
            settingsWindow = new SettingsWindow(this);
        }
        settingsWindow.setVisible(true);
        repaint();
    }

    private void quitGame() {
        System.exit(0);
    }

    private void showInfoLine() {
        infoLine = new JTextField("");
        infoLine.setBackground(Color.white);
        infoLine.setEditable(false);
        mainPanel.add(infoLine, BorderLayout.SOUTH);
    }

    public void showMainMenuPanel() {
        if (isGamePlaying()) {
            stopGame();
        }
        mainPanel.add(MainMenuPanel.getInstance(), BorderLayout.CENTER);
        repaint();
    }

    public void hideMainMenuPanel() {
        mainPanel.remove(MainMenuPanel.getInstance());
        repaint();
    }

    public void newGame(PlayerType player0Type, PlayerType player1Type) {
        if (gamePanel != null) {
            mainPanel.remove(gamePanel);
        }
        xogame = new Game(player0Type, player1Type);
        gamePanel = new GamePanel(xogame);
        mouseListener = new BattleXOMouseListener();
        gamePanel.addMouseListener(mouseListener);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        actualizeInfo();
        repaint();
        pack();
    }

    public void restart() {
        //TODO restart
    }

    private boolean isGamePlaying() {
        return gamePanel != null;
    }

    private void stopGame() {
        //TODO move methods to GamePanel
        mainPanel.remove(gamePanel);
    }

    public class BattleXOMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int line = gamePanel.searchPos(e.getY());
            int column = gamePanel.searchPos(e.getX());
            if (xogame.field[line][column] == null) {
                xogame.doMove(line, column);
                repaint();
                actualizeInfo();
            }
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    private void actualizeInfo() {
        switch (xogame.getState()) {
            case PLAYING:
                infoLine.setText(xogame.getActivePlayer() + " playing now.");
                break;
            case DRAW:
                infoLine.setText("It\'s a draw.");
                JOptionPane.showMessageDialog(mainPanel, "Game over! Draw!");
                gamePanel.removeMouseListener(mouseListener);
                break;
            case WIN:
                infoLine.setText(xogame.getWinner() + " is winner!");
                JOptionPane.showMessageDialog(mainPanel, xogame.getWinner() + " is winner!");
                gamePanel.removeMouseListener(mouseListener);
                break;
        }
    }
}