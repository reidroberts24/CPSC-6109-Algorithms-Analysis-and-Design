package Assignment_02_Big_Oh_Exploration;
import java.util.Arrays;
import java.util.Random;
public class main {
    public static void main(String[] args) {

        long startTime; 
        long endTime;
        long duration;

        //create a sample of array integers
        int n = 10000000;
        int[] largeArray = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            largeArray[i] = rand.nextInt();
        }
        int[] insertionSortArray = largeArray;
        int[] heapSortArray = largeArray;
      
/*
        //System.out.println("Before sorting: " + Arrays.toString(insertionSortArray));
        startTime = System.currentTimeMillis();
        InsertionSort.sort(insertionSortArray);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        //System.out.println("After sorting: " + Arrays.toString(insertionSortArray));
        System.out.println("Insertion sort took: " + duration + "ms");
*/

        //System.out.println("Before HeapSort: " + Arrays.toString(heapSortArray));
        startTime = System.currentTimeMillis();
        HeapSort.sort(heapSortArray, heapSortArray.length);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        //System.out.println("After sorting: " + Arrays.toString(heapSortArray));
        System.out.println("Heapsort took: " + duration + "ms");
    }
}
