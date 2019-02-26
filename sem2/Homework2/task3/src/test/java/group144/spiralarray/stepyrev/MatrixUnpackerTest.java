package group144.spiralarray.stepyrev;

import org.junit.jupiter.api.Test;

import static group144.spiralarray.stepyrev.MatrixUnpacker.convertMatrixToList;
import static org.junit.jupiter.api.Assertions.*;

class MatrixUnpackerTest {
    @Test
    void convertMatrixToListFromRegularExpression() throws WrongInputException {
        int[][] matrixTable = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(matrixTable, 3);

        int[] convertedMatrix = {5, 6, 9, 8, 7, 4, 1, 2, 3};
        assertArrayEquals(convertedMatrix, convertMatrixToList(matrix));
    }
}