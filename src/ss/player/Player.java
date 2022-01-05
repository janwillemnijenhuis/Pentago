package ss.player;

import ss.Marble;
import ss.board.Board;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

/**
 * abstract player class for the pentago UT Software Systems project
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public abstract class Player {

    private Marble marble;

    private String name;

    /**
     * creates a player with given name and marble
     * @param name given name
     * @param marble given marble
     */

    public Player (String name, Marble marble){
        this.name = name;
        if (marble.equals(Marble.EMPTY)) {
            throw new IllegalArgumentException("Empty marble not allowed for Player " + name);
        }
        this.marble = marble;
    }

    /**
     * gets name of player
     * @return name
     */

    public String getName(){
        return this.name;
    }

    /**
     * gets marble of player
     * @return marble
     */
    public Marble getMarble() {
        return this.marble;
    }

    /**
     * determines a move for the plyer
     * @param board the board the player plays in
     * @return the index of the field, -1 if it is an invalid move
     */
    public abstract int determineMove(Board board, Reader in, PrintStream out);

    /**
     * places the marble on the board
     * @param board the board the player plays in
     * @param index the move the player makes
     */
    public void makeMove(Board board, int index){
        board.setField(index, this.marble);
    }

}
