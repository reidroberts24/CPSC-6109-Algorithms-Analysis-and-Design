package Assignment_02_Big_Oh_Exploration;
import java.util.Arrays;
public class main {
    public static void main(String[] args) {

        long startTime; 
        long endTime;
        long duration;
        //create a sample of array integers
        int[] array = {5, 3, 6, 1, 7, 9};

        System.out.println("Before sorting: " + Arrays.toString(array));
        startTime = System.currentTimeMillis();
        InsertionSort.sort(array);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("After sorting: " + Arrays.toString(array));
        System.out.println("Insertion sort took: " + duration);

    }
}
