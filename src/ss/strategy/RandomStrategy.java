package ss.strategy;

import ss.board.Board;
import ss.Marble;

/**
 * random strategy to place a marble on the board
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class RandomStrategy implements Strategy{
    /**
     * gets the name of the strategy
     *
     * @return name of strategy
     */
    @Override
    public String getName() {
        return "Random";
    }

    /**
     * given board and marble it determines a move
     *
     * @param board  the board it plays in
     * @param marble the marbe it plays with
     * @return index of the field
     */
    @Override
    public int determineMove(Board board, Marble marble) {
        return 0;
    }
}
