package Assignment_02_Big_Oh_Exploration;

public class HeapSort {
    private static int heapSize;
    // max-heapify method to maintain max heap property
    private static void maxHeapify(int[] arr, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            maxHeapify(arr, largest);
        }
    }

    //build max-heap method to create heap from array
    private static void buildMaxHeap(int[] arr, int n) {
        heapSize = n;
        for (int i = (n / 2); i >= 0; i--) {
            maxHeapify(arr, i);
        }
    }


    //sort max-heap
    public static void sort(int[] arr, int n) {
        buildMaxHeap(arr, n);
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapSize--;
            maxHeapify(arr,0);
        }
    }
}
