//package ss.tests;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ss.Marble;
//import ss.board.Board;
//import ss.player.RandomPlayer;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RandomPlayerTest {
//    RandomPlayer randomPlayer;
//    Board board;
//
//    @BeforeEach
//    public void setUp() {
//        this.randomPlayer = new RandomPlayer("Player 1", Marble.BLACK);
//        this.board = new Board();
//    }
//
//    @Test
//    public void marbleCannotBeEmpty() {
//        boolean assertionThrown = false;
//        String eMessage = null;
//        try {
//            RandomPlayer humanPlayerEmpty = new RandomPlayer("TestPlayerEmtpy", Marble.EMPTY);
//        } catch (IllegalArgumentException e) {
//            assertionThrown = true;
//            eMessage = e.getMessage();
//        }
//        assertTrue(assertionThrown);
//        assertTrue(eMessage.contains("Empty marble not allowed for Player"));
//    }
//
//    @Test
//    public void testGetName() {
//        assertEquals("Player 1", randomPlayer.getName());
//    }
//
//    @Test
//    public void testGetMarble() {
//        assertEquals(Marble.BLACK, randomPlayer.getMarble());
//    }
//
//    @Test
//    public void testMoveIsValidInt() {
//        int move = randomPlayer.determineMove(board);
//        boolean valid = move < 36 && move >= 0;
//        assertTrue(valid);
//    }
//}
