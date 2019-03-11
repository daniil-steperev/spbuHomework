package group144.spiralarray.stepyrev;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static group144.spiralarray.stepyrev.MatrixUnpacker.convertMatrixToList;

/** A class that represents spiral writer of Matrix to the File */
public class FileWriter implements SpiralWriter {
    private String fileName = "";

    public FileWriter (String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(Matrix matrix) {
        if (fileName.equals("")) {
            System.out.println("Enter filename: ");
            return;
        }

        try {
            FileOutputStream fileOutput = new FileOutputStream(fileName);
            int[][] matrixArray = matrix.getMatrix();

            StringBuilder convertedMatrix = convertMatrixToList(matrix);
            String stringMatrix = convertedMatrix.toString();

            byte[] bufferedData = stringMatrix.getBytes();
            fileOutput.write(bufferedData);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
