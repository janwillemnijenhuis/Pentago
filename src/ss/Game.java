package ss;

import ss.board.Board;
import ss.player.Player;
import ss.utils.TextIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Game {


    private final int numPlayers = 2;
    private Board board;
    private Player[] players;
    private int currentPlayer;

    /**
     * constructs the game
     * @param player1
     * @param player2
     */
    public Game(Player player1,Player player2){
        board = new Board();
        players = new Player[numPlayers];
        players[0] = player1;
        players[1] = player2;
        currentPlayer = 0;
    }

    /**
     * starts the game
     * calls all the other methods
     */
    public void start(){
        boolean continueGame = true;
        while (continueGame) {
            reset();
            play();
            System.out.println("\n> Play another time? (y/n)?");
            continueGame = TextIO.getBoolean();
        }
        System.out.println("Thank you for playing with us!");
    }

    /**
     * resets the game
     */
    private void reset(){
        currentPlayer = 0;
        board.reset();
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
            this.update();
            this.board.rotateQuadrant(move.getSecond(), move.getThird());
            this.board.updateFields();
            playerMove = (playerMove + 1) % 2;
            this.update();
        }
        printResult();
    }

    /**
     * prints the updated board
     */
    private void update(){
        System.out.println("\ncurrent game situation: \n\n" + board.toString()
                + "\n");
    }

    /**
     * prints result of last game
     */
    private void printResult(){
        if (board.hasWinner()) {
            Player winner = board.isWinner(players[0].getMarble()) ? players[0]
                    : players[1];
            System.out.println("Player " + winner.getName() + " ("
                    + winner.getMarble().toString() + ") has won!");
        } else {
            System.out.println("Draw. There is no winner!");
        }
    }

}
