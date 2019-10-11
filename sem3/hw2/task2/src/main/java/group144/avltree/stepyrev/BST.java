package group144.avltree.stepyrev;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that represents BST, which can work with different Comparable types
 * @param <T> means type of elements that BST keeps
 */
public class BST<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;
    private int size;

    /** A constructor of an BST */
    public BST() {
        size = 0;
    }

    /**
     * A method that adds node to the bst tree.
     * @param value - a value of added node
     *
     * If there is node with such value in the tree, size doesn't increment.
     */
    public void add(T value) {
        if (isEmpty()) {
            root = new Node<>(value, null);
            size++;
            return;
        }

        if (addNode(root, value)) {
            size++;
        }
    }

    /**
     * A method that adds node to the tree.
     * @param node - a current node
     * @param value - a value of the new node
     * @return - true if node was successfully added, false otherwise
     *
     * Successfully added means that there is not node with such value in the tree.
     */
    private boolean addNode(Node<T> node, T value) {
        if (node.getValue().equals(value)) {
            return false;
        }

        if (value.compareTo(node.getValue()) > 0) {
            if (node.getRightChild() != null) {
                return addNode(node.getRightChild(), value);
            }

            node.setRightChild(new Node<>(value, node));
            return true;
        }

        if (value.compareTo(node.getValue()) < 0) {
            if (node.getLeftChild() != null) {
                return addNode(node.getLeftChild(), value);
            }

            node.setLeftChild(new Node<>(value, node));
            return true;
        }

        return false;
    }

    /**
     * A method that checks if the value is in the tree.
     * @param value - a value of the checked element
     * @return - true if element contains, false otherwise
     */
    public boolean contains(T value) {
        return (!isEmpty()) && (containsNode(root, value));
    }

    /**
     * A method that checks if the value is in the tree.
     * @param node - a current checked node
     * @param value - a value of the checked element
     * @return - true if element contains, false otherwise
     */
    private boolean containsNode(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (node.value.equals(value)) {
            return true;
        }

        if (value.compareTo(node.getValue()) > 0) {
            return containsNode(node.getRightChild(), value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            return containsNode(node.getLeftChild(), value);
        }

        return false;
    }

    /**
     * A method that removes the value from the tree.
     * @param value - a value of the removed element
     */
    public void remove(T value) {
        if (!isEmpty() && removeNode(root, value)) {
            size--;
        }
    }

    /**
     * A method that returns the size of the tree.
     * @return - the size of the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * A method that remoes the node from the tree.
     * @param node - a current node
     * @param value - a value of the removed element
     * @return - true if the node was removed, false otherwise
     */
    private boolean removeNode(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (node.value.equals(value)) {
            removeFromTree(node);
            return true;
        }

        if (value.compareTo(node.getValue()) > 0) {
            return removeNode(node.getRightChild(), value);
        }

        if (value.compareTo(node.getValue()) < 0) {
            return removeNode(node.getLeftChild(), value);
        }

        return false;
    }

    /**
     * A method that checks if the element is root.
     * @param node - a checked element
     * @return - true if it is a root, false otherwise
     */
    private boolean isRoot(Node<T> node) {
        return root.equals(node);
    }

    /**
     * A method that removes a node from the tree.
     * @param node - the node that should be removed
     */
    private void removeFromTree(Node<T> node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (isRoot(node)) {
                root = null;
                return;
            }

            if (node.parent.leftChild.equals(node)) {
                node.parent.leftChild = null;
            } else {
                node.parent.rightChild = null;
            }
            return;
        }

        if (node.getLeftChild() != null && node.getRightChild() == null) {
            if (isRoot(node)) {
                root = node.leftChild;
                root.parent = null;
                return;
            }

            if ((node.parent.rightChild != null) && (node.parent.leftChild.equals(node))) {
                node.parent.leftChild = node.leftChild;
                node.leftChild.parent = node.parent;
            } else {
                node.parent.rightChild = node.leftChild;
                node.leftChild.parent = node.parent;
            }

            return;
        }

        if (node.getLeftChild() == null && node.rightChild != null) {
            if (isRoot(node)) {
                root = node.rightChild;
                root.parent = null;
                return;
            }

            if ((node.parent.leftChild != null) && (node.parent.leftChild.equals(node))) {
                node.parent.leftChild = node.rightChild;
                node.rightChild.parent = node.parent;
            } else {
                node.parent.rightChild = node.rightChild;
                node.rightChild.parent = node.parent;
            }

            return;
        }

        // both are not null
        node.value = removeRightest(node);
    }

    /**
     * A method that removes the rightest node.
     * @param node - the node, which rightest should be remove
     * @return - a value of the rightest node
     */
    private T removeRightest(Node<T> node) {
        if (node.rightChild != null) {
            return removeRightest(node.rightChild);
        }

        T value = node.value;
        removeFromTree(node);

        return value;
    }

    /**
     * A method that checks if the tree is empty.
     * @return - true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * A method that returns tree iterator.
     *
     * Iteration happens in descending order
     * @return - a new tree iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }


    /**
     * A method that prints the tree
     * @return - a printed tree
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "(null)";
        }

        return root.toString();
    }

    /** A class that represents a node of the bts tree. */
    private class Node<T> {
        private T value;
        private Node<T> leftChild;
        private Node<T> rightChild;
        private Node<T> parent;

        /**
         * A constructor of node.
         * @param value - a value of the node
         * @param parent - a parent of the node
         */
        private Node(T value, Node<T> parent) {
            this.value = value;
            this.parent = parent;
        }

        /**
         * A method that returns the left child.
         * @return - the left child
         */
        private Node<T> getLeftChild() {
            return leftChild;
        }

        /**
         * A method that returns the right child.
         * @return - the right child
         */
        private Node<T> getRightChild() {
            return rightChild;
        }

        /**
         * A method that returns the parent.
         * @return - the parent
         */
        private Node<T> getParent() {
            return parent;
        }

        /**
         * A method that returns the value.
         * @return - the value
         */
        private T getValue() {
            return value;
        }

        /**
         * A method that sets the left child.
         * @param left - a new left child
         */
        private void setLeftChild(Node<T> left) {
            leftChild = left;
        }

        /**
         * A method that sets the right child.
         * @param right - a new right child
         */
        private void setRightChild(Node<T> right) {
            rightChild = right;
        }

        /**
         * A method that adds the converts the element to the string
         * @return - a string with elements
         */
        @Override
        public String toString() {
            StringBuilder string = new StringBuilder();

            string.append(value);
            string.append(" ");
            if (leftChild != null) {
                string.append(leftChild.toString());
            }

            string.append(" ");
            if (rightChild != null) {
                string.append(rightChild.toString());
            }

            return string.toString();
        }
    }

    /** A class that represents a tree iterator. */
    private class TreeIterator implements Iterator<T> {
        /** A current element. */
        private Node<T> current;
        /** A next element. */
        private Node<T> next;

        /** A method that initializes a tree iterator. */
        private TreeIterator() {
            current = null;
            next = root;

            while ((next != null) && (next.getLeftChild() != null)) {
                next = next.getLeftChild();
            }
        }

        /**
         * A method that checks if the element has next.
         * @return - true if it is, false otherwise
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * A method that returns a value of next element.
         * @return - a value of next element if it is, null otherwise
         */
        @Override
        public T next() {
            if (next == null) {
                throw new NoSuchElementException();
            }

            current = next;
            next = getNext();
            return current.getValue();
        }

        /**
         * A method that returns the next after 'next' element.
         * @return - the next element
         */
        private Node<T> getNext() {
            Node<T> tmp;

            if (next.getRightChild() != null) {
                tmp = next.getRightChild();
                while (tmp.getLeftChild() != null) {
                    tmp = tmp.getLeftChild();
                }

                return tmp;
            }

            tmp = next;
            while (isPossibleToStepUp(tmp)) { // get one step up
                tmp = tmp.getParent();
            }

            return tmp.getParent(); // we checked all left children and all right ones that's why we should check parent
        }

        /** A method that removes the current element from the tree. */
        @Override
        public void remove() {
            if (current != null) {
                BST.this.remove(current.getValue());
            }
        }

        private boolean isPossibleToStepUp(Node<T> node) {
            return node.getParent() != null &&
                    node.getParent().rightChild != null &&
                    node.getParent().getRightChild().equals(node);
        }
    }
}
