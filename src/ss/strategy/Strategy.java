package ss.strategy;

import ss.Tuple3;
import ss.board.Board;
import ss.Marble;

/**
 * Strategy interface
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public interface Strategy {
    /**
     * gets the name of the strategy
     * @return name of strategy
     */
    String getName();

    /**
     * given board and marble it determines a move
     * @param board the board it plays in
     * @param marble the marbe it plays with
     * @return index of the field
     */
    Tuple3<Integer, Integer, Character> determineMove(Board board, Marble marble);

}
