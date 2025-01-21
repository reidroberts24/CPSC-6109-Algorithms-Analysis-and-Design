package Assignment_02_Big_Oh_Exploration;

import java.util.Arrays;
import java.util.Random;

/**
 * main class to benchmark various sorting algorithms
 * this class generates a large array of random integers, creates copies for each sorting method,
 * measures the runtime of each sorting algorithm, and verifies that the array is sorted
 * 
 * @author  Reid Roberts
 */
public class main {
    /**
     * main: executes program and will run each of the sorting function classes and output their respective runtime results
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // creating a list of array sizes to test each sorting algorithm
        int[] arraySizes = {300000, 400000, 500000, 600000, 750000, 900000, 1000000};
        
        // for-loop creates a new randomized array to test for each array size in the list of array sizes
        for (int n : arraySizes) { 
            System.out.println("----- Array size: " + n + " -----");

            // Create a sample array of random integers.
            int[] largeArray = new int[n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                largeArray[i] = rand.nextInt(); // Randomly generate values
            }

            // Create copies of the randomly generated array for each sorting method.
            int[] insertionSortArray = Arrays.copyOf(largeArray, largeArray.length);
            int[] heapSortArray = Arrays.copyOf(largeArray, largeArray.length);
            int[] quickSortArray = Arrays.copyOf(largeArray, largeArray.length);
            int[] mergeSortArray = Arrays.copyOf(largeArray, largeArray.length);

            // Measure and print the runtime for each sorting algorithm.
            measureRuntime("Insertion sort", insertionSortArray, () -> InsertionSort.sort(insertionSortArray));
            measureRuntime("Heapsort", heapSortArray, () -> HeapSort.sort(heapSortArray, heapSortArray.length));
            measureRuntime("Quicksort", quickSortArray, () -> QuickSort.sort(quickSortArray, 0, quickSortArray.length - 1));
            measureRuntime("Merge sort", mergeSortArray, () -> MergeSort.sort(mergeSortArray, 0, mergeSortArray.length - 1));

            System.out.println(); // Blank line for readability between test cases.
        }

    }

    /**
     * measureRuntime: Measures the runtime of a given sorting function and prints the result to the console
     * Measures the runtime of the sorting function and verifies that the array is sorted.
     *
     * @param label        a label for the sorting algorithm (such as "Insertion sort")
     * @param arr          the array being sorted
     * @param sortFunction a Runnable encapsulating the call to the sorting algorithm
     */
    public static void measureRuntime(String label, int[] arr, Runnable sortFunction) {
        long startTime = System.currentTimeMillis();
        sortFunction.run();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(label + " took: " + duration + "ms");
        System.out.println(label + " successfully sorted the whole array: " + isSorted(arr));
    }

    /**
     * isSorted: checks if the array is sorted in ascending order
     *
     * @param arr the array to be tested
     * @return true if the array is sorted in ascending order, false otherwise
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
