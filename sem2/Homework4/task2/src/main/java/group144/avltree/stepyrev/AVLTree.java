package group144.avltree.stepyrev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class that represents AVLTree, which can work with different Comparable types
 * @param <T> means type of elements that AVLTree keeps
 */
public class AVLTree<T extends Comparable<T>> implements Collection<T> {
    private NodeOperator root;
    private int size;

    /** A constructor of an AVLTree */
    public AVLTree() {
        root = new NodeOperator();
        size = 0;
    }

    /**
     * A method that returns a amount of the elements of the tree
     * @return means amount of the elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * A method that checks whether tree is empty
     * @return true if tree is empty and false if isn't
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * A method that checks if the inputted element in tree
     * @param o means value of the element
     * @return true if element is in tree and false if isn't
     */
    @Override
    public boolean contains(Object o) {

        return !isEmpty() && root.contain((T) o);
    }

    /**
     * A method that returns tree iterator.
     *
     * Iteration happens in descending order
     * @return new tree iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    /**
     * A method that returns an array of the elements
     * @return an array of the elements
     */
    @Override
    public Object[] toArray() {
        ArrayList<T> elements = new ArrayList<>();
        root.addAll(elements);

        return elements.toArray();
    }

    /**
     * A method that returns an array of tree elements
     * @param a the array in which elements should be added
     * @param <T1> the type of stored elements
     * @return an array of tree elements
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        ArrayList<T1> list = new ArrayList<>();
        for (T element : this) {
            list.add((T1) element);
        }

        return list.toArray(a);
    }

    /**
     * A method that adds a new value to the tree
     *
     * If the element already added to the tree, there wouldn't be
     * any error. Number of the element will be increased.
     * @param value means a value of the new element
     * @return true if element was successfully added
     */
    @Override
    public boolean add(T value) {
        root.add(value);
        size++;
        return true;
    }

    /**
     * A method that removes an element from the tree
     *
     * If the amount of the element is more than 1, number of it
     * will be decreased.
     * @param o means value of the removed element
     * @return
     */
    @Override
    public boolean remove(Object o) {
        if (root.remove((T) o)) {
            size--;
            return true;
        }

        return false;
    }

