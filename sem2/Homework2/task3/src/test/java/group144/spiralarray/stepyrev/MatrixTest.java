package group144.spiralarray.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    @Test
    void createMatrixWithWrongSize() {
        int[][] matixTable = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertThrows(WrongInputException.class, () -> new Matrix(matixTable, 2));
    }

    @Test
    void createMatrixWithEvenSize() {
        int[][] matixTable = {
                {1, 2},
                {3, 4}
        };
        assertThrows(WrongInputException.class, () -> new Matrix(matixTable, 2));
    }
}