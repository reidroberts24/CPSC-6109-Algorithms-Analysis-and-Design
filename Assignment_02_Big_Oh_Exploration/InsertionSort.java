package Assignment_02_Big_Oh_Exploration;

/**
 * InsertionSort provides an implementation of the insertion sort algorithm
 * This class contains a method to sort an integer array in ascending order using the insertion sort technique
 * 
 * @author  Reid Roberts
 */
public class InsertionSort {

    /**
     * sort: sorts the given array in ascending order using the insertion sort algorithm
     *
     * @param arr the array of integers to be sorted
     */
    public static void sort(int[] arr) {
        // declare variable j for indexing within the inner loop
        int j;
        // start loop at index 1 since the element at index 0 is trivially sorted
        for (int i = 1; i < arr.length; i++) {
            // store the current element in a temporary variable for sorting
            int tmp = arr[i];
            // iterate backwards through the sorted portion of the array
            // shift elements that are greater than tmp one position to the right
            for (j = i; j > 0 && tmp < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            // insert the tmp value into its correct position
            arr[j] = tmp;
        }
    }
}
