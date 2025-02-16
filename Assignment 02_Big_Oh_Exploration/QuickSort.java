package Assignment_02_Big_Oh_Exploration;

/**
 * QuickSort provides an implementation of the Quick Sort algorithm
 * This class includes a method to recursively sort an integer array and a partitioning method
 * that rearranges the array elements around a pivot
 * 
 * @author  Reid Roberts
 */
public class QuickSort {

    /**
     * sort: recursively sort an array using the Quick Sort algorithm
     *
     * @param arr the array to be sorted
     * @param p   the starting index of the subarray
     * @param r   the ending index of the subarray
     */
    public static void sort(int[] arr, int p, int r) {
        // if the subarray has more than one element, continue sorting
        if (p < r) {
            int q = partition(arr, p, r);   // partition the array and get the final index of the pivot element
            sort(arr, p, q - 1);            // recursively sort the elements to the left of the pivot
            sort(arr, q + 1, r);            // recursively sort the elements to the right of the pivot
        }
    }

    /**
     * partition: Partitions the subarray around a pivot (chosen as the last element)
     * Elements less than or equal to the pivot are moved to its left, and elements greater are moved to its right
     *
     * @param arr the array containing the subarray to partition
     * @param p   the starting index of the subarray
     * @param r   the ending index of the subarray and the pivot's index
     * @return the index position of the pivot element after partitioning
     */
    private static int partition(int[] arr, int p, int r) {

        int x = arr[r]; // set the pivot to be the last element in the subarray
        int i = p - 1; // i will track the end of the "less than or equal to pivot" section


        // iterate through the subarray, excluding the pivot itself
        for (int j = p; j < r; j++) {
            // if current element is less than or equal to pivot, swap it into the correct partition
            if (arr[j] <= x) {
                i++;
                // Swap arr[i] and arr[j]
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        // place the pivot element after the last element smaller than it
        int tmp2 = arr[i + 1];
        arr[i + 1] = x;
        arr[r] = tmp2;

        // return the final pivot index
        return i + 1;
    }
}
