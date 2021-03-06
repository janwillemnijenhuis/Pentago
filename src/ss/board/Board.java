package ss.board;

import ss.Marble;

import java.util.Arrays;

/**
 * Board for the Pentago game. UT Software Systems final project
 * @author andreas.kakotaritis
 * @version 0.8
 */

public class Board {

//    /*@ private invariant fields.length == DIM*DIM;
//        private invariant (\forall int i, j; (i >= 0 && i < DIM) && (j>=0 && j<DIM); fields[i][j] == Marble.EMPTY || fields[i] == Marble.BLACK || fields[i] == Marble.WHITE);
//    @*/

    public static final String TEXT_YELLOW = "\u001B[33m";
    private final int DIM = 6;
    private static final String DELIM = "          ";
    private static final String[] NUMBERING = {
            " 0  | 1  | 2  | 3  | 4  | 5  ", "-----+----+----+----+----+-----",
            " 6  | 7  | 8  | 9  | 10 | 11 ", "-----+----+----+----+----+-----",
            " 12 | 13 | 14 | 15 | 16 | 17 ", "-----+----+----+----+----+-----",
            " 18 | 19 | 20 | 21 | 22 | 23 ", "-----+----+----+----+----+-----",
            " 24 | 25 | 26 | 27 | 28 | 29 ", "-----+----+----+----+----+-----",
            " 30 | 31 | 32 | 33 | 34 | 35 ", "-----+----+----+----+----+-----"};
    private static final String LINE = NUMBERING[1];

    private Marble[][] fields = new Marble[DIM][DIM];
    private Marble[][] quadrant;
    private Quadrant[] allQuadrants = new Quadrant[4];
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

    private int winRow = 7; //should be initialized to an impossible value
    private int winCol = 7;
    private int winDiag = 10;


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
        for (int j = 0; j < 4; j++) {
            this.allQuadrants[j] = new Quadrant(j);
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
        for (int j = 0; j < 4; j++) {
            boardCopy.allQuadrants[j] = this.allQuadrants[j].deepCopy();
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
                        winRow = i;
                        return true;
                    }
                }
            }
        }
        else if (marble.isEqual(Marble.WHITE)) {
            for (int i = 0; i < DIM; i++) {
                for (int j = 0; j < 5; j++) {
                    if ((Arrays.equals(fields[i], winningWhiteRows[j]))) {
                        winRow = i;
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
                winCol = i;
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

        for (int i = 2; i < 4; i++) {
            for (int j = 2; j < 4; j++) {
                if (fields[i][j] == marble && fields[i-1][j+1] == marble && fields[i-2][j+2] == marble && fields[i+1][j-1] == marble && fields[i+2][j-2] == marble ) {
                    winDiag = (2*i)-j;
                    return true;
                }
                else if (fields[i][j] == marble && fields[i-1][j-1] == marble && fields[i-2][j-2] == marble && fields[i+1][j+1] == marble && fields[i+2][j+2] == marble ) {
                    winDiag = i-(2*j);
                    return true;
                }

            }

        }

//            if ((fields[0][1] == marble && fields[1][2] == marble && fields[2][3] == marble && fields[3][4] == marble && fields[4][5] == marble)||
//                    (fields[0][0] == marble && fields[1][1] == marble && fields[2][2] == marble && fields[3][3] == marble && fields[4][4] == marble)||
//                    (fields[5][5] == marble && fields[1][1] == marble && fields[2][2] == marble && fields[3][3] == marble && fields[4][4] == marble) ||
//                    (fields[1][0] == marble && fields[2][1] == marble && fields[3][2] == marble && fields[4][3] == marble && fields[5][4] == marble) ||
//                    (fields[0][4] == marble && fields[1][3] == marble && fields[3][2] == marble && fields[3][1] == marble && fields[4][0] == marble)||
//                    (fields[0][5] == marble && fields[1][4] == marble && fields[2][3] == marble && fields[3][2] == marble && fields[4][1] == marble) ||
//                    (fields[5][0] == marble && fields[1][4] == marble && fields[2][3] == marble && fields[3][2] == marble && fields[4][1] == marble) ||
//                    (fields[1][5] == marble && fields[2][4] == marble && fields[3][3] == marble && fields[4][2] == marble && fields[5][1] == marble)) {
//                return true;
//            }


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
     * resets the board to initial state
     */
    public void reset() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                fields[i][j] = Marble.EMPTY;
            }
        }
        this.updateQuadrants();
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
    }

    public void updateField(int index, Marble marble) {
        if (isField(index)) {
            fields[getRowCol(index)[0]][getRowCol(index)[1]] = marble;
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
            updateField(i,allQuadrants[getQuadrantIndex(i)].getValues()[getQuadrantRowCol(i)[0]][getQuadrantRowCol(i)[1]]);
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
        Board boardCopy = this.deepCopy();
        Quadrant tempQuad = boardCopy.allQuadrants[numQuad];
        if (direction == 'w'){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.allQuadrants[numQuad].setValue(i, j, tempQuad.getValues()[2-j][i]);
                }
            }

        }
        else if (direction == 'a') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.allQuadrants[numQuad].setValue(i, j, tempQuad.getValues()[j][2-i]);
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

    /**
     * check if valid quadrant
     * @param quadNumber
     * @return true if between 0 and 3, false otherwise
     */
    public boolean isQuad(int quadNumber) {return quadNumber >= 0 && quadNumber <= 3;}

    public boolean isDir(char direction) {return (direction == 'w' || direction == 'a');}


    /**
     * prints the string representation of the board
     * it shows the numbering of the fields and quadrants
     * we make one board which represents both the numbers and placed
     * marbles at the same time
     * @return String representation of the current board
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < DIM; i++) {
            String row = "";
            if (i == 0) {
                s = s + new String(new char[30]).replace('\0', ' ') + DELIM + "0";
                s = s + new String(new char[31]).replace('\0', ' ') + "1\n";
                s = s + LINE + DELIM + LINE + "\n";
            }
            for (int j = 0; j < DIM; j++) {
                row = row + "  " + getField(i, j).toString().substring(0, 1).replace("E", " ") + " ";
                if (j < DIM - 1) {
                    row = row + "|";
                }
            }
            s = s + "|" + row + "|" + DELIM + "|" + NUMBERING[i * 2] + "|";
            if (i < DIM - 1) {
                s = s + "\n" + LINE + DELIM + LINE + "\n";
            }
            if (i == DIM - 1) {
                s = s + "\n"+ LINE + DELIM + LINE + "\n";
                s = s + new String(new char[30]).replace('\0', ' ') + DELIM + "2";
                s = s + new String(new char[31]).replace('\0', ' ') + "3\n";
            }

        }
        return s;
    }
}
