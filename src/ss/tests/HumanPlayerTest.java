package ss.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.Marble;
import ss.board.Board;
import ss.player.HumanPlayer;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Human Player class
 * @author janwillem.nijenhuis
 * @version 0.1.0
 */
public class HumanPlayerTest {
    HumanPlayer humanPlayerBlack;
    HumanPlayer humanPlayerWhite;
    Board board;

    @BeforeEach
    public void setUp() {
        humanPlayerBlack = new HumanPlayer("TestPlayerBlack", Marble.BLACK);
        humanPlayerWhite = new HumanPlayer("TestPlayerWhite", Marble.WHITE);
        board = new Board();
    }

    @Test
    public void testGetName() {
        assertEquals("TestPlayerBlack", humanPlayerBlack.getName());
        assertEquals("TestPlayerWhite", humanPlayerWhite.getName());
    }

    @Test
    public void testGetMarble() {
        assertEquals(Marble.BLACK, humanPlayerBlack.getMarble());
        assertEquals(Marble.WHITE, humanPlayerWhite.getMarble());
    }

    @Test
    public void marbleCannotBeEmpty() {
        boolean assertionThrown = false;
        String eMessage = null;
        try {
            HumanPlayer humanPlayerEmpty = new HumanPlayer("TestPlayerEmtpy", Marble.EMPTY);
        } catch (IllegalArgumentException e) {
            assertionThrown = true;
            eMessage = e.getMessage();
        }
        assertTrue(assertionThrown);
        assertTrue(eMessage.contains("Empty marble not allowed for Player"));
    }

    @Test
    public void makeMoveChangesBoard() {
        // this should work now, but wait for board to be implemented
        board.reset();
        humanPlayerBlack.makeMove(board, 5);
        humanPlayerWhite.makeMove(board, 6);
        assertEquals(Marble.BLACK, board.getField(5));
        assertEquals(Marble.WHITE, board.getField(6));
    }

    @Test
    public void testDetermineMoveCorrectInput() throws IOException {
        // this should work now, but wait for board to be implemented
        board.reset();
        var ow = new PrintStream(System.out, true);
        var pw1 = new PipedWriter();
        var pr1 = new PipedReader(pw1);
        var pw = new PrintWriter(pw1);
        pw.write("5\n");
        pw.close();
        int number = humanPlayerBlack.determineMove(board, pr1, ow);
        assertEquals(5, number);
        assertEquals(Marble.BLACK, board.getField(5));
    }

    @Test
    public void testDetermineMoveIncorrectInput() throws IOException {
        // this should work now, but wait for board to be implemented
        board.reset();
        var po2 = new PipedOutputStream();
        var pi2 = new PipedInputStream(po2);
        var ow = new PrintStream(po2);
        var pw1 = new PipedWriter();
        var pr1 = new PipedReader(pw1);
        var pw = new PrintWriter(pw1);
        var br = new BufferedInputStream(pi2);
        pw.write("36\n");
        pw.write("3\n");
        pw.close();
        int number = humanPlayerBlack.determineMove(board, pr1, ow);
        String line = new String(br.readAllBytes());
        assertTrue(line.contains("ERROR"));
        assertEquals(Marble.BLACK, board.getField(3));
    }
}
