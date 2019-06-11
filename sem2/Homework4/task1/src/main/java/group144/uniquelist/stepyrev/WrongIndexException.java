package group144.uniquelist.stepyrev;

/** An exception that should be raised when user tries to perform actions with wrong index (e.g. add element to the negative position) */
public class WrongIndexException extends Exception {
    public WrongIndexException() {
        super();
    }

    public WrongIndexException(String msg) {
        super(msg);
    }
}
