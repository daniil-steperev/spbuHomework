package group144.hashtable.stepyrev;

/** A class that intended to call in case of wrong inputted data. */
public class WrongInputException extends Exception {
    public WrongInputException() { super(); }

    public WrongInputException(String message) {
        super(message);
    }
}
