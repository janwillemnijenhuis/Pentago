package ss.player;

import ss.Marble;
import ss.board.Board;

public abstract class Player {

    private Marble marble;

    private String name;

    /**
     * creates a player with given name and marble
     * @param name given name
     * @param marble given marble
     */

    public  Player (String name, Marble marble){

    }

    /**
     * gets name of player
     * @return name
     */

    public String getName(){
        return null;
    }

    /**
     * gets marble of player
     * @return marble
     */
    public Marble getMarble() {
        return null;
    }

    /**
     * determines a move for the plyer
     * @param board the board the player plays in
     * @return the index of the field
     */
    public abstract int determineMove(Board board);

    /**
     * places the marble on the board
     * @param board the board the player plays in
     */
    public void makeMove(Board board){

    }

}
