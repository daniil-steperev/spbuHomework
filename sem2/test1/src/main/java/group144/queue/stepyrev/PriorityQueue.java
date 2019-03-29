package group144.queue.stepyrev;

/**
 * A class that represents a priority queue based on linked list.
 *
 * Values in the queue are stored by descending of their priorities.
 * @param <T> means a type of value of the stored elements
 */
public class PriorityQueue<T> {
    private Node<T> head;
    private int length;

    /**
     * A method that checks if the queue is empty.
     * @return true if the queue is empty and false if isn't
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * A method that adds a new element to the queue.
     * @param value means a value of the new element
     * @param priority means a priority of the new element
     */
    public void enqueue(T value, int priority) {
        length++;

        if (isEmpty()) {
            head = new Node<>(value, priority);
            return;
        }

        if (priority > head.priority) {
            head = new Node<>(value, priority, head);
            return;
        }

        if (length == 1) { // we should check this case because of using current.next in the loop
            head.next = new Node<>(value, priority);
            return;
        }

        Node<T> current = head;
        while (current.next != null && current.next.priority >= priority) { // >= because elements with the same priorities should be removed according to their order of addition
            current = current.next;
        }

        Node<T> currentNext = current.next;
        current.next = new Node<>(value, priority, currentNext);
    }

    /**
     * A method that removes the first element with the biggest priority from the queue.
     * @return a value of the removed element
     * @throws EmptyQueueException an exception that should be thrown if the queue is empty
     */
    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        length--;
        T removedValue = head.value;
        head = head.next;

        return removedValue;
    }

    /**
     * A method that returns a length of the queue.
     * @return means a length of the queue
     */
    public int getLength() {
        return length;
    }

    /**
     * A method that prints the queue.
     * @return a String with the queue elements which are separated by spaces
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }

        StringBuilder elements = new StringBuilder();
        Node<T> current = head;

        while (current != null) {
            elements.append(current.value);

            if (current.next != null) { // last element should be added without a space
                elements.append(" ");
            }

            current = current.next;
        }

        return elements.toString();
    }

    /**
     * A nested class that represents an element of the queue.
     * @param <T> means a type of value of the element
     */
    private class Node<T> {
        private T value;
        private int priority;
        private Node<T> next;

        private Node(T value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        private Node(T value, int priority, Node<T> next) {
            this.value = value;
            this.priority = priority;
            this.next = next;
        }
    }
}

