package ss.server;

import ss.Game;
import ss.Marble;
import ss.ServerGame;
import ss.player.HumanPlayer;
import ss.player.Player;

import java.io.*;
import java.net.Socket;

public class GameHandler implements Runnable {
    private Server server;
    private Socket[] sockets = new Socket[2];
    private Player[] players = new Player[2];
    private PrintWriter[] out = new PrintWriter[2];
    private BufferedReader[] in = new BufferedReader[2];
    private BufferedReader br;
    private PrintWriter pw;
    private Marble[] marbles = { Marble.BLACK, Marble.WHITE };
    public boolean inGame = false;

    public GameHandler(Socket sock1, Socket sock2, Server server) {
        this.server = server;
        this.sockets[0] = sock1;
        this.sockets[1] = sock2;
        try {
            this.out[0] = new PrintWriter(sock1.getOutputStream(), true);
            this.out[1] = new PrintWriter(sock2.getOutputStream(), true);
            this.in[0] = new BufferedReader(new InputStreamReader(sock1.getInputStream()));
            this.in[1] = new BufferedReader(new InputStreamReader(sock2.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String line = null;
        try {
            collectPlayers();
            var pr1 = new PipedReader();
            var pw1 = new PipedWriter(pr1);
            this.br = new BufferedReader(pr1);
            ServerGame game = new ServerGame(this.players[0], this.players[1], this.br, new PrintWriter(System.out, true));
            game.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!((line = this.br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void collectPlayers() {
        String username;
        for (int i = 0; i < 2; i++) {
            try {
                this.out[i].println("What is your name:");
                username = this.in[i].readLine();
                this.players[i] = new HumanPlayer(username, this.marbles[i]);
                this.out[i].println("Welcome, " + username + ". You'll play with " + this.marbles[i].toString());
            } catch (IOException e) {
                closeGame();
            }
        }
    }

    public void startGame() {

    }

    public void closeGame() {
        try {
            this.sockets[0].close();
            this.sockets[1].close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
