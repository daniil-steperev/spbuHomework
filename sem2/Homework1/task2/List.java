package group144.steperev_daniil;

/**
 * A singly connected List
*/
public class List {
    private class Node {
        private int value;
        private Node next;

        private Node(int value) {
            this.value = value;
            this.next = null;
        }

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        private int getValue() {
            return value;
        }

        private void setNextNode(Node node) {
            this.next = node;
        }

        private Node getNextNode() {
            return this.next;
        }
    }

    private Node head;
    private int length;

    public List() {
        head = null;
        length = 0;
    }

    private boolean isEmpty() {
        return (head == null);
    }

    /**
     * A function adding a new element to the end of the list
     */
    public void add(int value) {
        if (isEmpty()) {
            head = new Node(value);
            length = 1;
        }
        else {
            Node current = this.head;
            while (current.next != null) {
                current = current.getNextNode();
            }

            current.setNextNode(new Node(value));
            length++;
        }
    }

    /**
     * A function adding a new value to the index to the list
     */
    public void add(int value, int index) {
        if (isEmpty()) {
            head = new Node(value);
            length = 1;
            return;
        }

        if (length - 1 < index || index < 0) {
            add(value);
            return;
        }

        length++;

        if (index == 0) {
            Node previousHead = this.head;
            this.head = new Node(value, previousHead);
            return;
        }

        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNextNode();
        }

        current.setNextNode(new Node(value, current.next));
    }

    /**
     *A fuction removing last element from the list
     */
    public int remove() {
        if (isEmpty()) {
            return -1;
        }

        length--;

        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        int value = current.getValue();
        current = null;

        return value;
    }

    /**
     * A function removing element from the index from the list
     */
    public int remove(int index) {
        if (isEmpty() || length - 1 < index) {
            return -1;
        }

        if (length - 1 == index) {
            return remove();
        }

        length--;

        Node current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNextNode();
        }

        int value = current.next.getValue();
        current.setNextNode(current.next.next);

        return value;
    }

    /**
     * A function finding the element in the list
     */
    public int find(int value) {
        if (isEmpty()) {
            return -1;
        }

        int index = 0;

        Node current = this.head;
        while (current != null && current.getValue() != value) {
            current = current.getNextNode();
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

    /**
     * A function printing the list
     */
    public void printList() {
        Node currentNode = head;
        for (int i = 0; i < length; i++) {
            System.out.print(currentNode.getValue() + " ");
            currentNode = currentNode.getNextNode();
        }
    }
}
