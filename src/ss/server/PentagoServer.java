package ss.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PentagoServer implements Server, Runnable {
    private int PORT;
    private ServerSocket ss;
    private Thread t;
    private ArrayList<GameHandler> games = new ArrayList<>();
    private Socket bufSock = null;

    public PentagoServer(int port) {
        this.PORT = port;
    }

    public static void main(String[] args) {
        PentagoServer server = new PentagoServer(8080);
        server.start();
    }

    @Override
    public void run() {
        boolean run = true;
        while (run) {
            try {
                Socket sock = ss.accept();
                System.out.println("New player joined the game!");
                // if there is a player waiting start a game, otherwise let him wait in the buffer socket
                if (this.bufSock == null) {
                    this.bufSock = sock;
                } else {
                    var gh = new GameHandler(this.bufSock, sock, this);
                    this.bufSock = null;
                    this.games.add(gh);
                    System.out.println("New game started!");
                    new Thread(gh).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
                run = false;
            }
        }
    }

    @Override
    public void start() {
        try {
            this.ss = new ServerSocket(this.PORT);
            System.out.println("Starting server at port: " + this.ss.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.t = new Thread(this);
        t.start();
    }

    @Override
    public int getPort() {
        return this.PORT;
    }

    @Override
    public void stop() {
        try {
            ss.close();
            t.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
