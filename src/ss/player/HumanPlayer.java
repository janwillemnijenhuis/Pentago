package ss.player;

import ss.Tuple3;
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
    public Tuple3<Integer, Integer, Character> determineMove(Board board, Reader in, PrintStream out) {
        Scanner sc = new Scanner(in);
        String prompt = "> Player " + getName() + " (" + getMarble() +"): What is your choice? ";
        int choice; int quadNumber; char direction;
        do {
            out.println(prompt);
            choice = sc.nextInt();
            out.println("And which quadrant do you want to move?");
            quadNumber = sc.nextInt();
            out.println("With the clock (w) or against the clock (a)?");
            direction = sc.next().charAt(0);
        } while (board.isField(choice) && board.isEmpty(choice) && board.isQuad(quadNumber) && board.isDir(direction));
        return new Tuple3<>(choice, quadNumber, direction);
    }

    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board) {
        return new Tuple3<>(-1, -1, 'n');
    }
}
