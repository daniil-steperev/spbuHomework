package group144.spiralarray.stepyrev;

import static group144.spiralarray.stepyrev.MatrixUnpacker.convertMatrixToList;

/** A class that represents spiral writer of Matrix to the console */
public class ConsoleWriter implements SpiralWriter {
    @Override
    public void write(Matrix matrix) throws WrongInputException {
        StringBuilder convertedMatrix = convertMatrixToList(matrix);
        System.out.println(convertedMatrix);
    }
}
