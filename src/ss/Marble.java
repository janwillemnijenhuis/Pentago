package ss;

/**
 * Marble enum used in the pentago Software Systems UT project
 * @author janwillem.nijenhuis
 * @version 0.2.0
 */
public enum Marble {
    EMPTY(" "),
    BLACK("\u001B[36mB\u001B[0m"),
    WHITE("\u001B[37mW\u001B[0m");

    String colorCode;

    Marble(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

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
