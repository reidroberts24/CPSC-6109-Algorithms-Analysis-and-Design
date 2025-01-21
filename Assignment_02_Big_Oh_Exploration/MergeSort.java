package Assignment_02_Big_Oh_Exploration;
import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] arr, int p, int r) {
        if (p >= r) { //check for edge case of empty or 1-element input array
            return;
        }
        int q = (p + r) / 2; //compute midpoint of input array
        sort(arr, p, q); //recursively sort left subarray
        sort(arr, q + 1, r); //recursively sort right subarray
        merge(arr, p, q, r); //merge subarrays
    }


    //METHOD TO MERGE SUBARRAYS
    private static void merge(int[] arr, int p, int q, int r) { //input array, start index p, midpoint q, endpoint r
        int nL = q - p + 1; //length of left subarray from start to midpoint
        int nR = r - q; //length of right subarray from q to r
        
        int[] L = new int[nL]; //empty subarray of length nL
        int[] R = new int[nR]; //empty subarray of length nR
        
        //Copy left subarray to L from 0 to nL - 1
        for (int i = 0; i < nL; i++) {
            L[i] = arr[p + i]; 
        }

        //Copy right subarray to R from q to r - 1
        for (int j = 0; j < nR; j++) {
            R[j] = arr[q + j + 1]; 
        }

        int i = 0; //starts at index 0 in left subarray
        int j = 0; //starts at index 0 in right subarray
        int k = p; //indicates location in the input array to fill

        //Iterate through subarrays simultaneously and compare elements, inserting the lower element into the input array
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

        //Case where left subarray has remaining elements
        while (i < nL) {
            arr[k] = L[i];
            i++;
            k++;
        }

        //Case where right subarray has remaining elements
        while (j < nR) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
