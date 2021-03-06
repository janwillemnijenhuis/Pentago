package ss.player;

import ss.Tuple3;
import ss.board.Board;
import ss.Marble;
import ss.utils.TextIO;

import java.io.*;
import java.util.InputMismatchException;
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
    public Tuple3<Integer, Integer, Character> determineMove(Board board) {
        //var in = System.in;
        var out = System.out;
        //Scanner sc = new Scanner(in);
        String prompt = "> Player " + getName() + " (" + getMarble() +"): What is your choice? ";
        int choice = 0; int quadNumber = 0; char direction = 0;
        do {
            out.println(prompt);
            choice = TextIO.getInt();
            out.println("And which quadrant do you want to move?");
            quadNumber = TextIO.getInt();
            out.println("With the clock (w) or against the clock (a)?");
            direction = TextIO.getChar();
            out.println("Not a valid input, please try again");
        } while (!(board.isField(choice) && board.isEmpty(choice) && board.isQuad(quadNumber) && board.isDir(direction)));
        return new Tuple3<>(choice, quadNumber, direction);
    }
}
