package group144.sorter.stepyrev;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ShakerSortTest {
    private static ShakerSort sorter = new ShakerSort();

    @Test
    void sortEmptyArray() {
        int[] array = {};
        int[] sortedArray = {};

        sorter.sort(array);
        assertArrayEquals(sortedArray, array);
    }

    @Test
    void sortArrayConsistingFromOneNumber() {
        int[] array = {5};
        int[] sortedArray = {5};

        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    void sortArrayConsistingFromTheSameNumbers() {
        int[] array = {-5, -5, -5, -5, -5};
        int[] sortedArray = {-5, -5, -5, -5, -5};

        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    void sortAscendingSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] sortedArray = {1, 2, 3, 4, 5};

        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    void sortDescendingSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] sortedArray = {1, 2, 3, 4, 5};

        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    void sortRegularArray() {
        int[] array = {4, 1, 2, 5, 1, 2, 0, -1};
        int[] sortedArray = {-1, 0, 1, 1, 2, 2, 4, 5};

        sorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }
}