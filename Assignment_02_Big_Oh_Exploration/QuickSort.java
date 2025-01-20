package Assignment_02_Big_Oh_Exploration;

public class QuickSort {
    public static void sort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
        }
    }

    private static int partition(int[] arr, int p, int r) { //partition helper method
        int x = arr[r]; //set pivot point
        int i = p - 1; //highest index on the low side
        for (int j = p; j < r; j++) { // iterate from the pivot to the end of the array, j is the index being evaluated
            if (arr[j] <= x) { //if the current element at j is less than the pivot, swap them
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp2 = arr[i + 1];
        arr[i + 1] = x;
        x = tmp2;
        return i + 1;
    }
}
