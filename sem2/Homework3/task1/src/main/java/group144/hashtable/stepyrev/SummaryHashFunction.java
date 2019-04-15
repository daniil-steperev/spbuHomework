package group144.hashtable.stepyrev;

/** A class that represents the SummaryHashFunction
 *
 * Hash counts code of each symbol and returns it divided by mod
 */
public class SummaryHashFunction implements HashFunction {
    private int mod = 256;

    /**
     * A constructor of SummaryHashFunction
     * @param mod means mod that should be setted
     * @throws WrongInputException an exception that should be raised if user enters incorrect data
     */
    public SummaryHashFunction(int mod) throws WrongInputException {
        if (mod <= 1) {
            System.out.println("Inputted for SummaryHashFunction mod is less or equals 1!");
            throw new WrongInputException();
        }
        this.mod = mod;
    }

    /** {@inheritDoc} */
    @Override
    public int getHash(String element) {
        int hash = 0;
        char[] elementLetters = element.toCharArray();
        for (char letter : elementLetters) {
            hash += (int)letter;
        }

        return hash % mod;
    }

    /** {@inheritDoc} */
    @Override
    public int getMod() {
        return mod;
    }

    /** {@inheritDoc} */
    @Override
    public int getPower() {
        return 1;
    }

    /**
     * A method that compares two SummaryHashFunctions
     * @param newHashFunction a hash function that should be compared with the current one
     * @return true if hash functions are equal and false if aren't
     */
    public boolean equals(HashFunction newHashFunction) {
        return (newHashFunction instanceof SummaryHashFunction) && (mod == newHashFunction.getMod());
    }
}
