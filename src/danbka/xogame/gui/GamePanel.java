package danbka.xogame.gui;

import danbka.xogame.logic.Mark;
import danbka.xogame.logic.XOGame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    XOGame xoGame;
    Mark[][] field;
    BasicStroke pen;
    private int[] cellBorders;
    private int[] realCellBorders;
    private int secondLinePos;
    private int[] figurePoss;
    private int[] endXPoss;
    private int[] winLinePoss;

    public GamePanel(XOGame xoGame) {
        setPreferredSize(new Dimension(Gui.FIELD_SIZE, Gui.FIELD_SIZE));
        this.xoGame = xoGame;
        this.field = xoGame.field;
        pen = new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        cellBorders = new int[3];
        realCellBorders = new int[]{(Gui.CELL_SIZE + Gui.GRID_WIDTH / 2),
                (2 * Gui.CELL_SIZE + Gui.GRID_WIDTH),
                (3 * Gui.CELL_SIZE + 3 * Gui.GRID_WIDTH / 2)};
        figurePoss = new int[3];
        endXPoss = new int[3];
        winLinePoss = new int[3];
        for (int i = 0; i < 3; i++) {
            cellBorders[i] = i * Gui.CELL_SIZE + Gui.GRID_WIDTH;
            figurePoss[i] = i * Gui.CELL_N_GRID_SIZE + Gui.CELL_FIGURE_PAD;
            endXPoss[i] = figurePoss[i] + Gui.FIGURE_SIZE;
            winLinePoss[i] = i * Gui.CELL_N_GRID_SIZE + Gui.CELL_SIZE / 2;
        }
        secondLinePos = Gui.FIELD_SIZE - Gui.CELL_SIZE - Gui.GRID_WIDTH;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
//        g2.setStroke(pen);
        drawGrid(g2);
        drawMarks(g2);
        drawWinnerLine(g2);
    }

    private void drawGrid(Graphics2D g2) {
        g2.setColor(Gui.GRID_COLOR);
//        g2.drawLine(cellBorders[1], 0, cellBorders[1], Gui.FIELD_SIZE);
//        g2.drawLine(cellBorders[2], 0, cellBorders[2], Gui.FIELD_SIZE);
//        g2.drawLine(0, cellBorders[1], Gui.FIELD_SIZE, cellBorders[1]);
//        g2.drawLine(0, cellBorders[2], Gui.FIELD_SIZE, cellBorders[2]);
        g2.fillRoundRect(Gui.CELL_SIZE, 0, Gui.GRID_WIDTH, Gui.FIELD_SIZE, Gui.GRID_WIDTH, Gui.GRID_WIDTH);
        g2.fillRoundRect(secondLinePos, 0, Gui.GRID_WIDTH, Gui.FIELD_SIZE, Gui.GRID_WIDTH, Gui.GRID_WIDTH);
        g2.fillRoundRect(0, Gui.CELL_SIZE, Gui.FIELD_SIZE, Gui.GRID_WIDTH, Gui.GRID_WIDTH, Gui.GRID_WIDTH);
        g2.fillRoundRect(0, secondLinePos, Gui.FIELD_SIZE, Gui.GRID_WIDTH, Gui.GRID_WIDTH, Gui.GRID_WIDTH);
    }

    private void drawMarks(Graphics2D g2) {
        g2.setStroke(pen);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (field[i][j]) {
                    case X:
                        drawX(g2, i, j);
                        break;
                    case O:
                        drawO(g2, i, j);
                        break;
                }
            }
        }
    }

    private void drawO(Graphics2D g2, int line, int column) {
        g2.setColor(Gui.O_COLOR);
        g2.drawOval(figurePoss[column], figurePoss[line], Gui.FIGURE_SIZE, Gui.FIGURE_SIZE);
    }

    private void drawX(Graphics2D g2, int line, int column) {
        g2.setColor(Gui.X_COLOR);
        g2.drawLine(figurePoss[column], figurePoss[line], endXPoss[column], endXPoss[line]);
        g2.drawLine(figurePoss[column], endXPoss[line], endXPoss[column], figurePoss[line]);
    }

    private void drawWinnerLine(Graphics2D g2) {
        if (xoGame.getWinner() != null) {
            g2.setColor(Gui.GRID_COLOR);
            g2.setStroke(pen);
            g2.drawLine(winLinePoss[xoGame.getWinnerPos()[0]], winLinePoss[xoGame.getWinnerPos()[1]],
                    winLinePoss[xoGame.getWinnerPos()[2]], winLinePoss[xoGame.getWinnerPos()[3]]);
        }
    }

    public int searchPos(int x) {
        if (x < realCellBorders[0]) {
            return 0;
        } else if (x < realCellBorders[1]) {
            return 1;
        } else return 2;
    }
}