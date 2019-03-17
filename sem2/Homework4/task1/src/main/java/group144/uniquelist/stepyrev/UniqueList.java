package group144.uniquelist.stepyrev;

/**
 * A class that represents single linked list without similar elements
 * @param <T> means a value of elements that unique list is consisted of
 */
public class UniqueList<T> extends List<T> {
    /**
     * A method that adds the value to the head of the stack
     * @param value means value of the element that should be added
     * @throws AlreadyAddedElementException an exception that should be raised when user tries to add already presented element to the unique list
     */
    @Override
    public void addFirst(T value) throws AlreadyAddedElementException {
        if (isContain(value)) {
            throw new AlreadyAddedElementException();
        }

        super.addFirst(value);
    }

    /**
     * A method that adds the value to the end of the stack
     * @param value means value of the element that should be added
     * @throws AlreadyAddedElementException an exception that should be raised when user tries to add already presented element to the unique list
     */
    @Override
    public void addLast(T value) throws AlreadyAddedElementException {
        if (isContain(value)) {
            throw new AlreadyAddedElementException();
        }

        super.addLast(value);
    }

    /**
     * A method that adds the value to the position to the unique list
     * @param value means value of the element
     * @param index means number of the position to that element should be added
     * @throws WrongIndexException an exception that should be raised when user tries to add an element to the wrong position (e.g. index is negative)
     * @throws AlreadyAddedElementException an exception that should be raised when user tries to add already presented element to the unique list
     */
    @Override
    public void add(T value, int index) throws WrongIndexException, AlreadyAddedElementException {
        if (isContain(value)) {
            throw new AlreadyAddedElementException();
        }

        super.add(value, index);
    }
}
