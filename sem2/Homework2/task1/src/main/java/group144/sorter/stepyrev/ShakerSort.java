package group144.sorter.stepyrev;

/**
 * A class that represents sorter based on a Shaker Sort idea
 */
public class ShakerSort implements Sorter {
    @Override
    public void sort(int[] array) {
        int startIndex = 0;
        int finishIndex = array.length;

        for (int i = 0; i < finishIndex; ) {
            for (int j = i + 1; j < finishIndex; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                }
            }
            finishIndex--;

            for (startIndex = finishIndex - 1; startIndex > i; startIndex--) {
                if (array[startIndex - 1] < array[startIndex]) {
                    swap(array, startIndex - 1, startIndex);
                }
            }
            startIndex++;
        }
    }

    private void swap(int[] array, int firstIndex, int secondIndex) {
        int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }
}
