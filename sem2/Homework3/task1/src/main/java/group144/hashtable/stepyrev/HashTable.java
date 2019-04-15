package group144.hashtable.stepyrev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/** A class that represents a hash table with ability of changing hash function. */
public class HashTable {
    private HashFunction hashFunction;
    private List<String>[] hashTable;
    private int size = 256;
    private int numberOfElements = 0;

    /**
     * A constructor of class HashTable.
     * @throws WrongInputException an exception that should be raised when user tries to input incorrect data
     */
    public HashTable() throws WrongInputException {
        List<String>[] hashTable = new List[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new List<String>();
        }

        this.hashTable = hashTable;
        this.hashFunction = new PolynomialHashFunction(13, 256);
    }

    /** A constructor of HashTable with the hash function. */
    public HashTable(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        List<String>[] hashTable = new List[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new List<String>();
        }
        this.hashTable = hashTable;
    }

    /** A constructor of HashTable with the hash function and the size.
     * @param hashFunction a hashfunction that will be used in HashTable
     * @param size a size of HashTable
     */
    public HashTable(HashFunction hashFunction, int size) {
        this.hashFunction = hashFunction;
        this.size = size;
        List<String>[] hashTable = new List[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new List<String>();
        }
        this.hashTable = hashTable;
    }

    /**
     * A method that adds the element to the hashtable.
     * @param element means added element
     */
    public void add(String element) {
        int hash = hashFunction.getHash(element);
        if (isContain(element, hash)) {
            return;
        }
        hashTable[hash].add(element);
        numberOfElements++;

    }

    /**
     * A method that finds the element in the HashTable.
     * @param element an element that should be found
     * @return true if element present and false if not
     */
    public boolean find(String element) {
        for (int i = 0; i < size; i++) {
            if (hashTable[i].find(element)) {
                return true;
            }
        }

        return false;
    }

    private boolean isContain(String element, int hash) {
        if (hashTable[hash].find(element)) {
            return true;
        }

        return false;
    }

    /**
     * A method that removes an element from the HashTable.
     * @param element an element that should be removed
     * @throws ElementAbsenseException an exception that should be raised if element is not present in the HashTable
     */
    public void remove(String element) throws ElementAbsenseException {
        int hash = hashFunction.getHash(element);
        if (!isContain(element, hash)) {
            throw new ElementAbsenseException();
        }

        hashTable[hash].remove(element);
        numberOfElements--;
    }

    /**
     * A method that changes a hash function.
     * @param newHashFunction a new hash function that should be setted to the HashTable
     * @throws WrongInputException an exception that should be raised when user inputs incorrect data
     */
    public void changeHashFunction(HashFunction newHashFunction) throws WrongInputException {
        if (isSimilarHashFunction(newHashFunction)) {
            return;
        }

        int newSize = newHashFunction.getMod();
        this.hashFunction = newHashFunction;
        List[] newHashTable = new List[newSize];
        for (int i = 0; i < newSize; i++) {
            newHashTable[i] = new List<String>();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < hashTable[i].getLength(); j++) {
                String element = hashTable[i].getByIndex(j);
                int hash = newHashFunction.getHash(element);
                newHashTable[hash].add(element);
            }
        }

        this.hashTable = newHashTable;
        this.size = newSize;
    }

    private boolean isSimilarHashFunction(HashFunction newHashFunction) {
        return newHashFunction.equals(hashFunction);
    }

    /**
     * A method that returns a load factor of the hash table.
     * @return a load factor
     */
    public double getLoadFactor() {
        return (double) numberOfElements / size;
    }

    /**
     * A method that returns a number of conflicts in the hash table.
     * @return a number of conflicts
     */
    public int getNumberOfConflicts() {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (hashTable[i].getLength() > 1) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * A method that returns a max length of a conflict chain.
     * @return a max length of a conflict chain
     */
    public int getMaxLength() {
        int maxLength = 0;
        int length = 0;
        for (int i = 0; i < size; i++) {
            if (hashTable[i].getLength() > 1) {
                length = hashTable[i].getLength();
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }

        return maxLength;
    }

    /**
     * A method that counts empty rows
     * @return a number of empty rows
     */
    public int countEmptyRows() {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (hashTable[i].getLength() == 0) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * A method that returns statistics of the HashTable.
     * @return statistics of the HashTable
     */
    public Statistics getStatistics() {
        return new Statistics(this);
    }

    /**
     * A method that returns a number of elements of the hashtable.
     * @return a number of elements of the hashtable
     */
    public int getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * A method that fills the HashTable from the file.
     * @param fileName a name of file from which HashTable should be filled
     */
    public void getFromFile(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.next();
                add(data);
            }

            file.close();
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File cannot be opened!");
        }
        catch (IOException e) {
            System.out.println("File cannot be closed!");
        }
    }
}
