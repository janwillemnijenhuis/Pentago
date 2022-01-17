package ss.client;

import java.net.InetAddress;

public interface ClientPlayer {
    /**
     * Connect to the server
     * @param address
     * @param port
     * @return true if connected, false otherwise
     */
    boolean connect(InetAddress address, int port);

    /**
     * close the connection with the server
     */
    void close();

    /**
     * Send the username to the server
     * @param username
     * @return
     */
    void sendUsername(String username);

    /**
     * Asks the player to make a move
     * @return true if correct move, false otherwise
     */
    boolean makeMove();
}
