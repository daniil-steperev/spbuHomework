package group144.spiralarray.stepyrev;

/**
 * An interface that represents spiral writer of Matrix
 */
public interface SpiralWriter {
    /**
     * A method that writes converted Matrix to the console or file
     * @param matrix means matrix that should be written
     * @throws WrongInputException means exception that should be raised if input is not correct
     */
    void write(Matrix matrix) throws WrongInputException;
}
