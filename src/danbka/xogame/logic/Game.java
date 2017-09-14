package danbka.xogame.logic;

import danbka.xogame.logic.players.*;

import java.util.Random;

public class Game {
    private Player[] players = new Player[2];
    private Player activePlayer;
    private int numberOfMoves;
    private State state = State.PLAYING;
    private Player winner;
    private int[] winnerPos;
    public Mark[][] field = new Mark[3][3];

    public Game(PlayerType player0Type, PlayerType player1Type) {
        players[0] = getPlayerWithType(player0Type);
        players[1] = getPlayerWithType(player1Type);
        activePlayer = getRandomPlayer();
    }
    private Player getPlayerWithType(PlayerType type) {
        switch (type) {
            case PERSON:
                return new PersonPlayer(setRandomMark());
            case AI:
                return new NonPersonPlayer(setRandomMark());
        }
        return null;
    }

    private Mark setRandomMark() {
        if (players[0] == null) {
            Random random = new Random();
            if (random.nextInt(2) == 0) {
                return Mark.O;
            }
            return Mark.X;
        }
        if (players[0].getMark() == Mark.O) {
            return Mark.X;
        }
        return Mark.O;
    }

    private Player getRandomPlayer() {
        Random random = new Random();
        return players[random.nextInt(2)];
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
        if (activePlayer.equals(players[0])) {
            activePlayer = players[1];
        } else {
            activePlayer = players[0];
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
        if ((field[0][0] != null) && (field[0][0] == field[1][1] && field[1][1] == field[2][2])) {
            winnerPos = new int[]{0, 0, 2, 2};
            return true;
        }
        if ((field[0][2] != null) && (field[0][2] == field[1][1] && field[1][1] == field[2][0])) {
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
        return ((field[line][0] != null) && (field[line][0] == field[line][1] && field[line][1] == field[line][2]));
    }
    private boolean checkColumn(int column) {
        return ((field[0][column] != null) && (field[0][column] == field[1][column] && field[1][column] == field[2][column]));
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
