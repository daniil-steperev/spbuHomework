package group144.stepyrev;

import java.util.LinkedList;

import static group144.stepyrev.ListsComparator.compare;

/** A class that represents a sorted set of linked lists. */
public class SortedSet<T> {
    private Node<T> root = null;
    private int size = 0;

    /**
     * A method that adds a new element to the set.
     * @param element - an element that should be added
     * @return - true if element was added successfully, false otherwise
     */
    public boolean add(LinkedList<T> element) {
        if (isEmpty()) {
            root = new Node(element);
            size++;
            return true;
        }

        if (compare(root.value, element) > 0) {
            Node<T> previousRoot = root;
            root = new Node(element, previousRoot);
            size++;
            return true;
        }

        if (size == 1) {
            if (compare(root.value, element) == 0) {
                return false;
            }

            root.next = new Node(element);
            size++;
            return true;
        }

        Node<T> previous = null;
        Node<T> current = root;
        while (current.next != null && compare(current.value, element) < 0) {
            previous = current;
            current = current.next;
        }

        if (compare(current.value, element) == 0) {
            return false;
        }

        size++;
        previous.next = new Node(element, current);
        return true;
    }

    /** A method that prints all elements from the set. */
    public void print() {
        System.out.println(getElements());
    }

    /**
     * A method that gets a string with all elements of the set.
     * @return - a string with all elements
     */
    public String getElements() {
        if (isEmpty()) {
            return new String();
        }

        StringBuilder result = new StringBuilder();
        Node<T> current = root;
        while (current != null) {
            result.append(getListElements(current.value));

            current = current.next;
        }

        return result.toString();
    }

    private String getListElements(LinkedList<T> list) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
            result.append(" ");
        }

        return result.toString();
    }

    /**
     * A method that gets a set size.
     * @return - a set size
     */
    public int getSize() {
        return size;
    }

    /**
     * A method that checks if the set is empty.
     * @return - true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node<T> {
        private LinkedList<T> value;
        private Node next = null;

        private Node(LinkedList<T> value) {
            this.value = value;
        }

        private Node(LinkedList<T> value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
