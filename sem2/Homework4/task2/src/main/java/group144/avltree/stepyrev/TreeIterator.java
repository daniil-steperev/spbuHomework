package group144.avltree.stepyrev;

import java.util.ArrayList;
import java.util.Iterator;

/** A class that represents a tree iterator. */
public class TreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private AVLTree<T> tree;
    private ArrayList<T> elements;

    /**
     * A method that initializes a tree iterator.
     * @param tree - a tree which iterator should be initialized
     */
    public TreeIterator(AVLTree<T> tree) {
        this.tree = tree;
        elements = new ArrayList<>();
        if (tree.isEmpty()) {
            return;
        }
        elements = tree.getRoot().getAllToList(elements);
    }

    /**
     * A method that checks if the element has next.
     * @return - true if it is, false otherwise
     */
    @Override
    public boolean hasNext() {
        return !elements.isEmpty();
    }

    /**
     * A method that returns a value of next element.
     * @return - a value of next element if it is, null otherwise
     */
    @Override
    public T next() {
        if (elements.isEmpty()) {
            return null;
        }

        return elements.remove(0);
    }
}
