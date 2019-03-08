package group144.steperev_daniil;

/** A singly connected List */
public class List<Type> {
    private Node head = null;
    private int length = 0;

    private boolean isEmpty() {
        return head == null;
    }

    /**
     * A method adding a new element to the end of the list
     *
     * @param value means value of the element that user wants to add to List
     */
    public void addLast(Type value) {
        if (isEmpty()) {
            head = new Node(value);
            length = 1;
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = new Node(value);
            length++;
        }
    }

    /**
     * A method that adds a new value to the index to the list
     *
     * @param value means value of the element that user wants to add to List
     * @param index means positions where new element should be inserted
     */
    public void add(Type value, int index) {
        if (index < 0) {
            return;
        }

        if (isEmpty()) {
            head = new Node(value);
            length = 1;
            return;
        }

        if (length - 1 < index) {
            addLast(value);
            return;
        }

        length++;

        if (index == 0) {
            Node previousHead = this.head;
            this.head = new Node(value, previousHead);
            return;
        }

        Node current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = new Node(value, current.next);
    }

    /** *A method that removes last element from the list */
    public void removeLast() {
        if (isEmpty()) {
            return;
        }

        if (length == 1) {
            head = null;
            length--;
            return;
        }

        length--;

        Node current = this.head;
        while (current.next.next != null) {
            current = current.next;
        }

        current.next = null;
    }

    /**
     * A method that removes element from the index from the list
     *
     * @param index means index of the element that user wants to remove
     */
    public void remove(int index) {
        if (isEmpty() || length - 1 < index || index < 0) {
            return;
        }

        if (length - 1 == index) {
            removeLast();
            return;
        }

        if (index == 0) {
            head = head.next;
            length--;
            return;
        }

        length--;

        Node current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;
    }

    /**
     * A method  that finds the element in the list
     *
     * @param value means value of element that user wants to find
     * @return index means index of element that was found in List (-1 if not found)
     */
    public int find(Type value) {
        if (isEmpty()) {
            return -1;
        }

        int index = 0;

        Node current = this.head;
        while (current != null && !current.value.equals(value)) {
            current = current.next;
            index++;
        }

        if (current == null) {
            return -1;
        }

        return index;
    }

    public int getLength() {
        return length;
    }

    /** A method that prints the list */
    public void printList() {
        Node currentNode = head;
        for (int i = 0; i < length; i++) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
    }

    private class Node {
        private Type value;
        private Node next;

        private Node(Type value) {
            this.value = value;
            this.next = null;
        }

        private Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
