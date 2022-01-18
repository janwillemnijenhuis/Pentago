package ss.client;

import ss.utils.TextIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements ClientPlayer, Runnable {
    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private PrintWriter pw = new PrintWriter(System.out, true);
    private Thread t;
    private boolean run = true;

    public static void main(String[] args) {
        InetAddress address = null;
        int port = 0;
        Client client = new Client();

        do {
            System.out.println("Please provide a valid server address:");
            try {
                address = InetAddress.getByName(TextIO.getlnString());
            } catch (UnknownHostException e) {
                System.out.println("Not a valid server address");
                continue;
            }
            System.out.println("Please provide a valid port number:");
            port = TextIO.getlnInt();
        } while (!client.connect(address, port));

    }

    @Override
    public void run() {
        String line = null;
        while (this.run) {
            try {
                line = in.readLine();
                if (line == null) {
                    this.close();
                } else if (line.contains("What is your name:")) {
                    this.pw.println(line);
                    sendUsername(this.br.readLine());
                } else if (line.contains("?")) {
                    this.pw.println(line);
                    this.out.println(this.br.readLine());
                } else {
                    this.pw.println(line);
                }
            } catch (SocketTimeoutException | SocketException e) {
                // give time to set run to false
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean connect(InetAddress address, int port) {
        try {
            this.sock = new Socket(address, port);
            this.sock.setSoTimeout(1000);
            this.in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.out = new PrintWriter(this.sock.getOutputStream(), true);
            this.t = new Thread(this);
            t.start();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public void sendUsername(String username) {
        this.out.println(username);
    }

    @Override
    public boolean makeMove() {
        return false;
    }
}
