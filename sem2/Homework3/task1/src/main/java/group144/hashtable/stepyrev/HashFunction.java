package group144.hashtable.stepyrev;

/** An interface that describes hash function for hash table */
public interface HashFunction {
    /**
     * A method destined to receive hash of inputted element
     * @return means hash
     */
    int getHash(String element);

    /**
     * A method destined to receive a mod of the hash function
     * @return means mod
     */
    int getMod();

    /**
     * A method destined to receive a power of the hash function
     *
     * In case of summary hash function returns 1
     * @return a power of the hash function
     */
    int getPower();
}
