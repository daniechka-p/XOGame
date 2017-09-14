package danbka.xogame.logic.players;

import danbka.xogame.logic.Mark;

public abstract class Player{
    private Mark mark;
    private Type type;

    protected Player(Mark mark) {
        setMark(mark);
    }

    public Mark getMark() {
        return mark;
    }
    protected void setMark(Mark mark) {
        this.mark = mark;
    }
    public Type getType() {
        return type;
    }
    protected void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Player " + mark;
    }

    public abstract int[] doMove();

    public enum Type {AI, PERSON}

}
