package ss.strategy;

import ss.Tuple3;
import ss.board.Board;
import ss.Marble;
import java.lang.Math;

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
     * @param marble the marble it plays with
     * @return index of the field
     */
    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board, Marble marble) {
        int choice; int quadNumber; char direction;
        do {
            choice = (int) (Math.random() * 36);
            quadNumber = (int) (Math.random() * 4);
            if (Math.random() < 0.5) {
                direction = 'w';
            } else {
                direction = 'a';
            }
        } while (!board.isEmpty(choice));
        return new Tuple3<>(choice, quadNumber, direction);
    }

}
