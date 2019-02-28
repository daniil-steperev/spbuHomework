package group144.calculator.stepyrev;

import java.util.Currency;

/**
 * An implementation of stack that based on using linked list idea
 * @param <Type> means type of objects that stack is consisted of
 */
public class ListStack<Type> implements Stack<Type> {
    private class Node {
        private Type value;
        private Node next;

        private Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }

        private Node(Type value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head = null;
    private int length = 0;

    @Override
    public void push(Type value) {
        if (isEmpty()) {
            head = new Node(value);
            length++;
            return;
        }

        Node previousHead = head;
        Node newHead = new Node(value, previousHead);

        head = newHead;
        length++;
    }

    @Override
    public Type pop() {
        if (isEmpty()) {
            return null;
        }

        Type returnValue = head.value;

        head = head.next;
        length--;
        return returnValue;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getLength() {
        return length;
    }

    public void print() {
        if (isEmpty()) {
            return;
        }

        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
        }
     }
}
