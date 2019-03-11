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
    public static StringBuilder convertMatrixToList (Matrix matrix) {
        StringBuilder elements = new StringBuilder(); // array of Matrix elements
        int array[][] = matrix.getMatrix(); // Matrix in table form
        int size = matrix.getSize();

        int shift = 1;
        int currentI = size / 2; // index of current first coordinate of the element in table form
        int currentJ = size / 2; // index of current second coordinate of the element in table form
        Position position = new Position(currentI, currentJ); // consists of current position
        while (shift < size) {
            position =  makeMove(array, elements, position, shift, "right");

            position = makeMove(array, elements, position, shift, "down");
            shift++;

            position = makeMove(array, elements, position, shift, "left");

            position = makeMove(array, elements, position, shift, "up");
            shift++;
        }

        shift = array.length;
        position = makeMove(array, elements, position, shift, "right");

        return elements;
    }

    private static Position makeMove(int[][] array, StringBuilder list, Position position, int shift, String direction) {
        int counter = 0;
        while (counter < shift) {
            list.append(array[position.currentI][position.currentJ] + " ");
            counter++;

            if (direction.equals("right")) {
                position.currentJ++;
            } else if (direction.equals("down")) {
                position.currentI++;
            } else if (direction.equals("left")) {
                position.currentJ--;
            } else if (direction.equals("up")) {
                position.currentI--;
            }
        }

        return position;
    }

    private static class Position {
        private int currentI;
        private int currentJ;

        private Position(int i, int j) {
            currentI = i;
            currentJ = j;
        }
    }
}
