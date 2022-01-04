package ss;

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
}
