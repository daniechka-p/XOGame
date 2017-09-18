package danbka.xogame.gui;

import java.awt.*;

public class Gui {
    public final static Color   X_COLOR =           Color.green;
    public final static Color   O_COLOR =           Color.magenta;
    public final static Color   GRID_COLOR =        Color.white;
    public final static Color   BACK_COLOR =        Color.darkGray;

    public final static int     FIELD_SIZE =        400;
    public final static int     FIGURE_PEN_WIDTH =  20;
    public final static int     GRID_PEN_WIDTH =    17;
    public final static int     CELL_SIZE =         FIELD_SIZE  / 3;
    public final static int     CELL_INSIDE_SIZE =  (FIELD_SIZE - 2 * GRID_PEN_WIDTH) / 3;
    public final static int     CELL_FIGURE_PAD =   8 + FIGURE_PEN_WIDTH / 2;
    public final static int     FIGURE_SIZE =       CELL_INSIDE_SIZE - CELL_FIGURE_PAD * 2;
    public final static int[]   REAL_CELL_POSS;
    static {
        REAL_CELL_POSS = new int[3];
        for (int i = 0; i < 3; i++) {
            REAL_CELL_POSS[i] = i * CELL_SIZE;
        }
    }
}
