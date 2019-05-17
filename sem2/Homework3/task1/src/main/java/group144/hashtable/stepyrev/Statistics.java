package group144.hashtable.stepyrev;

/** A class that represents a statistics of the hashtable. */
public class Statistics {
    private double loadFactor;
    private int numberOfConflicts;
    private int maxLength;
    private int emptyRows;
    private int amountOfElements;

    /** A constructor of the class. */
    public Statistics(HashTable hashTable) {
        loadFactor = hashTable.getLoadFactor();
        numberOfConflicts = hashTable.getNumberOfConflicts();
        maxLength = hashTable.getMaxLength();
        emptyRows = hashTable.countEmptyRows();
        amountOfElements = hashTable.getNumberOfElements();
    }

    /** A constructor of the class. */
    public Statistics(double loadFactor, int numberOfConflicts, int maxLength, int emptyRows, int amountOfElements) {
        this.loadFactor = loadFactor;
        this.numberOfConflicts = numberOfConflicts;
        this.maxLength = maxLength;
        this.emptyRows = emptyRows;
        this.amountOfElements = amountOfElements;
    }

    /**
     * A method that checks if the another Statistics equals to this one.
     * @param another an another statistics
     * @return true if equals, false otherwise
     */
    public boolean equals(Statistics another) {
        return another.loadFactor == loadFactor &&
                another.emptyRows == emptyRows &&
                another.numberOfConflicts == numberOfConflicts &&
                another.maxLength == maxLength &&
                another.amountOfElements == amountOfElements;
    }

    /** A method that returns a string that consists of statistics. */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Load factor: ");
        stringBuilder.append(loadFactor);
        stringBuilder.append("; number of conflicts: ");
        stringBuilder.append(numberOfConflicts);
        stringBuilder.append("; max conflict chain length: ");
        stringBuilder.append(maxLength);
        stringBuilder.append("; a number of empty rows: ");
        stringBuilder.append(emptyRows);
        stringBuilder.append("; amount of elements: ");
        stringBuilder.append(amountOfElements);
        stringBuilder.append(".");

        return stringBuilder.toString();
    }
}
