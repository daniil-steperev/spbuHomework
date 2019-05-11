package group144.spiralarray.stepyrev;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/** A class that represents spiral writer of Matrix to the File */
public class FileWriter implements SpiralWriter {
    private FileOutputStream file;

    /**
     * A constructor of FileWriter.
     * @param fileName - a name of the file
     * @throws FileNotFoundException - if file was not found -- create new one
     */
    public FileWriter (String fileName) throws FileNotFoundException {
        file = new FileOutputStream(fileName, true);
    }

    /** {@inheritDoc}*/
    @Override
    public void write(String expression) {
        try {
            file.write(expression.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
