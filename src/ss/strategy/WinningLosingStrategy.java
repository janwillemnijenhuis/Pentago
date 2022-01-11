package ss.strategy;

import ss.Marble;
import ss.Tuple3;
import ss.board.Board;

/**
 * make a winning move or block a losing move, random otherwise
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class WinningLosingStrategy implements Strategy {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board, Marble marble) {
        return null;
    }

    /**
     * check if it can block losing, or make winning move, otherwise random
     * @param board the board it plays in
     * @param marble the marbe it plays with
     * @return
     */


}
