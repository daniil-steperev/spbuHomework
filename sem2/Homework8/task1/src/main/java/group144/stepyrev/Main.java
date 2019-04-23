package group144.stepyrev;

import java.util.Random;

public class Main {
    private static int ARRAY_SIZE = 100000;
    private static int NUMBER_OF_TESTS = 100;

    public static void main(String[] args) {
        long simpleSorterTime = 0;
        long quickSorterTime = 0;
        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            int[] array = new int[ARRAY_SIZE];
            initializeArray(array);

            int[] clonedArray = new int[ARRAY_SIZE];
            clonedArray = array.clone();

            Sorter simpleSorter = new QuickSort();
            Sorter quickSorter = new MultiThreadQuickSort();

            simpleSorterTime += noteTime(array, simpleSorter);
            quickSorterTime += noteTime(clonedArray, quickSorter);
        }

        long averageSimpleSorterTime = simpleSorterTime / NUMBER_OF_TESTS;
        long averageQuickSorterTime = quickSorterTime / NUMBER_OF_TESTS;
        printResult(simpleSorterTime, quickSorterTime);
    }

    private static void initializeArray(int[] array) {
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt();
        }
    }

    private static long noteTime(int[] array, Sorter sorter) {
        long startTime = System.currentTimeMillis();
        sorter.sort(array);
        long result = System.currentTimeMillis() - startTime;

        return result;
    }

    private static void printResult(long simpleSorterTime, long quickSorterTime) {
        System.out.println("The average time of sorting random int array with 100000 elements:");
        System.out.println(" -Simple Quick Sorter: " + simpleSorterTime + " ms;");
        System.out.println(" -Multi-thread Quick Sorter: " + quickSorterTime + " ms.");
        System.out.println("Multi-thread sorter is " + (simpleSorterTime - quickSorterTime) + " ms faster!");
    }
}
