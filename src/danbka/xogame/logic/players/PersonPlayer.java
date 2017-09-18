package danbka.xogame.logic.players;

import danbka.xogame.logic.Game;
import danbka.xogame.logic.Mark;

public class PersonPlayer extends Player{

    public PersonPlayer(Mark mark) {
        super(mark);
        setType(PlayerType.PERSON);
    }

    @Override
    public int[] move(Game game) {
        return null;
    }
}
