package ss.board;

import ss.Marble;

public class Board {

    private Marble[][] fields;
    private Marble[][] quadrant;
    private Quadrant[] allQuadrants;

    private final int DIM = 6;

    /**
     * initialize empty board
     */
    public Board() {

    }

    /**
     * returns copy of the board
     * @return the deepcopy
     */
    public Board deepCopy() {
        return null;
    }

    /**
     * transforms row,col pair to index from 0..35
     * @param row index
     * @param col index
     * @return index on board
     */
    public int getIndex(int row, int col) {
        return 0;
    }

    /**
     * checks if index if valid 0..35
     * @param index
     * @return true if valid, false otherwise
     */
    public boolean isField(int index) {
        return false;
    }

    /**
     * checks if index is valid row,col pair
     * @param row index
     * @param col index
     * @return true if valid, false if not
     */
    public boolean isField(int row, int col) {
        return false;
    }

    /**
     * returns the marble currently in the field by index
     * @param index
     * @return the marble in the field
     */
    public Marble getField(int index) {
        return null;
    }

    /**
     * returns the marble currently in the field by row,col pair
     * @param row index
     * @param col index
     * @return the marble in the field
     */
    public Marble getField(int row, int col) {
        return null;
    }

    /**
     * check if field is empty
     * @param index
     * @return true if empty, false if not
     */
    public boolean isEmpty(int index) {
        return false;
    }

    /**
     * check if field is empty
     * @param row index
     * @param col index
     * @return true if empty, false if not
     */
    public boolean isEmpty(int row, int col) {
        return false;
    }

    /**
     * check if board is full
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return false;
    }

    /**
     * check if game is terminated
     * board is full or board has winner
     * @return true if game over, false otherwise
     */
    public boolean gameOver() {
        return false;
    }

    /**
     * check if marble has a full row (i.e. 5 marbles)
     * @param marble the marble to check for
     * @return true if full row, false otherwise
     */
    public boolean hasRow(Marble marble) {
        return false;
    }

    /**
     * check if marble has a full col (i.e. 5 marbles)
     * @param marble the marble to check for
     * @return true if full col, false otherwise
     */
    public boolean hasCol(Marble marble) {
        return false;
    }

    /**
     * check if marble has a full diagonal (i.e. 5 marbles)
     * @param marble the marble to check for
     * @return true if full diagonal, false otherwise
     */
    public boolean hasDiagonal(Marble marble) {
        return false;
    }

    /**
     * check if marble has won
     * @param marble to check for
     * @return true if has won, false otherwise
     */
    public boolean isWinner(Marble marble) {
        return false;
    }

    /**
     * check if the board is at a draw
     * 1. both players win
     * 2. board is full
     * @return true if draw, false otherwise
     */
    public boolean isDraw() {
        return false;
    }

    /**
     * check if board has a winner
     * @return true if someone has won, false otherwise
     */
    public boolean hasWinner() {
        return false;
    }

    /**
     * prints the string representation of the board
     * it shows the numbering of the fields and quadrants
     * we make one board which represents both the numbers and placed
     * marbles at the same time
     * @return String representation of the current board
     */
    public String toString() {
        return null;
    }

    /**
     * resets the board to initial state
     */
    public void reset() {

    }

    /**
     * sets field at this integer to this marble
     * @param index of the field
     * @param marble the marble to set the field to
     */
    public void setField(int index, Marble marble) {

    }

    /**
     * sets field at this row,col pair to this marble
     * @param row index
     * @param col index
     * @param marble the marble to set the field to
     */
    public void setField(int row, int col, Marble marble) {

    }

    /**
     * specifies at which quadrant the index is in
     * @param index given index
     * @return number of quadrant
     */
    public int getQuadrantIndex(int index){
        return 0;
    }

    /**
     * converts boardwide index to quadrant row and col
     * @param index board field
     * @return row and col in quadrant
     */
    public int[] getQuadrantRowCol(int index){
        return null;
    }

    /**
     * transforms the variable allQuadrants to the variable fields
     */
    public void updateFields(){

    }

    /**
     * transforms the variable fields to the variable allQuadrants
     */
    public void updateQuadrants(){

    }

    /**
     * rotates a quadrant of the board
     * @param numQuad number of quadrant to be rotated
     * @param direction direction of rotation
     */
    public void rotateQuadrant(int numQuad, char direction) {

    }

    /**
     * @return the dimension of the board
     */
    public int getDIM() {
        return this.DIM;
    }

}
