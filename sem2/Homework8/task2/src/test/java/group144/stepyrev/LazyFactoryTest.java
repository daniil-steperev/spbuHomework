package group144.stepyrev;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static group144.stepyrev.LazyFactory.createNonSynchronizedLazy;
import static group144.stepyrev.LazyFactory.createSynchronizedLazy;
import static org.junit.jupiter.api.Assertions.*;

class LazyFactoryTest {
    @Test
    void createNonSynchronizedLazyCommonTest() {
        SmartSupplier<Integer> supplier = new SmartSupplier<>(1);
        Lazy<Integer> lazy = createNonSynchronizedLazy(supplier);

        assertEquals(Integer.valueOf(1), lazy.get());

        lazy.get();
        lazy.get();
        assertEquals(1, supplier.howManyTimesWasCalled);

        assertEquals(Integer.valueOf(1), lazy.get());
    }

    @Test
    void createNonSynchronizedLazyNullTest() {
        SmartSupplier<Integer> supplier = new SmartSupplier<>(null);
        Lazy<Integer> lazy = createNonSynchronizedLazy(supplier);

        assertEquals(null, lazy.get());

        lazy.get();
        lazy.get();
        assertEquals(1, supplier.howManyTimesWasCalled);

        assertEquals(null, lazy.get());
    }

    @Test
    void createSynchronizedLazyCommonTest() {
        SmartSupplier<String> supplier = new SmartSupplier<>("ABC");
        Lazy<String> lazy = createSynchronizedLazy(supplier);

        assertEquals("ABC", lazy.get());

        lazy.get();
        lazy.get();
        assertEquals(1, supplier.howManyTimesWasCalled);

        assertEquals("ABC", lazy.get());
    }

    @Test
    void createSynchronizedLazyNullTest() {
        SmartSupplier<Integer> supplier = new SmartSupplier<>(null);
        Lazy<Integer> lazy = createSynchronizedLazy(supplier);

        assertEquals(null, lazy.get());

        lazy.get();
        lazy.get();
        assertEquals(1, supplier.howManyTimesWasCalled);

        assertEquals(null, lazy.get());
    }

    @Test
    void synchronizedRaceCaseTest() {
        SmartSupplier<String> supplier = new SmartSupplier<>("Race");
        Lazy<String> lazy = createSynchronizedLazy(supplier);

        int NUMBER_OF_THREADS = 1000;
        Thread[] threads = new Thread[NUMBER_OF_THREADS];
        for (int i = 0; i < NUMBER_OF_THREADS; i++) { // initializing all threads
            threads[i] = new Thread(() -> assertEquals("Race", lazy.get()));
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) { // starting all threads
            threads[i].start();
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) { // joining all threads
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertEquals(1, supplier.howManyTimesWasCalled);

        assertEquals("Race", lazy.get());
    }

    private class SmartSupplier<T> implements Supplier<T> {
        private int howManyTimesWasCalled = 0;
        private T value;

        private SmartSupplier(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            howManyTimesWasCalled++;
            return value;
        }
    }
}