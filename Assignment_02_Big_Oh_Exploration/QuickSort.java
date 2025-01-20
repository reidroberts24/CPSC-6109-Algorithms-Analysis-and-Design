package Assignment_02_Big_Oh_Exploration;

public class QuickSort {
    public static void sort(int[] arr, int p, int r) { //input array, initial index p, length of array r
        if (p < r) {
            int q = partition(arr, p, r);
            sort(arr, p, q - 1);
            sort(arr, q + 1, r);
        }
    }
    
    //partition the array in place to get high and low side about index i+1
    private static int partition(int[] arr, int p, int r) { 
        int x = arr[r]; //set pivot point as last element
        int i = p - 1; //highest index on the low side
        for (int j = p; j < r; j++) { // iterate from the current element to the end of the array (to the pivot) 
            if (arr[j] <= x) { //if element at j is less than the pivot, they need to be swapped
                i++;
                int tmp = arr[i]; //store element at i as tmp variable
                arr[i] = arr[j]; //swap i with j
                arr[j] = tmp; //set j as tmp
            }
        }
        //swap the pivot element with the i+1 element so we have a low side and a high side
        int tmp2 = arr[i + 1]; //store i+1 element as tmp2
        arr[i + 1] = x; //swap i+1 element with pivot
        x = tmp2; //set final array element as tmp2 
        return i + 1; //return i+1 because that is where the final element on the low side is 
    }
}
