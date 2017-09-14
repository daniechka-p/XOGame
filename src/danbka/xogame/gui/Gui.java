package danbka.xogame.gui;

import java.awt.*;

public class Gui {
    public final static Color   X_COLOR =           Color.green;
    public final static Color   O_COLOR =           Color.magenta;
    public final static Color   GRID_COLOR =        Color.white;
    public final static Color   BACK_COLOR =        Color.darkGray;

    public final static int     FIELD_SIZE =        400;
    public final static int     FIGURE_WIDTH =      18;
    public final static int     GRID_WIDTH =        17;
    public final static int     CELL_SIZE =         (FIELD_SIZE - 2 * GRID_WIDTH) / 3;
    public final static int     CELL_N_GRID_SIZE =  CELL_SIZE + GRID_WIDTH;
    public final static int     REAL_CELL_SIZE =    FIELD_SIZE  / 3;
    public final static int     CELL_FIGURE_PAD =   8 + FIGURE_WIDTH / 2;
    public final static int     FIGURE_SIZE =       CELL_SIZE - CELL_FIGURE_PAD * 2;


}
