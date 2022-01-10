package ss.board;

import ss.Marble;

import java.util.Arrays;

/**
 * Board for the Pentago game. UT Software Systems final project
 * @author andreas.kakotaritis
 * @version 0.7
 */

public class Board {

    /*@ private invariant fields.length == DIM*DIM;
        private invariant (\forall int i, j; (i >= 0 && i < DIM) && (j>=0 && j<DIM); fields[i][j] == Marble.EMPTY || fields[i] == Marble.BLACK || fields[i] == Marble.WHITE);
    @*/

    private final int DIM = 6;
    private Marble[][] fields = new Marble[DIM][DIM];
    private Marble[][] quadrant;
    private Quadrant[] allQuadrants;
    private Marble[][] winningWhiteRows = {{Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE},
            {Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.BLACK},
            {Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.EMPTY},
            {Marble.BLACK,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE},
            {Marble.EMPTY,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE,Marble.WHITE}};

    private Marble[][] winningBlackRows = {{Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK},
            {Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.WHITE},
            {Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.EMPTY},
            {Marble.WHITE,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK},
            {Marble.EMPTY,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK,Marble.BLACK}};


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

    public int getIndex(int row, int col) {
        return row * 6 + col;
    }


    public int[] getRowCol(int index) {
        int[] rowCol = {index/6 , index%6};
        return rowCol;
    }


    /**
     * checks if index if valid 0..35
     *
     * @param index
     * @return true if valid, false otherwise
     */
    public boolean isField(int index) {
        return (index >= 0 && index < DIM * DIM);
    }

    /**
     * checks if index is valid row,col pair
     *
     * @param row index
     * @param col index
     * @return true if valid, false if not
     */
    public boolean isField(int row, int col) {
        return isField(getIndex(row, col));
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
        return fields[row][col];
    }

    /**
     * returns the marble currently in the field by row,col pair
     *
     * @param row index
     * @param col index
     * @return the marble in the field
     */
    public Marble getField(int row, int col) {
        return fields[row][col];
    }

    /**
     * check if field is empty
     *
     * @param index
     * @return true if empty, false if not
     */
    public boolean isEmpty(int index) {
        Marble marble = getField(index);
        return marble.isEqual(Marble.EMPTY);

    }

    /**
     * check if field is empty
     *
     * @param row index
     * @param col index
     * @return true if empty, false if not
     */
    public boolean isEmpty(int row, int col) {
        return isEmpty(getIndex(row, col));
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
        return (isFull() || hasWinner() || isDraw());
    }

