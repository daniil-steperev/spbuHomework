package group144.stepyrev;

/**
 * An interface that presents a lazy calculation.
 * @param <T> - a value of a calculated object
 */
public interface Lazy<T> {
    /**
     * A method that calculates the answer.
     *
     * First call of this method produces calculation and returns the answer, all subsequent return the first answer
     * @return - an answer that was calculated
     */
    T get();
}
