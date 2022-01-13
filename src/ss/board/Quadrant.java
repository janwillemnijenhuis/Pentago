package ss.board;

import ss.Marble;

public class Quadrant {

    private int index;
    private Marble[][] values = new Marble[3][3];
    private final int DIM = 3;

    /**
     * creates a quadrant as a list of marbles
     * @param index the number of the quadrant
     */
    public Quadrant(int index){
        this.index = index;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                values[i][j] = Marble.EMPTY;
            }
        }
    }

    public Quadrant deepCopy() {
        Quadrant deepCopy = new Quadrant(this.index);
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                deepCopy.values[i][j] = this.values[i][j];
            }
        }
        return deepCopy;
    }

    /**
     * gives the values of the quadrant
     * @return the values
     */
    public Marble[][] getValues() {
        return values;
    }

    /**
     * sets the value of the col,row pair to marble
     * @param col index
     * @param row index
     * @param marble value to be set
     */
    public void setValue(int row, int col, Marble marble) {
        this.values[row][col] = marble;
    }

    /**
     * rotates given quadrant to given direction
     * @param direction give direction
     * @param quadrant quadrant to be rotated
     */
    public void rotateQuadrant(Rotation direction, Quadrant quadrant){

    }

}
