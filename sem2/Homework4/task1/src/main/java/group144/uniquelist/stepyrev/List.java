package group144.uniquelist.stepyrev;

/**
 * A class that represents simply linked list
 * @param <T> means value that the list holds
 */
public class List<T> {
    private Node<T> head;
    private int length;

    /**
     * A method that adds new value to the head of the list
     * @param value means value of the element that should be added
     */
    public void addFirst(T value) throws AlreadyAddedElementException {
        head = new Node<>(value, head);
        length++;
    }

    /**
     * A method that adds new value to the end of the list
     * @param value means value of the element that should be added
     */
    public void addLast(T value) throws AlreadyAddedElementException {
        if (isEmpty()) {
            addFirst(value);
            return;
        }

        length++;

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node<>(value);
    }

    /**
     * A method that adds new value to a certain position to the list
     * @param value means value of the element
     * @param index means number of the position to that element should be added
     * @throws WrongIndexException an exception that should be raised when user tries to add an element to the wrong position (e.g. index is negative)
     */
    public void add(T value, int index) throws WrongIndexException, AlreadyAddedElementException {
        if ((isEmpty() || index == 0) && index >= 0) {
            addFirst(value);
            return;
        }

        if (index < 0 || index > length - 1) {
            throw new WrongIndexException();
        }

        if (index == length) { // without -1 because we count with one more element
            addLast(value);
            return;
        }

        length++;

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
     */
    public T removeFirst() throws EmptyListException, AlreadyAddedElementException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        Node<T> removed = head;
        head = head.next;

        length--;
        return removed.value;
    }

    /**
     * A method that removes last element from the list
     * @return value of the removed element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     */
    public T removeLast() throws EmptyListException, AlreadyAddedElementException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        length--;
        T removedValue = current.next.value;
        current.next = null;

        return removedValue;
    }

    /**
     * A method that removes element from the list by value
     * @param value a value of the element that should be removed
     * @return a value of the removed element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     * @throws AbsenceElementException an exception that should be raised when user tries to remove missing element
     */
    public T remove(T value) throws EmptyListException, AbsenceElementException, AlreadyAddedElementException {
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

        throw new AbsenceElementException();
    }

    /**
     * A method that removes an element from the inputted index
     * @param index an index of the element that should be removed from the list
     * @return a value of the removed element
     * @throws EmptyListException an exception that should be raised when user tries to remove from empty list
     * @throws AbsenceElementException an exception that should be raised when user tries to remove missing element
     */
    public T remove(int index) throws EmptyListException, WrongIndexException, AlreadyAddedElementException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        if (index < 0 || index > length - 1) {
            throw new WrongIndexException();
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == length - 1) {
            return removeLast();
        }

        Node<T> current = head;
        Node<T> previous = null;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }

        length--;
        T removedValue = current.value;
        previous.next = current.next;

        return removedValue;
    }

    /** A method that checks if the element contains in the list */
    public boolean isContain(T value) {
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
     * @throws AbsenceElementException an exception that should be raised when user tries to remove missing element
     */
    public int getIndexOf(T value) throws EmptyListException, AbsenceElementException {
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

        throw new AbsenceElementException();
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

        private Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
