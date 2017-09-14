package danbka.xogame.logic.players;

import danbka.xogame.logic.Mark;

public abstract class Player{
    private Mark mark;
    private PlayerType type;

    protected Player(Mark mark) {
        setMark(mark);
    }

    public Mark getMark() {
        return mark;
    }
    protected void setMark(Mark mark) {
        this.mark = mark;
    }
    public PlayerType getType() {
        return type;
    }
    protected void setType(PlayerType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Player " + mark;
    }

    public abstract int[] doMove();

}