    /**
     * check if marble has a full row (i.e. 5 marbles)
     *
     * @param marble the marble to check for
     * @return true if full row, false otherwise
     */
    public boolean hasRow(Marble marble) {
        if (marble.isEqual(Marble.BLACK)) {
            for (int i = 0; i < DIM; i++) {
                for (int j = 0; j < 5; j++) {
                    if ((Arrays.equals(fields[i], winningBlackRows[j]))) {
                        return true;
                    }
                }
            }
        }
        else if (marble.isEqual(Marble.WHITE)) {
            for (int i = 0; i < DIM; i++) {
                for (int j = 0; j < 5; j++) {
                    if ((Arrays.equals(fields[i], winningWhiteRows[j]))) {
                        return true;
                    }
                }
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

            if ((fields[0][1] == marble && fields[1][2] == marble && fields[2][3] == marble && fields[3][4] == marble && fields[4][5] == marble)||
                    (fields[0][0] == marble && fields[1][1] == marble && fields[2][2] == marble && fields[3][3] == marble && fields[4][4] == marble)||
                    (fields[5][5] == marble && fields[1][1] == marble && fields[2][2] == marble && fields[3][3] == marble && fields[4][4] == marble) ||
                    (fields[1][0] == marble && fields[2][1] == marble && fields[3][2] == marble && fields[4][3] == marble && fields[5][4] == marble) ||
                    (fields[0][4] == marble && fields[1][3] == marble && fields[3][2] == marble && fields[3][1] == marble && fields[4][0] == marble)||
                    (fields[0][5] == marble && fields[1][4] == marble && fields[2][3] == marble && fields[3][2] == marble && fields[4][1] == marble) ||
                    (fields[5][0] == marble && fields[1][4] == marble && fields[2][3] == marble && fields[3][2] == marble && fields[4][1] == marble) ||
                    (fields[1][5] == marble && fields[2][4] == marble && fields[3][3] == marble && fields[4][2] == marble && fields[5][1] == marble)) {
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
        return (hasCol(marble) || hasRow(marble) || hasDiagonal(marble));
    }



    /**
     * check if the board is at a draw
     * 1. both players win
     * 2. board is full
     * @return true if draw, false otherwise
     */
    public boolean isDraw() {
        return ((isWinner(Marble.BLACK) && isWinner(Marble.WHITE)) || isFull());
    }

    /**
     * check if board has a winner
     * @return true if someone has won, false otherwise
     */
    public boolean hasWinner() {
        return ((isWinner(Marble.WHITE) && !isDraw()) || (isWinner(Marble.BLACK) && !isDraw()));
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
        for (int i = 0; i < 36; i++) {
            setField(i,Marble.EMPTY);
        }
    }

    /**
     * sets field at this integer to this marble
     * @param index of the field
     * @param marble the marble to set the field to
     */
    public void setField(int index, Marble marble) {
        if (isField(index) && isEmpty(index)) {
            fields[getRowCol(index)[0]][getRowCol(index)[1]] = marble;
        }
        else {
            System.out.println("Invalid field location");//we should create an exception for this
        }
    }

    /**
     * sets field at this row,col pair to this marble
     * @param row index
     * @param col index
     * @param marble the marble to set the field to
     */
    public void setField(int row, int col, Marble marble) {
        if (isField(row,col) && isEmpty(row,col)) {
            fields[row][col] = marble;
        }
        else {
            System.out.println("Invalid field location"); //we should create an exception for this
        }
    }

    /**
     * specifies at which quadrant the index is in
     * @param index given index
     * @return number of quadrant. returns 5 for invalid input
     */
    public int getQuadrantIndex(int index) {
        int mod = index % 6;
        if (isField(index) && index < 18) {
            if (mod == 0 || mod == 1 || mod == 2) {
                return 0;
            } else {
                return 1;
            }
        } else if (isField(index) && index >= 18) {
            if (mod == 0 || mod == 1 || mod == 2) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return 5;// maybe an exception here
        }
    }

    /**
     * converts boardwide index to quadrant row and col
     * @param index board field
     * @return row and col in quadrant
     */
    public int[] getQuadrantRowCol(int index){
        return new int[] {(index / 6) % 3,index % 3};
    }

    /**
     * transforms the variable allQuadrants to the variable fields
     */
    public void updateFields(){
        for (int i = 0; i < 36; i++) {
            setField(i,allQuadrants[getQuadrantIndex(i)].getValues()[getQuadrantRowCol(i)[0]][getQuadrantRowCol(i)[1]]);
        }
    }

    /**
     * transforms the variable fields to the variable allQuadrants
     */
    public void updateQuadrants(){
        for (int i = 0; i < 36; i++) {
            allQuadrants[getQuadrantIndex(i)].getValues()[getQuadrantRowCol(i)[0]][getQuadrantRowCol(i)[1]] = getField(i);
        }


    }

    /**
     * rotates a quadrant of the board
     * @param numQuad number of quadrant to be rotated
     * @param direction direction of rotation
     */
    public void rotateQuadrant(int numQuad, char direction) {
       Quadrant tempQuad = allQuadrants[numQuad];
        if (direction == 'l'){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    allQuadrants[numQuad].getValues()[2-j][i] = tempQuad.getValues()[i][j];
                }
            }

        }
        else if (direction == 'r') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    allQuadrants[numQuad].getValues()[j][2-i] = tempQuad.getValues()[i][j];
                }
            }
        }
        else {
            System.out.println("Invalid direction"); //we should create an exception for this
        }

    }

    /**
     * @return the dimension of the board
     */
    public int getDIM() {
        return this.DIM;
    }

}
