package group144.avltree.stepyrev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class that represents AVLTree, which can work with different Comparable types
 * @param <T> means type of elements that AVLTree keeps
 */
public class AVLTree<T extends Comparable<T>> implements Collection<T> {
    private Node<T> root;
    private int size;

    /** A constructor of an AVLTree */
    public AVLTree() {
        size = 0;
    }

    public void setRoot(Node<T> root) {
      this.root = root;
    }

    public Node<T> getRoot() {
        return root;
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
        return size == 0;
    }

    /**
     * A method that checks if the inputted element in tree
     * @param o means value of the element
     * @return true if element is in tree and false if isn't
     */
    @Override
    public boolean contains(Object o) {
        return !isEmpty() && root.containsNode((T) o);
    }

    /**
     * A method that returns tree iterator.
     *
     * Iteration happens in descending order
     * @return new tree iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator(this);
    }

    /**
     * A method that returns an array of the elements
     * @return an array of the elements
     */
    @Override
    public Object[] toArray() {
        ArrayList<T> elements = new ArrayList<>();
        root.getAllToList(elements);

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
        if (root == null) {
            root = new Node<>(value, null);
            size++;
            return true;
        }

        if (contains(value)) {
            return false;
        }

        root.addNode(value, this);
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
        if (root.remove((T) o, this)) {
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
}
