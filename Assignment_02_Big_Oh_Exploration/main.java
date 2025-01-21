package Assignment_02_Big_Oh_Exploration;
import java.util.Arrays;
import java.util.Random;
public class main {
    public static void main(String[] args) {

        long startTime;  
        long endTime;
        long duration;

        //create a sample array off random integers
        int n = 100000000;
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
        
/*        //Runtime test ffor Insertion Sort
        //System.out.println("Before sorting: " + Arrays.toString(insertionSortArray));
        startTime = System.currentTimeMillis();
        InsertionSort.sort(insertionSortArray);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        //System.out.println("After sorting: " + Arrays.toString(insertionSortArray));
        System.out.println("Insertion sort took: " + duration + "ms");

*/        //Runtime test for HeapSort
        startTime = System.currentTimeMillis();
        HeapSort.sort(heapSortArray, heapSortArray.length);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Heapsort took: " + duration + "ms");
        System.out.println("Heapsort successfully sorted the whole array: " + isSorted(heapSortArray));

        //Runtime test for QuickSort
        startTime = System.currentTimeMillis();
        QuickSort.sort(quickSortArray, 0, quickSortArray.length - 1);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Quicksort took: " + duration + "ms");
        System.out.println("Quicksort successfully sorted the whole array: " + isSorted(quickSortArray));

        //Runtime test for MergeSort
        startTime = System.currentTimeMillis();
        MergeSort.sort(mergeSortArray, 0, mergeSortArray.length - 1);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("MergeSort took: " + duration + "ms");
        System.out.println("MergeSort successfully sorted the whole array: " + isSorted(mergeSortArray));
        
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
