package group144.stepyrev;

import java.util.LinkedList;

/** A class that intended to add an array of strings to a sorted set. */
public class Application {
    private SortedSet<String> sortedSet = new SortedSet<>();

    /**
     * A method that adds an array of strings to the sorted set.
     * @param strings - an array of strings, which words should be added
     */
    public void add(String[] strings) {
        for (String line : strings) {
            LinkedList<String> currentList = new LinkedList<>();

            String[] separatedWords = line.split(" ");
            for (String word : separatedWords) {
                currentList.add(word);
            }

            sortedSet.add(currentList);
        }
    }

    /** A method that prints the set.
     *  @return - a string with elements */
    public String printSet() {
        sortedSet.print();
        return sortedSet.getElements();
    }
}
