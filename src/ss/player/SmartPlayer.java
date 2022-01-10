package ss.player;

import ss.board.Board;
import ss.Marble;
import ss.strategy.SmartStrategy;
import ss.strategy.Strategy;

import java.io.PrintStream;
import java.io.Reader;

/**
 * smart player for the Pentago Software Systems UT project
 * this should get different levels of difficulty, and one to launch the AI
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class SmartPlayer extends Player{
    private int level;
    private SmartStrategy strategy;
    /**
     * creates a player with given name and marble
     *
     * @param name   given name
     * @param marble given marble
     */
    public SmartPlayer(String name, Marble marble, int level) {
        super(name, marble);
        this.strategy = new SmartStrategy();
        this.strategy.setLevel(level);
    }

    /**
     * should not be used for a smart player
     */
    @Override
    public int determineMove(Board board, Reader in, PrintStream out) {
        return -1;
    }

    /**
     * determines a move for the plyer
     *
     * @param board the board the player plays in
     * @return the index of the field, -1 if it is an invalid move
     */
    @Override
    public int determineMove(Board board) {
        return this.strategy.determineMove(board, this.getMarble());
    }
}
