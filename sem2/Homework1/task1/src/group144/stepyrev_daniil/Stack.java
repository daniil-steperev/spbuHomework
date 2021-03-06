package group144.stepyrev_daniil;

/** A class that realizes ordinary stack */
public class Stack<Type> {
    private Node head = null;
    private int length = 0;

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * A method that puts element to the top of stack
     *
     * @param value means value of the element that user wants to push to the Stack
     */
    public void push(Type value) {
        length++;
        head = new Node(value, head);
    }

    /**
     * A method that deletes top element from the stack
     * @return means element that was deleted from stack (if stack is empty returns null)
     */
    public Type pop() {
        if (isEmpty()) {
            return null;
        }

        Type valueOfFirst = head.value;
        head = head.next;
        length--;

        return valueOfFirst;
    }

    /** A method that prints the stack */
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

    private class Node {
        private Type value;
        private Node next;

        private Node(Type value) {
            this.value = value;
        }

        private Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
