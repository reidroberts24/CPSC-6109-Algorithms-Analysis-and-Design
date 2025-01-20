package Assignment_02_Big_Oh_Exploration;

public class InsertionSort {    // declare class
    // declare sort method
    public static void sort(int[] arr) {
        int j;  //instantiate variable j for future for-loop
        for (int i = 1; i < arr.length; i++) {  //start from i=1 because first element is trivially sorted
            int tmp = arr[i]; //store current value as tmp for sorting comparison
            for (j = i; j > 0 && tmp <arr[j - 1]; j--) {    //iteratively check if current value is in the correct place, moving j backwards
                arr[j] = arr[j - 1]; //shift larger element one position to the right
            }
            arr[j] = tmp; //insert tmp into the correct position
        }
    }
}
