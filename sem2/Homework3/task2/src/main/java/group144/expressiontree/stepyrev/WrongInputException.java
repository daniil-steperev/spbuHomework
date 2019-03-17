package group144.expressiontree.stepyrev;

/** A class that should be raised up when user tries to input incorrect data */
public class WrongInputException extends Exception {
    public WrongInputException() {
        super();
    }

    public WrongInputException(String msg) {
        super(msg);
    }
}
