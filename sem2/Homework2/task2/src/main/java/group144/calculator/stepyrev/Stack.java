package group144.calculator.stepyrev;

/**
 * An interface that describes main methods of stack
 * @param <Type> means type of value that stack is consisted of
 */
public interface Stack<Type> {
    /**
     * A method that adds new value to the top of the stack
     * @param value means value that should be added
     */
    void push(Type value);

    /**
     * A method that removes top element
     * @return means value of removed element
     */
    Type pop() throws EmptyStackException;

    /**
     * A method that checks if the stack is empty
     * @return means true if stack is empty and false if it is not
     */
    boolean isEmpty();

    int getLength();
}
