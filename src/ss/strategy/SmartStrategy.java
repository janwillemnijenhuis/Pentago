package ss.strategy;

import ss.Tuple3;
import ss.board.Board;
import ss.Marble;

/**
 * smart strategy, that contains levels of difficulty
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class SmartStrategy implements Strategy {
    private int level;
    /**
     * gets the name of the strategy
     * maybe add the levels of difficulty to this if they are implemented
     * @return name of strategy
     */
    @Override
    public String getName() {
        return "Smart" + this.level;
    }

    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board, Marble marble) {
        return null;
    }

    public void setLevel(int level) {
        if (level <= 0) {
            this.level = 0;
        } else if (level >= 3) {
            this.level = 3;
        } else {
            this.level = level;
        }
    }

    public int getLevel() {
        return this.level;
    }

    /**
     * given board and marble it determines a move
     * level 0: random strategy
     * level 1: identify winning moves, random otherwise
     * level 2: identify winning and losing moves, random otherwise
     * level 3: AI (yet to be implemented)
     * @param board  the board it plays in
     * @param marble the marbe it plays with
     * @return index of the field
     */

}
