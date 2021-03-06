package group144.stepyrev_daniil;

/**
 *A class that realizes ordinary stack
 */
public class Stack<Type> {
    private class Node {
        private Type value = null;
        private Node next = null;

        private Node(Type value) {
            this.value = value;
            this.next = null;
        }

        private Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }

        private Type getValue() {
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
     * A method that puts element to the top of stack
     *
     * @param value means value of the element that user wants to push to the Stack
     */
    public void push(Type value) {
        Node newElement = new Node(value, head);
        length++;
        head = newElement;
    }

    /**
     * A method that delets top element from the stack
     */
    public Type pop() {
        if (isEmpty()) {
            return null;
        }

        Type valueOfFirst = head.getValue();
        head = head.next;
        length--;

        return valueOfFirst;
    }

    /**
     * A method that prints the stack
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
