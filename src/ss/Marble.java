package ss;

/**
 * Marble enum used in the pentago Software Systems UT project
 * @author janwillem.nijenhuis
 * @version 0.2.0
 */
public enum Marble {
    EMPTY,
    BLACK,
    WHITE;

    /**
     * checks if marble is equal to the other marble
     * @param other marble to check
     * @return true if equal, false otherwise
     */
    public boolean isEqual(Marble other) {
        return this.equals(other);
    }

    /**
     * function to easily switch between marbles
     * if marble is black, turn white
     * if marble is white, turn black
     * if marble is empty, retain
     * @return marble
     */
    public Marble other() {
        if (this.equals(Marble.BLACK)) {
            return Marble.WHITE;
        } else if (this.equals(Marble.WHITE)) {
            return Marble.BLACK;
        } else {
            return Marble.EMPTY;
        }
    }
}
