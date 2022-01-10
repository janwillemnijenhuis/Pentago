package ss.strategy;

import ss.Marble;
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

    /**
     * check if it can block losing, or make winning move, otherwise random
     * @param board the board it plays in
     * @param marble the marbe it plays with
     * @return
     */
    @Override
    public int determineMove(Board board, Marble marble) {
        // the strategy for the other player to make a winning move
        WinningStrategy otherWinning = new WinningStrategy();
        for (int i = 0; i < board.getDIM(); i++) {
            for (int j = 0; j < board.getDIM(); j++) {
                Board deepCopy = board.deepCopy();
                deepCopy.setField(i, j, marble);
                int otherMove = otherWinning.determineMove(deepCopy, marble.other());
                deepCopy.setField(otherMove, marble.other());
                if (deepCopy.isWinner(marble.other())) {
                    return board.getIndex(i, j);
                }
            }
        }
        // if there is no winning move, create a random strategy and return a random move.
        RandomStrategy randomStrategy = new RandomStrategy();
        return randomStrategy.determineMove(board, marble);
    }

}
