package ss;

import ss.player.HumanPlayer;
import ss.player.Player;
import ss.player.RandomPlayer;
import ss.utils.TextIO;

public class Pentago {
    /**
     * starts the game
     * @param args the name of the players
     */
    public static void main(String[] args) {
        Player[] players = new Player[2];

        Marble[] marbles = {Marble.BLACK, Marble.WHITE};
        //String[] names = checkArgs(args);

        players[0] = new HumanPlayer("Player 1", marbles[0]);
        players[1] = new HumanPlayer("Player 2", marbles[1]);

//        players[0] = new RandomPlayer("1",marbles[0]);
//        players[1] = new RandomPlayer("2",marbles[1]);

        Game game = new Game(players[0], players[1]);
        game.start();
    }

    public static String[] checkArgs(String[] args) {
        String[] names = new String[2];
        if (args.length >= 2) {
            names[0] = args[0];
            names[1] = args[1];
        } else if (args.length == 1) {
            names[0] = args[0];
            System.out.println("Please input the name of the second player: ");
            names[1] = TextIO.getlnString();
        } else {
            System.out.println("Please input the name of the first player: ");
            names[0] = TextIO.getlnString();
            System.out.println("Please input the name of the second player: ");
            names[1] = TextIO.getlnString();
        }
        return names;
    }
}
