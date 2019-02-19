package group144.steperev_daniil;

/**
 * A singly connected List
*/
public class List<Type> {
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

        private Type getValue() {
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
    public void add(Type value) {
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
    public void add(Type value, int index) {
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
    public void remove() {
        if (isEmpty()) {
            return;
        }

        length--;

        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        current = null;
    }

    /**
     * A function removing element from the index from the list
     */
    public void remove(int index) {
        if (isEmpty() || length - 1 < index) {
            return;
        }

        if (length - 1 == index) {
            remove();
            return;
        }

        length--;

        Node current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNextNode();
        }

        current.setNextNode(current.next.next);
    }

    /**
     * A function finding the element in the list
     */
    public int find(Type value) {
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
