package group144.uniquelist.stepyrev;

/** A class that represents an exception that should be raised when user tries to remove missing element */
public class ElementNotFoundException extends Exception {
    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String msg) {
        super(msg);
    }
}
