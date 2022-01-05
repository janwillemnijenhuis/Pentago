package ss.player;

import ss.board.Board;
import ss.Marble;

import java.io.*;
import java.util.Scanner;

/**
 * HumanPlayer for the Pentago Software Systems UT project
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class HumanPlayer extends Player {
    /**
     * creates a player with given name and marble
     *
     * @param name   given name
     * @param marble given marble
     */
    public HumanPlayer(String name, Marble marble) {
        super(name, marble);
    }

    /**
     * determines a move for the player
     *
     * @param board the board the player plays in
     * @return the index of the field, -1 if this is an invalid move
     */
    @Override
    public int determineMove(Board board, Reader in, PrintStream out) {
        Scanner sc = new Scanner(in);
        String prompt = "> Player " + getName() + " (" + getMarble() +"): What is your choice? ";
        out.println(prompt);
        int choice = sc.nextInt();
        boolean valid = board.isField(choice) && board.isEmpty(choice);
        while (!valid) {
            out.println("ERROR: Invalid number - Field is occupied or move is not on the board\n" + prompt);
            choice = sc.nextInt();
            valid = board.isField(choice) && board.isEmpty(choice);
        }
        return choice;
    }
}
