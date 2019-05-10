package group144.stepyrev;

import java.util.LinkedList;

/** An interface that realizes a linked list comparator. */
public interface ListsComparator {
    /**
     * A method that compares two linked lists due to their lengths.
     * @param first - a first linked list
     * @param second - a second linked list
     * @return - difference between lists
     */
    static int compare(LinkedList first, LinkedList second) {
        return first.size() - second.size();
    }
}
