package danbka.xogame.gui;

import danbka.xogame.logic.Mark;
import danbka.xogame.logic.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Game xoGame;
    Mark[][] field;

    private static final BasicStroke FIGURE_PEN;
    private static final BasicStroke GRID_PEN;
    private static final BasicStroke WIN_LINE_PEN;
    private static final int GRID_PAD = 10;
    private static final int GRID_LENGTH = Gui.FIELD_SIZE - GRID_PAD;
    private static final int CELL_N_GRID_SIZE =  Gui.CELL_INSIDE_SIZE + Gui.GRID_PEN_WIDTH;
    private static final int[] FIGURE_POSS;
    private static final int[] END_X_POSS;
    private static final int[] WIN_LINE_POSS;

    static {
        FIGURE_PEN = new BasicStroke(Gui.FIGURE_PEN_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        GRID_PEN = new BasicStroke(Gui.GRID_PEN_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        WIN_LINE_PEN = new BasicStroke(Gui.GRID_PEN_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        FIGURE_POSS = new int[3];
        END_X_POSS = new int[3];
        WIN_LINE_POSS = new int[3];
        for (int i = 0; i < 3; i++) {
            FIGURE_POSS[i] = i * CELL_N_GRID_SIZE + Gui.CELL_FIGURE_PAD;
            END_X_POSS[i] = FIGURE_POSS[i] + Gui.FIGURE_SIZE;
            WIN_LINE_POSS[i] = i * Gui.CELL_SIZE + Gui.CELL_SIZE / 2;
        }
    }

    public GamePanel(Game xoGame) {
        setPreferredSize(new Dimension(Gui.FIELD_SIZE, Gui.FIELD_SIZE));
        this.xoGame = xoGame;
        this.field = xoGame.field;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
//        g2.setStroke(FIGURE_PEN);
        drawGrid(g2);
        drawMarks(g2);
        drawWinnerLine(g2);
    }

    private void drawGrid(Graphics2D g2) {
        g2.setColor(Gui.GRID_COLOR);
        g2.setStroke(GRID_PEN);
        //vertical lines
        g2.drawLine(Gui.REAL_CELL_POSS[1], GRID_PAD, Gui.REAL_CELL_POSS[1], GRID_LENGTH);
        g2.drawLine(Gui.REAL_CELL_POSS[2], GRID_PAD, Gui.REAL_CELL_POSS[2], GRID_LENGTH);
        //horizontal lines
        g2.drawLine(GRID_PAD, Gui.REAL_CELL_POSS[1], GRID_LENGTH, Gui.REAL_CELL_POSS[1]);
        g2.drawLine(GRID_PAD, Gui.REAL_CELL_POSS[2], GRID_LENGTH, Gui.REAL_CELL_POSS[2]);
    }

    private void drawMarks(Graphics2D g2) {
        g2.setStroke(FIGURE_PEN);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == null) {
                    //do nothing
                } else if (field[i][j] == Mark.X) {
                    drawX(g2, i, j);
                } else {
                    drawO(g2, i, j);
                }
            }
        }
    }

    private void drawO(Graphics2D g2, int line, int column) {
        g2.setColor(Gui.O_COLOR);
        g2.drawOval(FIGURE_POSS[column], FIGURE_POSS[line], Gui.FIGURE_SIZE, Gui.FIGURE_SIZE);
    }

    private void drawX(Graphics2D g2, int line, int column) {
        g2.setColor(Gui.X_COLOR);
        g2.drawLine(FIGURE_POSS[column], FIGURE_POSS[line], END_X_POSS[column], END_X_POSS[line]);
        g2.drawLine(FIGURE_POSS[column], END_X_POSS[line], END_X_POSS[column], FIGURE_POSS[line]);
    }

    private void drawWinnerLine(Graphics2D g2) {
        if (xoGame.getWinner() != null) {
            g2.setColor(Gui.GRID_COLOR);
            g2.setStroke(WIN_LINE_PEN);
            g2.drawLine(WIN_LINE_POSS[xoGame.getWinnerPos()[0]], WIN_LINE_POSS[xoGame.getWinnerPos()[1]],
                    WIN_LINE_POSS[xoGame.getWinnerPos()[2]], WIN_LINE_POSS[xoGame.getWinnerPos()[3]]);
        }
    }

    public int searchPos(int x) {
        if (x < Gui.REAL_CELL_POSS[1]) {
            return 0;
        } else if (x < Gui.REAL_CELL_POSS[2]) {
            return 1;
        } else return 2;
    }
}