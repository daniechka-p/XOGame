package danbka.xogame.logic;

import danbka.xogame.logic.players.*;

import java.util.Random;

public class XOGame {
    private Player xPlayer, oPlayer;
    private Player.Type xPlayerType, oPlayerType;
    private Player activePlayer;
    private int numberOfMoves;
    private State state;
    private Player winner;
    private int[] winnerPos;
    public Mark[][] field = {   //TODO initialize using loop
        {Mark.N, Mark.N, Mark.N},
        {Mark.N, Mark.N, Mark.N},
        {Mark.N, Mark.N, Mark.N}
    };

    public XOGame() {
        xPlayer = new PersonPlayer(Mark.X);
        oPlayer = new PersonPlayer(Mark.O);
        activePlayer = getRandomPlayer(xPlayer, oPlayer);
        numberOfMoves = 0;
        state = State.PLAYING;
    }

    public void play(PersonPlayer xPlayer, PersonPlayer oPlayer) {
        activePlayer = getRandomPlayer(xPlayer, oPlayer);
        //activePlayer.
    }

    private Player getRandomPlayer(Player xPlayer, Player oPlayer) {
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            return xPlayer;
        }
        return oPlayer;

    }

    public void doMove(int line, int column) {
        if (winner == null) {
            drawMark(line, column);
            numberOfMoves++;
            checkState();
            nextPlayer();
        }
    }

    private void drawMark(int line, int column) {
        field[line][column] = activePlayer.getMark();
    }

    private void nextPlayer() {
        if (activePlayer.equals(xPlayer)) {
            activePlayer = oPlayer;
        } else {
            activePlayer = xPlayer;
        }
    }

    public void checkState() {
        if (numberOfMoves >= 5) {
            if (checkWon()) {
                setState(State.WIN);
                setWinner(activePlayer);
            } else if (numberOfMoves > 8) {
                setState(State.DRAW);
            }
        }
    }

    public boolean checkWon() {
        if ((field[0][0] != Mark.N) && (field[0][0] == field[1][1] && field[1][1] == field[2][2])) {
            winnerPos = new int[]{0, 0, 2, 2};
            return true;
        }
        if ((field[0][2] != Mark.N) && (field[0][2] == field[1][1] && field[1][1] == field[2][0])) {
            winnerPos = new int[]{0, 2, 2, 0};
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (checkLine(i)) {
                winnerPos = new int[]{0, i, 2, i};
                return true;
            }
            if (checkColumn(i)) {
                winnerPos = new int[]{i, 0, i, 2};
                return true;
            }
        }
        return false;
    }
    private boolean checkLine(int line) {
        return ((field[line][0] != Mark.N) && (field[line][0] == field[line][1] && field[line][1] == field[line][2]));
    }
    private boolean checkColumn(int column) {
        return ((field[0][column] != Mark.N) && (field[0][column] == field[1][column] && field[1][column] == field[2][column]));
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public Player getWinner() {
        return winner;
    }

    public int[] getWinnerPos() {
        return winnerPos;
    }

    public enum State {PLAYING, DRAW, WIN}
}
