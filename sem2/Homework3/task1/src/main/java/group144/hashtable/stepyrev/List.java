package group144.hashtable.stepyrev;


/**
 * A class that represents singly connected list
 * @param <Type> a type of elements that present in the list
 */
public class List<Type> {
    private Node head;
    private int length;

    /**
     * A method that checks if the list is empty
     * @return true if list is empty and false if is not
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * A method adding a new element to the top of the list
     * @param value means value of the element that user wants to add to List
     */
    public void add(Type value) {
        if (isEmpty()) {
            head = new Node(value);
            length = 1;
        }
        else {
            head = new Node(value, head);
            length++;
        }
    }


    /**
     * A method that removes element from the list
     * @param element means value that should be removed from the list
     */
    public void remove(Type element) {
        if (isEmpty()) {
            return;
        }

        if (head.value.equals(element)) {
            head = head.next;
            length--;
            return;
        }

        Node currentNode = head;
        Node previousNode = null;
        while (!currentNode.value.equals(element) && currentNode != null) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode != null) {
            previousNode = currentNode.next;
        }
    }

    /**
     * A method finding the element in the list
     * @param value means value of element that user wants to find
     * @return false if not found and true if element is found
     */
    public boolean find(Type value) {
        if (isEmpty()) {
            return false;
        }

        Node current = this.head;
        while (current != null && !current.value.equals(value)) {
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        return true;
    }

    /**
     * A method that gets element from the list by index
     * @param index means index of element that should be getted
     * @return value of getted element
     * @throws WrongInputException an exception that should be raised if user tries to do incorrect actions
     */
    public Type getByIndex(int index) throws WrongInputException {
        if (index < 0 || isEmpty() || index > length - 1) {
            throw new WrongInputException("Incorrect actions with List!");
        }

        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.value;
    }

    /**
     * A method that returns length of the list
     * @return length of the list
     */
    public int getLength() {
        return length;
    }

    /** A method printing the list */
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
        }

        private Node(Type value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}