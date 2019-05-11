package group144.spiralarray.stepyrev;

/** An interface that represents a writer. */
public interface SpiralWriter {
    /**
     * A method that writes converted Matrix to the console or file
     * @param expression means an expression that should be written
     * @throws WrongInputException means exception that should be raised if input is not correct
     */
    void write(String expression) throws WrongInputException;
}
