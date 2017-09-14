package danbka.xogame.logic.players;

import danbka.xogame.logic.Mark;

public class NonPersonPlayer extends Player{

    public NonPersonPlayer(Mark mark) {
        super(mark);
        setType(PlayerType.AI);
    }

    @Override
    public int[] doMove() {
        return new int[]{1,1};
    }
}
