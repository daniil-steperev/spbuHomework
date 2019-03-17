package group144.hashtable.stepyrev;

/** A class that should be raised when user tries to remove() element which is not in the HashTable*/
public class ElementAbsenseException extends Exception {
    public ElementAbsenseException() {
        super();
    }

    public ElementAbsenseException(String message) {
        super(message);
    }
}
