package group144.stepyrev;

import java.util.function.Supplier;

/** A class that creates Lazy objects. */
public class LazyFactory {
    /**
     * A method that performs non-synchronized calculation.
     *
     * Only one thread is used.
     * @param supplier - a supplier from which the answer is expected
     * @param <T> - a type of the value of the answer
     * @return - a calculated value
     */
    public static <T> Lazy<T> createNonSynchronizedLazy(Supplier<T> supplier) {
        return new Lazy<T>() {
            private T value;
            private boolean wasCalculated = false;

            @Override
            public T get() {
                if (wasCalculated) {
                    return value;
                } else {
                    value = supplier.get();
                    wasCalculated = true;
                    return value;
                }
            }
        };
    }

    /**
     * A method that performs synchronized calculation.
     *
     * Many threads are used.
     * @param supplier - a supplier from which the answer is expected
     * @param <T> - a type of the value of the answer
     * @return - a calculated value
     */
    public static <T> Lazy<T> createSynchronizedLazy(Supplier<T> supplier) {
        return new Lazy<T>() {
            private volatile T value;
            private volatile boolean wasCalculated = false;

            @Override
            public T get() {
                if (wasCalculated) {
                    return value;
                } else {
                    synchronized (this) {
                        if (!wasCalculated) {
                            value = supplier.get();
                            wasCalculated = true;
                        }
                    }
                }

                return value;
            }
        };
    }
}
