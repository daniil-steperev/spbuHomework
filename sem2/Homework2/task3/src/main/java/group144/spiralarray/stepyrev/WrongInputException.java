package group144.spiralarray.stepyrev;

/**
 * A class that represents Exception that should be raised in case of wrong input
 */
public class WrongInputException extends Exception {
    /**
     * A method that raises the Exception
     * @param message means message that should be added to Exception text
     */
    public WrongInputException(String message) {
        super(message);
    }
}
