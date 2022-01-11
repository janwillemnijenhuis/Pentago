package ss.player;

import ss.Tuple3;
import ss.board.Board;
import ss.Marble;
import ss.strategy.RandomStrategy;
import ss.strategy.Strategy;

import java.io.PrintStream;
import java.io.Reader;

/**
 * random player for the Pentago Software Systems UT project
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class RandomPlayer extends Player {

    private Strategy strategy;
    /**
     * creates a player with given name and marble
     *
     * @param name   given name
     * @param marble given marble
     */
    public RandomPlayer(String name, Marble marble) {
        super(name, marble);
        this.strategy = new RandomStrategy();
    }

    /**
     * should not be used in the context of a randomplayer
     */
    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board, Reader in, PrintStream out) {
        return new Tuple3<>(-1, -1, 'n');
    }

    /**
     * determines a move for the plyer
     *
     * @param board the board the player plays in
     * @return the index of the field, -1 if it is an invalid move
     */
    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board) {
        return strategy.determineMove(board, this.getMarble());
    }
}
