package danbka.xogame.logic.players;

import danbka.xogame.logic.Mark;

public class PersonPlayer extends Player{

    public PersonPlayer(Mark mark) {
        super(mark);
        setType(PlayerType.PERSON);
    }

    @Override
    public int[] doMove() {
        return null;
    }

    private int[] getPosFromGui(int x, int y) {
        return new int[] {x, y};
    }
}
