package group144.hashtable.stepyrev;

/**
 * A class that realizes hash function based on idea of getting polynomial hash.
 *
 * Polynomial hash means a*x^(m) + b*x^(m-1) + ...
 */
public class PolynomialHashFunction implements HashFunction {
    private int power = 13;
    private int mod = 256;

    /**
     * A constructor of PolynomialHashFunction
     * @param power means a power of the hash function
     * @param mod means a mod of the hash function
     * @throws WrongInputException an exception that should be raised if user inputs incorrect data
     */
    public PolynomialHashFunction(int power, int mod) throws WrongInputException {
        if (power <= 1 || mod <= 1) {
            throw new WrongInputException("Incorrect inputted data for Polynomial hash function");
        }

        this.power = power;
        this.mod = mod;
    }

    /** {@inheritDoc} */
    @Override
    public int getHash(String element) {
        int hash = 0;
        char[] elementLetters = element.toCharArray();

        for (int i = 0; i < elementLetters.length; i++) {
            hash = ((hash * power) % mod + elementLetters[i] % mod) % mod;
        }

        return hash;
    }

    /** {@inheritDoc} */
    @Override
    public int getMod() {
        return mod;
    }

    /**
     * A method that returns the power of the hash function
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * A method that compares two PolynomialHashFunctions
     * @param newHashFunction means new PolynomialHashFunctions that should be compared with current one
     * @return true if hash functions are equal and false if aren't
     */
    public boolean equals(PolynomialHashFunction newHashFunction) {
        return (mod == newHashFunction.getMod()) && (power == newHashFunction.getPower());
    }
}
