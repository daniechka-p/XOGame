package danbka.xogame.logic.players;

import danbka.xogame.logic.Game;
import danbka.xogame.logic.Mark;

import java.util.ArrayList;
import java.util.Random;

public class AiPlayer extends Player{

    public AiPlayer(Mark mark) {
        super(mark);
        setType(PlayerType.AI);
    }

    @Override
    public int[] move(Game game) {
        return dumbTestMove(game);
    }

    private int[] dumbTestMove(Game game) {
        Random random = new Random();
        while (game.getWinner() == null) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);
            if (game.field[x][y] == null) {
                return new int[]{x, y};
            }
        }
        return null;
    }

    private int[] intelligentMove(Game game) {
        //trying to mark center cell
        if (game.getField()[1][1] == null) {
            return new int[]{1, 1};
        }
        //creating graph of available moves
        searchAvailableMoves(game)
        return null;
    }

    private int[][] searchAvailableMoves(Game game) {
        ArrayList<int[]> freeCells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getField()[i][j] == null) {
                    freeCells.add(new int[]{i, j});
                }
            }
        }
        return (int[][]) freeCells.toArray();
    }
}
