package group144.hashtable.stepyrev;

/** A class that should be raised when user tries to change HashFunction to similar one */
public class SimilarHashFunctionException extends Exception {
    public SimilarHashFunctionException() {
        super();
    }

    public SimilarHashFunctionException(String msg) {
        super(msg);
    }
}
