package group144.stepyrev;

import java.io.*;
import java.util.HashMap;

/** A class that represents a structure trie. */
public class Trie implements Serializable {
    private Node root;

    public Trie() {
        root = new Node();
    }

    /**
     * A method that adds a new element to the trie.
     * @param element - a new added element
     * @return - true if element was successfully added, false whether the element is present in the trie
     */
    public boolean add(String element) {
        if (element == null || contains(element)) {
            return false;
        }

        Node current = root;
        current.prefixElements++;
        int currentIndex = 0;
        char currentValue = 'x';
        while (currentIndex < element.length()) {
            currentValue = element.charAt(currentIndex);
            if (!current.children.containsKey(currentValue)) {
                addToChild(current, currentValue);
            }

            current = current.children.get(currentValue);
            current.prefixElements++;

            if (current.content.equals(element)) {
                current.isEndOfWord = true;
                return true;
            }

            currentIndex++;
        }

        return false;
    }

    /**
     * A method that adds a new value to the children map.
     * @param parent - an element to which newValue will be added
     * @param newValue - a new value of the element
     */
    private void addToChild(Node parent, Character newValue) {
        StringBuilder newContent = new StringBuilder(parent.content);
        newContent.append(newValue);

        Node newChild = new Node(newContent.toString());
        parent.children.put(newValue, newChild);
    }

    /**
     * A method that checks if the element is in the trie.
     * @param element - a checked element
     * @return - true if element contains in trie, false if isn't
     */
    public boolean contains(String element) {
        if (element == null) {
            return false;
        }

        Node current = root;
        char currentValue = 'x';
        for (int i = 0; i < element.length(); i++) {
            currentValue = element.charAt(i);
            Node node = current.children.get(currentValue);

            if (node == null) {
                return false;
            }
            current = node;
        }

        return current.isEndOfWord;
    }

    /**
     * A method that removes the element from the trie.
     * @param element - a removed element
     * @return - true if element was successfully removed, false if element isn't in the trie
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }

        Node current = root;
        Node parent = null;
        current.prefixElements--;
        char currentValue = 'x';
        for (int i = 0; i < element.length(); i++) {
            currentValue = element.charAt(i);
            parent = current;

            current = current.children.get(currentValue);
            current.prefixElements--;

            if (current.prefixElements == 0) {
                parent.children.remove(currentValue);
                return true;
            }
        }

        current.isEndOfWord = false;
        return true;
    }

    /**
     * A method that returns current trie size.
     * @return - size of the trie
     */
    public int size() {
        return root.prefixElements;
    }

    /**
     * A method that checks how many elements in the trie starts with the prefix.
     * @param prefix - a prefix which is being searched
     * @return - a number of elements that starts from the prefix
     */
    public int howManyStartWithPrefix(String prefix) {
        if (prefix == null) {
            return 0;
        }

        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (current.children.containsKey(prefix.charAt(i))) {
                current = current.children.get(prefix.charAt(i));
            } else {
                return 0;
            }
        }

        return current.prefixElements;
    }

    /**
     * A method that writes the trie to the out stream.
     * @param out - a stream to which the trie should be written
     * @throws IOException - an exception that should be raised when the trie couldn't be written to the out
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(this);
        outputStream.close();
    }

    /**
     * A method that changed the trie to another one from the in stream.
     * @param in - a stream from which the trie should be added
     * @throws IOException - an exception that should be raised when the stream couldn't be read
     * @throws ClassNotFoundException - an exception that should be raised when we couldn't find the class Trie
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(in);
        Trie newTrie = (Trie) inputStream.readObject();
        this.root = newTrie.root;
    }

    /**
     * A class that represents a node of the trie.
     * @isEndOfWord - an indicator of end of the world
     * @content - a value that consists of all previous Node and this one
     * @children - all elements that follows this letter
     * @prefixElements - a number of elements with such prefix
     */
    private class Node implements Serializable {
        private boolean isEndOfWord = false;
        private String content;
        private HashMap<Character, Node> children;
        private int prefixElements;

        private Node() {
            children = new HashMap<>();
            content = "";
            prefixElements = 0;
        }

        private Node(String content) {
            this.content = content;
            children = new HashMap<>();
        }
    }
}
