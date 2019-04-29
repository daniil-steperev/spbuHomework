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
    public static void writeMatrixSpirally (Matrix matrix, SpiralWriter writer) throws WrongInputException {
        int array[][] = matrix.getMatrix(); // Matrix in table form
        int size = matrix.getSize();

        int shift = 1;
        int currentI = size / 2; // index of current first coordinate of the element in table form
        int currentJ = size / 2; // index of current second coordinate of the element in table form
        Position position = new Position(currentI, currentJ); // consists of current position
        while (shift < size) {
            position =  makeMove(array, writer, position, shift, "right");

            position = makeMove(array, writer, position, shift, "down");
            shift++;

            position = makeMove(array, writer, position, shift, "left");

            position = makeMove(array, writer, position, shift, "up");
            shift++;
        }

        shift = array.length; // last move
        position = makeMove(array, writer, position, shift, "right");
    }

    private static Position makeMove(int[][] array, SpiralWriter writer, Position position, int shift, String direction) throws WrongInputException {
        int counter = 0;
        while (counter < shift) {
            writer.write(array[position.currentI][position.currentJ] + " ");
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
