package group144.calculator.stepyrev;

/** A class that describes the Expression that should be raised if inputted expression is not correct */
public class WrongInputException extends Exception {
    /**
     * A method that raises an Exception
     * @param message means message that should be added to Exception message
     */
    public WrongInputException(String message) {
        super(message);
    }
}
