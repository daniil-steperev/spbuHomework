package group144.calculator.stepyrev;

/** A class that describes an Exception that should be raised when user tries to pop from empty stack*/
public class EmptyStackException extends Exception {
    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String msg) {
        super(msg);
    }
}
