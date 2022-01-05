package ss.strategy;

import ss.board.Board;
import ss.Marble;

/**
 * smart strategy, that contains levels of difficulty and AI
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class SmartStrategy implements Strategy{
    /**
     * gets the name of the strategy
     * maybe add the levels of difficulty to this if they are implemented
     * @return name of strategy
     */
    @Override
    public String getName() {
        return "Smart";
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
