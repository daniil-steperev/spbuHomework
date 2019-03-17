package group144.uniquelist.stepyrev;

/** A class that represents an exception that should be raised when user tries to remove missing element */
public class AbsenceElementException extends Exception {
    public AbsenceElementException() {
        super();
    }

    public AbsenceElementException(String msg) {
        super(msg);
    }
}
