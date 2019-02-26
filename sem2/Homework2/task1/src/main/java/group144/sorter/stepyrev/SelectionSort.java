package group144.sorter.stepyrev;

/**
 * A class that represents sorter based on a Selection Sort idea
 */
public class SelectionSort implements Sorter {
    @Override
    public void sort(int[] array) {
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            if (array[min] != array[i]) {
                swap(array, min, i);
            }
        }
    }

    private void swap(int[] array, int firstIndex, int secondIndex) {
        int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }
}
