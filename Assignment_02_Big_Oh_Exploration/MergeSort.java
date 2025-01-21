package Assignment_02_Big_Oh_Exploration;

import java.util.Arrays;

/**
 * MergeSort provides an implementation of the Merge Sort algorithm.
 * This class includes methods to recursively sort an integer array and merge sorted subarrays.
 *
 * @author  Reid Roberts
 */
public class MergeSort {

    /**
     * sort: recursively sort an input array using the merge sort algorithm
     *
     * @param arr the array to be sorted
     * @param p   the starting index of the subarray to be sorted
     * @param r   the ending index of the subarray to be sorted
     */
    public static void sort(int[] arr, int p, int r) {
        // ACcount for edge case: if subarray is empty or has one element, it is already sorted
        if (p >= r) {
            return;
        }
        
        int q = (p + r) / 2; // compute the midpoint of the subarray
        sort(arr, p, q); // recursively sort the left subarray
        sort(arr, q + 1, r); // recursively sort the right subarray
        merge(arr, p, q, r); // merge the sorted subarrays
    }

    /** 
     * merge: merges two sorted subarrays into a single sorted subarray
     * @param arr the original array containing the subarrays
     * @param p   the starting index of the left subarray
     * @param q   the ending index of the left subarray (midpoint)
     * @param r   the ending index of the right subarray
     */
    private static void merge(int[] arr, int p, int q, int r) {
        int nL = q - p + 1; // calculate the length of the left subarray (from index p to q)
        int nR = r - q; // calculate the length of the right subarray (from index q+1 to r)

        // create temporary arrays for left and right subarrays
        int[] L = new int[nL]; // left subarray
        int[] R = new int[nR]; // right subarray

        // copy elements to left subarray
        for (int i = 0; i < nL; i++) {
            L[i] = arr[p + i];
        }

        // Copy elements to right subarray
        for (int j = 0; j < nR; j++) {
            R[j] = arr[q + j + 1];
        }

        // Initial indexes for left subarray (i), right subarray (j), and merged array (k)
        int i = 0; // current index in L
        int j = 0; // current index in R
        int k = p; // current index in arr to be filled

        // merge elements from L and R back into arr in sorted order
        while (i < nL && j < nR) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // copy remaining elements of L or R into arr
        // case where remain
        while (i < nL) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // copy any remaining elements of R into arr.
        while (j < nR) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
