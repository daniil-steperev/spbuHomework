package group144.uniquelist.stepyrev;

/** An exception that should be raised when user tries to remove an element from an empty list */
public class EmptyListException extends Exception {
    public EmptyListException() {
        super();
    }

    public EmptyListException(String msg) {
        super(msg);
    }
}
