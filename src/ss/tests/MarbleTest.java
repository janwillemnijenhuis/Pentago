package ss.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.Marble;

import static org.junit.jupiter.api.Assertions.*;

public class MarbleTest {
    Marble black;
    Marble white;
    Marble empty;
    Marble testBlack;
    Marble testWhite;
    Marble testEmpty;

    @BeforeEach
    void setUp() {
        black = Marble.BLACK;
        white = Marble.WHITE;
        empty = Marble.EMPTY;

        testBlack = Marble.BLACK;
        testWhite = Marble.WHITE;
        testEmpty = Marble.EMPTY;
    }

    @Test
    void testIsEqual() {
        assertTrue(black.isEqual(testBlack));
        assertTrue(white.isEqual(testWhite));
        assertTrue(empty.isEqual(testEmpty));
    }

    @Test
    void testIsNotEqual() {
        assertFalse(black.isEqual(testWhite));
        assertFalse(black.isEqual(testEmpty));
        assertFalse(white.isEqual(testBlack));
        assertFalse(white.isEqual(testEmpty));
        assertFalse(empty.isEqual(testWhite));
        assertFalse(empty.isEqual(testBlack));
    }
}
