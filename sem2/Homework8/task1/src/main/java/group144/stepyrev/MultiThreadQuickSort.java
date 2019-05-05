package group144.stepyrev;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * A class that sorts an array via quick sort algorithm.
 *
 * This class uses only multi-thread algorithm.
 */
public class MultiThreadQuickSort implements Sorter {
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
        ForkJoinPool.commonPool().invoke(new SortAction(start, end));
    }

    private class SortAction extends RecursiveAction {
        private int start;
        private int end;

        private SortAction(int left, int right) {
            start = left;
            end = right;
        }

        @Override
        protected void compute() {
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

            invokeAll(new SortAction(start, currentIndex), new SortAction(currentIndex + 1, end));
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
}
