package Assignment_02_Big_Oh_Exploration;

public class QuickSort {
    public static void sort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
        }
    }

    private static int partition(int[] arr, int p, int r) {
        int x = arr[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (arr[j] <= x) {
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
