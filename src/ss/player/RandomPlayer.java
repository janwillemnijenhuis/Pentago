package ss.player;

import ss.board.Board;
import ss.Marble;
import ss.strategy.Strategy;

public class RandomPlayer extends Player{

    private Strategy strategy;
    /**
     * creates a player with given name and marble
     *
     * @param name   given name
     * @param marble given marble
     */
    public RandomPlayer(String name, Marble marble) {
        super(name, marble);
    }

    /**
     * determines a move for the plyer
     *
     * @param board the board the player plays in
     * @return the index of the field
     */
    @Override
    public int determineMove(Board board) {
        return 0;
    }
}
