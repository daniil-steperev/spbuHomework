package group144.spiralarray.stepyrev;

/**
 * A class that implement converting Matrix from a table form to array.
 *
 * Converting happens from the center of the Matrix to the top right element using spiral walk
 */
public class MatrixUnpacker {
    /**
     * A method that converts Matrix from a table form to array
     * @param matrix means Matrix that should be converted
     * @return means array representing converted Matrix
     */
    public static int[] convertMatrixToList (Matrix matrix) {
        int[] elements = new int[matrix.getSize() * matrix.getSize()]; // array of Matrix elements
        int array[][] = matrix.getMatrix(); // Matrix in table form
        int size = matrix.getSize();

        int shift = 1;
        int currentI = size / 2; // index of current first coordinate of the element in table form
        int currentJ = size / 2; // index of current second coordinate of the element in table form
        int freeSpace = 0; // index of free cell in array of Matrix elements
        int[] lastPositions = new int[3]; // a small array with currentI, currentJ, freeSpace
        while (shift < size) {
            lastPositions =  makeMove(array, elements, freeSpace, shift, currentI, currentJ, "right");
            currentI = lastPositions[0];
            currentJ = lastPositions[1];
            freeSpace = lastPositions[2];

            lastPositions = makeMove(array, elements, freeSpace, shift, currentI, currentJ, "down");
            currentI = lastPositions[0];
            currentJ = lastPositions[1];
            freeSpace = lastPositions[2];
            shift++;

            lastPositions = makeMove(array, elements, freeSpace, shift, currentI, currentJ, "left");
            currentI = lastPositions[0];
            currentJ = lastPositions[1];
            freeSpace = lastPositions[2];

            lastPositions = makeMove(array, elements, freeSpace, shift, currentI, currentJ, "up");
            currentI = lastPositions[0];
            currentJ = lastPositions[1];
            freeSpace = lastPositions[2];
            shift++;
        }

        shift = array.length;
        makeMove(array, elements, freeSpace, shift, currentI, currentJ, "right");

        return elements;
    }

    private static int[] makeMove(int[][] array, int[] list, int freeSpace, int shift, int currentI, int currentJ, String direction) {
        int counter = 0;
        while (counter < shift) {
            list[freeSpace] = array[currentI][currentJ];
            freeSpace++;
            counter++;

            if (direction.equals("right")) {
                currentJ++;
            } else if (direction.equals("down")) {
                currentI++;
            } else if (direction.equals("left")) {
                currentJ--;
            } else if (direction.equals("up")) {
                currentI--;
            }
        }

        int[] lastPositions = {currentI, currentJ, freeSpace};
        return lastPositions;
    }
}
