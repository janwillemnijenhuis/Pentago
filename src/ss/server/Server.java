package ss.server;

public interface Server {

    /**
     * start the server
     */
    void start();

    /**
     * returns the port of the server
     * @return port
     */
    int getPort();

    /**
     * terminates the server
     */
    void stop();
}
