package Assignment_02_Big_Oh_Exploration;
import java.util.Arrays;
import java.util.Random;
public class main {
    public static void main(String[] args) {

        long startTime;  
        long endTime;
        long duration;

        //create a sample array off random integers
        int n = 500000;
        int[] largeArray = new int[n];
        Random rand = new Random(); 
        for (int i = 0; i < n; i++) { 
            largeArray[i] = rand.nextInt(); //randomly generate values for each element in the sample array
        }
        //{3, -1, 5, 0, 12, 12, -7, 2, 9, 0};

        //create copies of the randomly generated array for each sorting method for direct runtime comparisons
        int[] insertionSortArray = Arrays.copyOf(largeArray, largeArray.length);
        int[] heapSortArray = Arrays.copyOf(largeArray, largeArray.length);
        int[] quickSortArray = Arrays.copyOf(largeArray, largeArray.length); 
        int[] mergeSortArray = Arrays.copyOf(largeArray, largeArray.length);
        

        //use measureRuntime method to test each sorting function and output runtime results in console
        measureRuntime("Insertion sort", insertionSortArray, () -> InsertionSort.sort(insertionSortArray));
        measureRuntime("Heapsort", heapSortArray, () -> HeapSort.sort(heapSortArray, heapSortArray.length));
        measureRuntime("Quicksort", quickSortArray, () -> QuickSort.sort(quickSortArray, 0, quickSortArray.length - 1));
        measureRuntime("Merge sort", mergeSortArray, () -> MergeSort.sort(mergeSortArray, 0, mergeSortArray.length - 1));   
    }


    // method to measure runtime and print to console
    public static void measureRuntime(String label, int[] arr, Runnable sortFunction) {
        long startTime = System.currentTimeMillis();
        sortFunction.run();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(label + " took: " + duration + "ms");
        System.out.println(label + " successfully sorted the whole array: " + isSorted(arr));
    }


    //test case to verify that each sorting function properly sorted its array
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) {
                return false;
            }
        }
        return true;
    }
}
