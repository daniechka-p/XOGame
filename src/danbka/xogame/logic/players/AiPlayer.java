package danbka.xogame.logic.players;

import danbka.xogame.logic.Game;
import danbka.xogame.logic.Mark;

import java.util.Random;

public class AiPlayer extends Player{

    public AiPlayer(Mark mark) {
        super(mark);
        setType(PlayerType.AI);
    }

    @Override
    public int[] move(Game game) {
        return intelligentMove(game);
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

        return null;
    }
}
