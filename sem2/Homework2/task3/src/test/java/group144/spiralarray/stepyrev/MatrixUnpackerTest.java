package group144.spiralarray.stepyrev;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MatrixUnpackerTest {
    @Test
    void convertMatrixToListFromRegularExpression() throws WrongInputException, IOException {
        int[][] matrixTable = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(matrixTable, 3);
        String testFileName = "test.txt";
        SpiralWriter writer = new FileWriter(testFileName);
        MatrixUnpacker unpacker = new MatrixUnpacker();
        unpacker.writeMatrixSpirally(matrix, writer);

        String convertedMatrix = "5 6 9 8 7 4 1 2 3 ";
        BufferedReader reader = new BufferedReader(new FileReader(testFileName));
        assertEquals(convertedMatrix, reader.readLine());

        reader.close();
    }
}