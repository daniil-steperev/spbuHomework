package group144.hashtable.stepyrev;

/** An interface that describes hash function for hash table */
public interface HashFunction {
    /**
     * A method destined to receive hash of inputted element
     * @return means hash
     */
    int getHash(String element);

    /**
     * A method destined to receive mod of the hash function
     * @return means mod
     */
    int getMod();
}
