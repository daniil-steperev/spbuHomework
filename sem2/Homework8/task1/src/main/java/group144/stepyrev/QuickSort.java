package group144.stepyrev;

/**
 * A class that sorts an array via quick sort algorithm.
 *
 * This class uses only one thread algorithm.
 */
public class QuickSort implements Sorter {
    private int[] array;

    /** {@inheritDoc} */
    @Override
    public void sort(int[] sortedArray) {
        array = sortedArray;
        if (array.length <= 1) {
            return;
        }

        int start = 0;
        int end = sortedArray.length - 1;
        makeSort(start, end);
    }

    /**
     * A method that performs one iteration of sorting algorithm.
     * @param start - an index of first element
     * @param end - an index of last element
     */
    private void makeSort(int start, int end) {
        if (start >= end) {
            return;
        }

        int leftIndex = start;
        int rightIndex = end;
        int currentIndex = leftIndex - (leftIndex - rightIndex) / 2;
        while (leftIndex < rightIndex) {
            while (leftIndex < currentIndex && (array[leftIndex] <= array[currentIndex])) {
                leftIndex++;
            }
            while (rightIndex > currentIndex && (array[rightIndex] >= array[currentIndex])) {
                rightIndex--;
            }

            if (leftIndex < rightIndex) {
                swap(leftIndex, rightIndex);
                if (leftIndex == currentIndex) {
                    currentIndex = rightIndex;
                } else {
                    currentIndex = leftIndex;
                }
            }
        }

        makeSort(start, currentIndex);
        makeSort(currentIndex + 1, end);
    }

    /**
     * A method that swaps two values of the array.
     * @param first - an index of first element
     * @param second - an index of second element
     */
    private void swap(int first, int second) {
        int tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }
}
