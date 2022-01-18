package ss;

import ss.board.Board;
import ss.player.Player;
import ss.utils.TextIO;

import java.io.*;

public class ServerGame implements Runnable {

    private final int numPlayers = 2;
    private Board board;
    private Player[] players;
    private int currentPlayer;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * constructs the game
     * @param player1
     * @param player2
     */
    public ServerGame(Player player1, Player player2, Reader in, Writer out){
        this.board = new Board();
        this.players = new Player[numPlayers];
        this.players[0] = player1;
        this.players[1] = player2;
        this.currentPlayer = 0;
        this.in = new BufferedReader(in);
        this.out = new PrintWriter(out, true);
    }

    /**
     * starts the game
     * calls all the other methods
     */
    public void run(){
        boolean continueGame = true;
        while (continueGame) {
            reset();
            play();
            this.out.println("\n> Play another time? (y/n)?");
            continueGame = TextIO.getBoolean();
        }
        this.out.println("Thank you for playing with us!");
    }

    /**
     * resets the game
     */
    private void reset(){
        this.currentPlayer = 0;
        this.board.reset();
    }

    /**
     * plays the game
     */
    private void play(){
        Tuple3<Integer, Integer, Character> move;
        this.update();
        int playerMove = 0;
        while (!this.board.hasWinner() && !this.board.isFull()) {
            move = players[playerMove].determineMove(board);
            this.board.setField(move.getFirst(), players[playerMove].getMarble());
            this.board.updateQuadrants();
            this.board.rotateQuadrant(move.getSecond(), move.getThird());
            this.board.updateFields();
            this.update();
            playerMove = (playerMove + 1) % 2;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printResult();
    }

    /**
     * prints the updated board
     */
    private void update(){
        this.out.println("\ncurrent game situation: \n" + this.board.toString()
                + "\n");
    }

    /**
     * prints result of last game
     */
    private void printResult(){
        if (board.hasWinner()) {
            Player winner = board.isWinner(players[0].getMarble()) ? players[0]
                    : players[1];
            this.out.println("Player " + winner.getName() + " ("
                    + winner.getMarble().toString() + ") has won!");
        } else {
            this.out.println("Draw. There is no winner!");
        }
    }
}
