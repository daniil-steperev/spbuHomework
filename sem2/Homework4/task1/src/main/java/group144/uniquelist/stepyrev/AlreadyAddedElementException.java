package group144.uniquelist.stepyrev;

/** An exception that should be raised when user tries to add value (that was already added) to the unique list */
public class AlreadyAddedElementException extends Exception {
    public AlreadyAddedElementException() {
        super();
    }

    public AlreadyAddedElementException(String msg) {
        super(msg);
    }
}
