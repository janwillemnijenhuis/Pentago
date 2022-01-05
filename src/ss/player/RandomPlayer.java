package ss.player;

import ss.board.Board;
import ss.Marble;
import ss.strategy.Strategy;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public class RandomPlayer extends Player{

    private Strategy strategy;
    /**
     * creates a player with given name and marble
     *
     * @param name   given name
     * @param marble given marble
     */
    public RandomPlayer(String name, Marble marble) {
        super(name, marble);
    }

    /**
     * determines a move for the plyer
     *
     * @param board the board the player plays in
     * @return the index of the field, -1 if it is an invalid move
     */
    @Override
    public int determineMove(Board board, Reader in, PrintStream out) {
        return -1;
    }
}