    /**
     * A method that checks if the collections of elements present in the tree
     * @param c means a collection of checked elements
     * @return true if all elements are in the tree and false if they aren't
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for (Object element : c) {
            result = result && contains(element);
        }

        return result;
    }

    /**
     * A method that removes elements from the collection from the tree
     * @param c means a collection of removing elements
     * @return true if at least one element was removed from the tree
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object element : c) {
            result = result || remove(element);
        }

        return result;
    }

    /**
     * A method that retains elements from the collection from the tree
     * @param c means collection of the elements
     * @return true if at least one element was removed
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (Object element : this) {
            if (!c.contains(element)) {
                remove(element);
                result = true;
            }
        }

        return result;
    }

    /** A method that clears the tree */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * A method that adds all elements from the collection to the tree
     * @param c means a collection of the elements
     * @return true if at least one element was added
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }

        return !c.isEmpty();
    }

    /** A class that represents tree iterator */
    private class TreeIterator implements Iterator<T> {
        ArrayList<T> elements;

        private TreeIterator() {
            elements = new ArrayList<>();
            root.addAll(elements);
        }

        @Override
        public boolean hasNext() {
            return !elements.isEmpty();
        }

        @Override
        public T next() {
            if (isEmpty()) {
                return null;
            }

            return elements.remove(0);
        }
    }

    /**
     * A method that prints the tree
     * @return printed tree
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "(null)";
        }

        return root.toString();
    }

    /** A class that represents tree element (node) */
    private class Node {
        private T value;
        private NodeOperator leftChild;
        private NodeOperator rightChild;
        private int height;
        private int quantity;

        private Node(T value) {
            this.value = value;
            height = 1;
            quantity = 1;
            leftChild = new NodeOperator();
            rightChild = new NodeOperator();
        }

        /** A method that updates a height of the element   */
        private void updateHeight() {
            int leftHeight = (leftChild.currentNode == null) ? 0 : leftChild.currentNode.height;
            int rightHeight = (rightChild.currentNode == null) ? 0 : rightChild.currentNode.height;

            height = ((leftHeight > rightHeight) ? leftHeight : rightHeight) + 1;
        }

        /** A method that returns a balance factor of the element */
        private int balanceFactor() {
            int leftHeight = (leftChild.currentNode == null) ? 0 : leftChild.currentNode.height;
            int rightHeight = (rightChild.currentNode == null) ? 0 : rightChild.currentNode.height;

            return rightHeight - leftHeight;
        }
    }

    /** A class that performs operations with node */
    private class NodeOperator {
        private Node currentNode;

        /**
         * A method that adds element to the tree
         * @param value means value of the added element
         */
        private void add(T value) {
            if (currentNode == null) {
                currentNode = new Node(value);
                return;
            }

            if (currentNode.value.equals(value)) {
                currentNode.quantity++;
                return;
            }

            if (currentNode.value.compareTo(value) > 0) {
                currentNode.leftChild.add(value);
            } else {
                currentNode.rightChild.add(value);
            }

            balance();
        }

        /**
         * A method that removes the element from the tree
         * @param value means value of the removed element
         * @return true if element was successfully removed
         */
        private boolean remove(T value) {
            boolean result = false;

            if (currentNode == null) {
                return false;
            }

            if (currentNode.value.equals(value)) {
                if (currentNode.quantity > 1) {
                    currentNode.quantity--;
                    return true;
                }

                removeNode();
                return true;
            }

            if (currentNode.value.compareTo(value) > 0) {
                result = currentNode.leftChild.remove(value);
            } else {
                result = currentNode.rightChild.remove(value);
            }

            currentNode.updateHeight();
            this.balance();
            return result;
        }

        /** A method that removes the node from the tree */
        private void removeNode() {
            if (currentNode.leftChild.currentNode == null && currentNode.rightChild.currentNode == null) {
                currentNode = null;
                return;
            }

            if (currentNode.leftChild.currentNode != null && currentNode.rightChild.currentNode == null) {
                currentNode = currentNode.leftChild.currentNode;
                return;
            }

            if (currentNode.leftChild.currentNode == null && currentNode.rightChild.currentNode != null) {
                currentNode = currentNode.rightChild.currentNode;
                return;
            }

            currentNode.value = currentNode.leftChild.removeRightest();
            currentNode.updateHeight();
        }

        /**
         * A method that removes the rightest node of the tree
         *
         * This method would be useful to method removeNode (in case when both children is present)
         * @return a removed rightest node
         */
        private T removeRightest() {
            if (currentNode.rightChild.currentNode != null) {
                T rightest = currentNode.rightChild.removeRightest();
                currentNode.updateHeight();
                this.balance();
                return rightest;
            } else {
                Node rightest = currentNode;
                this.removeNode();
                return rightest.value;
            }
        }

        /** A method that performs balance with the tree
         *
         * This method is used in method add and remove
         */
        private void balance() {
            currentNode.updateHeight();

            if (currentNode.balanceFactor() == 2) {
                if (currentNode.rightChild.currentNode.balanceFactor() < 0) {
                    currentNode.rightChild.rotateRight();
                }

                this.rotateLeft();
                return;
            }

            if (currentNode.balanceFactor() == -2) {
                if (currentNode.leftChild.currentNode.balanceFactor() > 0) {
                    currentNode.leftChild.rotateLeft();
                }

                this.rotateRight();
            }
        }

        /** A method that performs right rotate */
        private void rotateRight() {
            NodeOperator tmp = currentNode.leftChild;
            NodeOperator newNode = new NodeOperator();

            newNode.currentNode = currentNode;
            newNode.currentNode.leftChild = tmp.currentNode.rightChild;
            tmp.currentNode.rightChild = newNode;

            tmp.currentNode.updateHeight();
            newNode.currentNode.updateHeight();

            currentNode = tmp.currentNode;
        }

        /** A method that performs left rotate */
        private void rotateLeft() {
            NodeOperator tmp = currentNode.rightChild;
            NodeOperator newNode = new NodeOperator();

            newNode.currentNode = currentNode;
            newNode.currentNode.rightChild = tmp.currentNode.leftChild;
            tmp.currentNode.leftChild = newNode;

            tmp.currentNode.updateHeight();
            newNode.currentNode.updateHeight();

            currentNode = tmp.currentNode;
        }

        /** A method that checks if the value is in the tree
         *
         * @return true if value is in the tree and false if isn't
         * */
        private boolean contain(T value) {
            if (currentNode == null) {
                return false;
            }

            if (currentNode.value.equals(value)) {
                return true;
            }

            if (currentNode.value.compareTo(value) > 0) {
                return currentNode.leftChild.contain(value);
            } else {
                return currentNode.rightChild.contain(value);
            }
        }

        /**
         * A method that converts node to the string
         * @return node in a line representation
         */
        @Override
        public String toString() {
            String result = "(" + currentNode.value + "{" + currentNode.quantity + "} ";
            if (currentNode.leftChild.currentNode != null) {
                result = result + currentNode.leftChild.toString();
            } else {
                result = result + "null";
            }

            result = result + " ";

            if (currentNode.rightChild.currentNode != null) {
                result = result + currentNode.rightChild.toString();
            } else {
                result = result + "null";
            }

            result = result + ")";
            return result;
        }

        /**
         * A method that adds of elements of the tree to the array
         *
         * This method is useful for converting tree to the array
         * @param elements means an array in which elements should be added
         */
        private void addAll(ArrayList<T> elements) {
            if (currentNode == null) {
                return;
            }

            currentNode.leftChild.addAll(elements);
            for (int i = 0; i < currentNode.quantity; i++) {
                elements.add(currentNode.value);
            }
            currentNode.rightChild.addAll(elements);
        }
    }
}
