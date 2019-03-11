package group144.calculator.stepyrev;

/**
 * An implementation of Stack based on using array
 * @param <Type> means type of Objects that stack is consisted of
 */
public class ArrayStack<Type> implements Stack<Type> {
    private int length = 0;
    private int size = 15;
    private Type[] values = (Type[]) new Object[size];

    /** {@inheritDoc} */
    @Override
    public void push(Type value) {
        length++;

        if (length == size) {
            increaseStack();
            values[length] = value;
            return;
        }

        values[length] = value;
    }

    /** {@inheritDoc} */
    @Override
    public Type pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Type returnElement = values[length - 1];
        values[length - 1] = null;
        length--;

        return returnElement;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /** {@inheritDoc} */
    @Override
    public int getLength() {
        return length;
    }

    private void increaseStack() {
        Type[] newValues = (Type[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newValues[i] = values[i];
        }

        size *= 2;
        values = newValues;
    }

    private void decreaseStack() {
        size = length + 10;
        Type[] newValues = (Type[]) new Object[size];
        for (int i = 0; i < length; i++) {
            newValues[i] = values[i];
        }

        values = newValues;
    }
}
