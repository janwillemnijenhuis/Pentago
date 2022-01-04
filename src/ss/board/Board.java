package ss.board;

import ss.Marble;

/**
 * Board for the Pentago game. UT Software Systems final project
 * @author andreas.kakotaritis
 * @version 0.2
 */

public class Board {

    /*@ private invariant fields.length == DIM*DIM;
        private invariant (\forall int i, j; (i >= 0 && i < DIM) && (j>=0 && j<DIM); fields[i][j] == Marble.EMPTY || fields[i] == Marble.BLACK || fields[i] == Marble.WHITE);
    @*/

    private final int DIM = 6;
    private Marble[][] fields = new Marble[DIM][DIM];
    private Marble[][] quadrant;
    private Quadrant[] allQuadrants;


    /**
     * initialize empty board
     */

    //@ ensures (\forall int i,j; (i >= 0 && i < DIM*DIM) && (j>=0 && j<DIM); fields[i][j] == Marble.EMPTY);
    public Board() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                this.fields[i][j] = Marble.EMPTY;
            }

        }

    }

    /**
     * returns copy of the board
     *
     * @return the deepcopy
     */
    public Board deepCopy() {
        Board boardCopy = new Board();

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                boardCopy.fields[i][j] = this.fields[i][j];
            }

        }
        return boardCopy;
    }

    /**
     * transforms row,col pair to index from 0..35
     *
     * @param row index
     * @param col index
     * @return index on board
     */
    //is this what we want or the opposite?
    public int getIndex(int row, int col) {
        return row * 6 + col;
    }


    public int[] getRowCol(int index) {
        int[] rowCol = new int[2];
        rowCol[0] = index / 6;
        rowCol[1] = index % 6;
        return rowCol;
    }


    /**
     * checks if index if valid 0..35
     *
     * @param index
     * @return true if valid, false otherwise
     */
    public boolean isField(int index) {
        if (index >= 0 && index < DIM * DIM) {
            return true;
        }
        return false;
    }

    /**
     * checks if index is valid row,col pair
     *
     * @param row index
     * @param col index
     * @return true if valid, false if not
     */
    public boolean isField(int row, int col) {
        int index = getIndex(row, col);
        return isField(index);
    }

    /**
     * returns the marble currently in the field by index
     *
     * @param index
     * @return the marble in the field
     */
    public Marble getField(int index) {
        int row = getRowCol(index)[0];
        int col = getRowCol(index)[1];
        Marble marble = fields[row][col];
        return marble;
    }

    /**
     * returns the marble currently in the field by row,col pair
     *
     * @param row index
     * @param col index
     * @return the marble in the field
     */
    public Marble getField(int row, int col) {
        Marble marble = fields[row][col];
        return marble;
    }

    /**
     * check if field is empty
     *
     * @param index
     * @return true if empty, false if not
     */
    public boolean isEmpty(int index) {
        Marble marble = getField(index);
        if (marble.isEqual(marble, Marble.EMPTY)) {
            return true;
        }
        return false;
    }

    /**
     * check if field is empty
     *
     * @param row index
     * @param col index
     * @return true if empty, false if not
     */
    public boolean isEmpty(int row, int col) {
        int index = getIndex(row, col);
        return isEmpty(index);
    }

    /**
     * check if board is full
     *
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        for (int i = 0; i < DIM * DIM; i++) {
            if (isEmpty(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if game is terminated
     * board is full or board has winner
     *
     * @return true if game over, false otherwise
     */
    public boolean gameOver() {
        if (isFull() || hasWinner() || isDraw()) {
            return true;
        }
        return false;
    }

    /**
     * check if marble has a full row (i.e. 5 marbles)
     *
     * @param marble the marble to check for
     * @return true if full row, false otherwise
     */
    public boolean hasRow(Marble marble) {
        for (int i = 0; i < DIM; i++) {
            if((fields[i][0] == marble && fields[i][0] == fields[i][1] && fields[i][0] == fields[i][2] && fields[i][0] == fields[i][3] && fields[i][0] == fields[i][4])||
                    (fields[i][5] == marble && fields[i][5] == fields[i][1] && fields[i][5] == fields[i][2] && fields[i][5] == fields[i][3] && fields[i][5] == fields[i][4])) {
                return true;
            }

        }
        return false;
    }

    /**
     * check if marble has a full col (i.e. 5 marbles)
     *
     * @param marble the marble to check for
     * @return true if full col, false otherwise
     */
    public boolean hasCol(Marble marble) {
        for (int i = 0; i < DIM; i++) {
            if((fields[0][i] == marble && fields[0][i] == fields[1][i] && fields[0][i] == fields[2][i] && fields[0][i] == fields[3][i] && fields[0][i] == fields[4][i])||
                    (fields[5][i] == marble && fields[5][i] == fields[1][i] && fields[5][i] == fields[2][i] && fields[5][i] == fields[3][i] && fields[5][i] == fields[4][i])) {
                return true;
            }
            }
        return false;
    }

    /**
     * check if marble has a full diagonal (i.e. 5 marbles)
     *
     * @param marble the marble to check for
     * @return true if full diagonal, false otherwise
     */
    public boolean hasDiagonal(Marble marble) {

            if ((fields[0][1] == marble && fields[1][2] == marble && fields[3][2] == marble && fields[4][3] == marble && fields[5][4] == marble)||
                    (fields[0][0] == marble && fields[1][1] == marble && fields[2][2] == marble && fields[3][3] == marble && fields[4][4] == marble)||
                    (fields[1][0] == marble)) {
                return true;
            }


        return false;
    }

    /**
     * check if marble has won
     *
     * @param marble to check for
     * @return true if has won, false otherwise
     */
    public boolean isWinner(Marble marble) {
        if (hasCol(marble) || hasRow(marble) || hasDiagonal(marble)) {
            return true;
        }
        return false;
    }



    /**
     * check if the board is at a draw
     * 1. both players win
     * 2. board is full
     * @return true if draw, false otherwise
     */
    public boolean isDraw() {
        if (isWinner(Marble.BLACK) && isWinner(Marble.WHITE)) {
            return true;
        }
        else if (isFull()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * check if board has a winner
     * @return true if someone has won, false otherwise
     */
    public boolean hasWinner() {
        if ((isWinner(Marble.WHITE) && !isDraw()) || (isWinner(Marble.BLACK) && !isDraw())) {
            return true;
        }
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

}
