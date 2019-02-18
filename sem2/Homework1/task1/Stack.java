package group144.stepyrev_daniil;

/**
 *
 */
public class Stack {
    private class Node {
        private int value = 0;
        private Node next = null;

        private Node(int value) {
            this.value = value;
            this.next = null;
        }

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        private int getValue() {
            return this.value;
        }

        private void setNextNode(Node node) {
            this.next = node;
        }
    }

    private Node head = null;
    private int length = 0;

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    /**
     * A function that puts element to the top of stack
     */
    public void push(int value) {
        Node newElement = new Node(value, head);
        length++;
        head = newElement;
    }

    /**
     * A function that delets top element from the stack
     */
    public int pop() {
        if (isEmpty()) {
            return -1;
        }

        int valueOfFirst = head.getValue();
        head = head.next;

        return valueOfFirst;
    }

    /**
     * A function that prints the stack
     */
    public void print() {
        if (isEmpty()) {
            return;
        }

        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
    }
}
