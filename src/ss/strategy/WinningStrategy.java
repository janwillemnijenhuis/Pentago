package ss.strategy;

import ss.Marble;
import ss.Tuple3;
import ss.board.Board;

/**
 * strategy that checks if there is a winning move, random otherwise
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class WinningStrategy implements Strategy{
    /**
     * get the name of the strategy
     * @return the name
     */
    @Override
    public String getName() {
        return "Winning";
    }

    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board, Marble marble) {
        return null;
    }

    /**
     * winning move if possible, otherwise random
     * @param board the board it plays in
     * @param marble the marbe it plays with
     * @return
     */

}
