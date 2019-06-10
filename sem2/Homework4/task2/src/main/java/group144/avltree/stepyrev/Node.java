package group144.avltree.stepyrev;

import java.util.ArrayList;

/** A class that represents a node of the balanced tree. */
public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> parent;
    private int height = 1;

    /**
     * A constructor of node.
     * @param value - a value of the node
     * @param parent - a parent of the node
     */
    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    /**
     * A method that adds a new value to the tree.
     * @param value - a value of new element
     * @param tree - a tree in which new node should be added
     */
    public void addNode(T value, AVLTree<T> tree) {
        if (value.compareTo(this.value) == 0) {
            return;
        }

        if (value.compareTo(this.value) < 0) {
            if (leftChild == null) {
                leftChild = new Node<>(value, this);
            } else {
                leftChild.addNode(value, tree);
            }
        } else if (value.compareTo(this.value) > 0) {
            if (rightChild == null) {
                rightChild = new Node<>(value, this);
            } else {
                rightChild.addNode(value, tree);
            }
        }

        balance(tree);
    }

    /**
     * A method that removes a node from the tree.
     * @param value - a value of removed node
     * @param tree - a tree from which a node should be removed
     * @return - true if a node was removes successfully, false otherwise
     */
    public boolean remove(T value, AVLTree<T> tree) {
        boolean result;

        if (this.value.equals(value)) {
            removeNode(tree);
            return true;
        }

        if (value.compareTo(this.value) < 0) {
            result = leftChild.remove(value, tree);
        } else {
            result = rightChild.remove(value, tree);
        }

        updateHeight();
        balance(tree);
        return result;
    }

    /**
     * A method that checks if the node contains in tree.
     * @param value - a value of checked node
     * @return - true if contains, false otherwise
     */
    public boolean containsNode(T value) {
        if (this.value.equals(value)) {
            return true;
        }

        if (value.compareTo(this.value) < 0 && leftChild != null) {
            return leftChild.containsNode(value);
        } else if (value.compareTo(this.value) > 0 && rightChild != null){
            return rightChild.containsNode(value);
        }

        return false;
    }

    /**
     * A method that translates a tree to the string.
     * @return - a string with tree elements
     */
    public String toString() {
        StringBuilder result = new StringBuilder("(");
        result.append(this.value);
        result.append(" ");
        if (leftChild != null) {
            result.append(leftChild.toString());
        } else {
            result.append("null");
        }

        result.append(" ");

        if (rightChild != null) {
            result.append(rightChild.toString());
        } else {
            result.append("null");
        }

        result.append(")");
        return result.toString();
    }

    /**
     * A method that gets all elements of the tree to a list.
     * @param elements - a list with elements
     * @return - a list with added elements
     */
    public ArrayList<T> getAllToList(ArrayList<T> elements) {
        if (leftChild != null) {
            elements = leftChild.getAllToList(elements);
        }
        elements.add(this.value);
        if (rightChild != null) {
            elements = rightChild.getAllToList(elements);
        }

        return elements;
    }

    private void removeNode(AVLTree<T> tree) {
        if (leftChild == null && rightChild == null) {
            if (isRoot(this.value, tree.getRoot())) {
                tree.setRoot(null);
                return;
            }

            changeParentChildren(null);
            if (parent == null) {
                tree.setRoot(null);
            }
            return;
        }

        if (leftChild != null && rightChild == null) {
            if (!setNewRoot(value, tree, leftChild)){
                changeParentChildren(leftChild);
            }
            return;
        }

        if (leftChild == null && rightChild != null) {
            if (!setNewRoot(value, tree, rightChild)){
                changeParentChildren(rightChild);
            }

            return;
        }

        this.value = leftChild.removeRightest(tree);
        updateHeight();
        balance(tree);
    }

    private boolean setNewRoot(T value, AVLTree<T> tree, Node<T> newRoot) {
        if (isRoot(value, tree.getRoot())) {
            tree.setRoot(newRoot);
            return true;
        }

        return false;
    }

    private boolean isEqualNode(Node<T> node) {
        return this.equals(node);
    }

    private boolean isRoot(T value, Node<T> root) {
        return value.equals(root.value);
    }

    private void changeParentChildren(Node<T> newChild) {
        if (isEqualNode(parent.leftChild)) {
            parent.leftChild = newChild;
        } else {
            parent.rightChild = newChild;
        }
    }

    private T removeRightest(AVLTree<T> tree) {
        if (rightChild != null) {
            T rightest = rightChild.removeRightest(tree);
            updateHeight();
            balance(tree);
            return rightest;
        } else {
            T rightest = this.value;
            removeNode(tree);
            return rightest;
        }
    }


    /** A method that performs balance with the tree.
     *
     * This method is used in method add and remove.
     */
    private void balance(AVLTree<T> tree) {
        updateHeight();

        if (balanceFactor() == 2) {
            if (rightChild.balanceFactor() < 0) {
                rightChild.rotateRight(tree);
            }

            rotateLeft(tree);
            return;
        }

        if (balanceFactor() == -2) {
            if (leftChild.balanceFactor() > 0) {
                leftChild.rotateLeft(tree);
            }

            rotateRight(tree);
        }
    }

    /** A method that updates a height of the element. */
    private void updateHeight() {
        int leftHeight = (leftChild == null) ? 0 : leftChild.height;
        int rightHeight = (rightChild == null) ? 0 : rightChild.height;

        height = ((leftHeight > rightHeight) ? leftHeight : rightHeight) + 1;
    }

    /** A method that returns a balance factor of the element. */
    private int balanceFactor() {
        int leftHeight = (leftChild == null) ? 0 : leftChild.height;
        int rightHeight = (rightChild == null) ? 0 : rightChild.height;

        return rightHeight - leftHeight;
    }

    /** A method that performs right rotate. */
    private void rotateRight(AVLTree<T> tree) {
        Node<T> tmp = leftChild; // set this node
        leftChild = tmp.rightChild;

        if (tmp.rightChild != null) { // set tmp right child
            tmp.rightChild.parent = this;
        }
        tmp.rightChild = this;

        if (parent == null) { // set this parent children
            tree.setRoot(tmp);
        } else {
            if (isEqualNode(rightChild.parent)) {
                parent.rightChild = tmp;
            } else {
                parent.leftChild = tmp;
            }
        }

        tmp.parent = parent;
        parent = tmp;

        updateHeight();
        tmp.updateHeight();
    }

    /** A method that performs left rotate. */
    private void rotateLeft(AVLTree<T> tree) {
        Node<T> tmp = rightChild; // set this node
        rightChild = tmp.leftChild;

        if (tmp.leftChild != null) { // set tmp right child
            tmp.leftChild.parent = this;
        }
        tmp.leftChild = this;

        if (parent == null) { // set this parent children
            tree.setRoot(tmp);
        } else {
            if (leftChild != null && isEqualNode(leftChild.parent)) {
                parent.leftChild = tmp;
            } else {
                parent.rightChild = tmp;
            }
        }

        tmp.parent = parent;
        parent = tmp;

        updateHeight();
        tmp.updateHeight();
    }
}
