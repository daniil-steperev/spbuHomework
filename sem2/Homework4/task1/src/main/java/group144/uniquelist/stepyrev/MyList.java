package group144.uniquelist.stepyrev;

/**
 * A class that represents simply linked list
 * @param <T> means value that the list holds
 */
public class MyList<T> {
    private Node<T> head;
    private int length;

    /**
     * A method that adds new value to the head of the list
     * @param value means value of the element that should be added
     * @throws AlreadyAddedElementException - an exception that should be raised when element already added to the list
     * @throws WrongIndexException - an exception that should be raised when user inputted wrong index
     */
    public void addFirst(T value) throws AlreadyAddedElementException, WrongIndexException {
        add(value, 0);
    }

    /**
     * A method that adds new value to the end of the list
     * @param value means value of the element that should be added
     * @throws AlreadyAddedElementException - an exception that should be raised when element already added to the list
     * @throws WrongIndexException - an exception that should be raised when user inputted wrong index
     */
    public void addLast(T value) throws AlreadyAddedElementException, WrongIndexException {
        add(value, length);
    }

    /**
     * A method that adds new value to a certain position to the list
     * @param value means value of the element
     * @param index means number of the position to that element should be added
     * @throws WrongIndexException an exception that should be raised when user tries to add an element to the wrong position (e.g. index is negative)
     * @throws AlreadyAddedElementException - an exception that should be raised when element already added to the list
     */
    public void add(T value, int index) throws WrongIndexException, AlreadyAddedElementException {
        if (index < 0 || (index > length && length > 0)) {
            throw new WrongIndexException();
        }

        length++;

        if ((isEmpty() || index == 0) && index >= 0) {
            head = new Node<>(value, head);
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }

        previous.next = new Node<>(value, current);
    }

    /**
     * A method that removes first element from the list
     * @return value of the removed element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     * @throws WrongIndexException an exception that should be raised when user inputted wrong index
     */
    public T removeFirst() throws EmptyListException, WrongIndexException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return remove(0);
    }

    /**
     * A method that removes last element from the list
     * @return value of the removed element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     * @throws WrongIndexException - an exception that should be raised when user inputted wrong index
     */
    public T removeLast() throws EmptyListException, WrongIndexException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return remove(length - 1);
    }

    /**
     * A method that removes element from the list by value
     * @param value a value of the element that should be removed
     * @return a value of the removed element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     * @throws ElementNotFoundException an exception that should be raised when user tries to remove missing element
     * @throws WrongIndexException an exception that should be raised when user inputted wrong index
     */
    public T removeByValue(T value) throws EmptyListException, ElementNotFoundException, WrongIndexException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        Node<T> current = head;
        Node<T> previous = null;
        while (current.next != null && !current.value.equals(value)) {
            previous = current;
            current = current.next;
        }

        if (current.value.equals(value) && current == head) {
            return removeFirst();
        }

        if (current.value.equals(value)) {
            length--;
            previous.next = current.next;

            return value; // as value equals we can return inputted value
        }

        throw new ElementNotFoundException();
    }

    /**
     * A method that removes an element from the inputted index
     * @param index an index of the element that should be removed from the list
     * @return a value of the removed element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     * @throws WrongIndexException - an exception that should be raised when user inputted wrong index
     */
    public T remove(int index) throws EmptyListException, WrongIndexException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        if (index < 0 || (index > length - 1 && length > 0)) {
            throw new WrongIndexException();
        }

        length--;

        if (index == 0) {
            T returnValue = head.value;
            head = head.next;
            return returnValue;
        }

        Node<T> current = head;
        Node<T> previous = null;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }

        T removedValue = current.value;
        previous.next = current.next;

        return removedValue;
    }

    /**
     * A method that checks if the element contains in the list
     * @param value - a value of element that should be found
     * @return true if it presents, false otherwise
     */
    public boolean contains(T value) {
        if (isEmpty()) {
            return false;
        }

        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    /**
     * A method that returns an index of the inputted element
     * @param value means a value that's index should be returned
     * @return an index of the element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     * @throws ElementNotFoundException an exception that should be raised when user tries to remove missing element
     */
    public int getIndexOf(T value) throws EmptyListException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }

            index++;
            current = current.next;
        }

        throw new ElementNotFoundException();
    }

    /**
     * A method that returns a length of the list
     * @return - a length of the list
     */
    public int getLength() {
        return length;
    }

    /** A method that prints the list */
    public String print() {
        if (isEmpty()) {
            return "";
        }

        StringBuilder list = new StringBuilder();
        Node<T> current = head;
        for (int i = 0; i < length; i++) {
            list.append(current.value);
            if (i != length - 1) {
                list.append(" ");
            }

            current = current.next;
        }

        return list.toString();
    }

    /**
     * A method that checks if the list is empty
     * @return true if list is empty and false if isn't
     */
    public boolean isEmpty() { return head == null; }

    private class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
