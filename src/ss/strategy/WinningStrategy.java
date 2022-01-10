package ss.strategy;

import ss.Marble;
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

    /**
     * winning move if possible, otherwise random
     * @param board the board it plays in
     * @param marble the marbe it plays with
     * @return
     */
    @Override
    public int determineMove(Board board, Marble marble) {
        for (int i = 0; i < board.getDIM(); i++) {
            for (int j = 0; j < board.getDIM(); j++) {
                Board deepCopy = board.deepCopy();
                deepCopy.setField(i, j, marble);
                if (deepCopy.isWinner(marble)) {
                    return board.getIndex(i, j);
                }
            }
        }
        // if there is no winning move, create a random strategy and return a random move.
        RandomStrategy randomStrategy = new RandomStrategy();
        return randomStrategy.determineMove(board, marble);
    }
}
