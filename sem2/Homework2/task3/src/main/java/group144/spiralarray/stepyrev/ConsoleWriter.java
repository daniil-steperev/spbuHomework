package group144.spiralarray.stepyrev;

import static group144.spiralarray.stepyrev.MatrixUnpacker.convertMatrixToList;

/**
 * A class that represents spiral writer of Matrix to the console
 */
public class ConsoleWriter implements SpiralWriter {
    @Override
    public void write(Matrix matrix) throws WrongInputException {
        int[][] matrixArray = matrix.getMatrix();

        int[] convertedMatrix = new int[0];
        convertedMatrix = convertMatrixToList(matrix);

        for (int i = 0; i < convertedMatrix.length; i++) {
            System.out.print(convertedMatrix[i] + " ");
        }
    }
}
