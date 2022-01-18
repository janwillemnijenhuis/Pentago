package ss.player;

import ss.Marble;
import ss.Tuple3;
import ss.board.Board;
import ss.utils.TextIO;

import java.io.*;

/**
 * HumanPlayer for the Pentago Software Systems UT project
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class ServerHumanPlayer extends Player {
    private BufferedReader br;
    private PrintWriter pw;
    /**
     * creates a player with given name and marble
     *
     * @param name   given name
     * @param marble given marble
     */
    public ServerHumanPlayer(String name, Marble marble, BufferedReader in, PrintWriter out) {
        super(name, marble);
        this.br = in;
        this.pw = out;
    }

    /**
     * determines a move for the player
     *
     * @param board the board the player plays in
     * @return the index of the field, -1 if this is an invalid move
     */
    @Override
    public Tuple3<Integer, Integer, Character> determineMove(Board board) {
        String prompt = "> Player " + getName() + " (" + getMarble() +"): What is your choice? ";
        int choice = -1; int quadNumber = -1; char direction = 0;
        do {
            try {
                this.pw.println(prompt);
                choice = Integer.parseInt(br.readLine());
                this.pw.println("And which quadrant do you want to move?");
                quadNumber = Integer.parseInt(br.readLine());
                this.pw.println("With the clock (w) or against the clock (a)?");
                direction = br.readLine().charAt(0);
            } catch (Exception e) {
                pw.println(e.getMessage());
            }
        } while (!(board.isField(choice) && board.isEmpty(choice) && board.isQuad(quadNumber) && board.isDir(direction)));
        return new Tuple3<>(choice, quadNumber, direction);
    }
}
