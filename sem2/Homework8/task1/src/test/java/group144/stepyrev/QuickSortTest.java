package group144.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    private static QuickSort quickSorter = new QuickSort();

    @Test
    public void sortEmptyList() {
        int array[] = {};
        int sortedArray[] = {};
        quickSorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    public void sortSortedList() {
        int array[] = {0, 10, 20, 30, 100};
        int sortedArray[] = {0, 10, 20, 30, 100};
        quickSorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    public void sortArrayWithSimilarElements() {
        int array[] = {0, 0, 0, 0, 0};
        int sortedArray[] = {0, 0, 0, 0, 0};
        quickSorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

    @Test
    public void commonSortTest() {
        int array[] = {0, -123, 12, -312312, 0};
        int sortedArray[] = {-312312, -123, 0, 0, 12};
        quickSorter.sort(array);
        assertArrayEquals(array, sortedArray);
    }

}