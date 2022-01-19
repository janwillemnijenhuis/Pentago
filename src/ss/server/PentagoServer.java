package ss.server;

import org.w3c.dom.Text;
import ss.utils.TextIO;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class PentagoServer implements Server, Runnable {
    private int PORT;
    private InetAddress address = null;
    private ServerSocket ss;
    private Thread t;
    private ArrayList<GameHandler> games = new ArrayList<>();
    private Socket bufSock = null;
    private boolean run = true;

    public static void main(String[] args) {
        String address = null;
        int port = 0;
        PentagoServer server = new PentagoServer();
        do {
            System.out.println("Please provide a valid IP address:");
            address = TextIO.getlnString();
            System.out.println("Please provide a valid port number:");
            port = TextIO.getlnInt();
        } while (!server.setServerAddress(address) || !server.setValidPort(port));
        server.start();
        boolean inGame = true;
        do {
            String line = TextIO.getlnString();
            if (line.equals("quit")) {
                server.stop();
                inGame = false;
            }
        } while (inGame);
    }

    public boolean setServerAddress(String address) {
        try {
            this.address = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            return false;
        }
        return true;
    }

    public boolean setValidPort(int port) {
        if (port >= 0 && port <= Math.pow(2, 16) - 1) {
            this.PORT = port;
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (this.run) {
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
            } catch (SocketException e) {
                System.out.println("Terminating server at port: " + this.getPort());
            } catch (IOException e) {
                e.printStackTrace();
                stop();
            }
        }
    }

    @Override
    public void start() {
        try {
            this.ss = new ServerSocket(this.PORT, 50, this.address);
            System.out.println("Starting server at IP " + this.ss.getInetAddress() + " and port: " + this.ss.getLocalPort());
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
            this.run = false;
            ss.close();
            t.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
