package Assignment_02_Big_Oh_Exploration;

/**
 * HeapSort provides an implementation of the heap sort algorithm
 * This class contains methods to build a max heap and sort an integer array in ascending order
 * 
 * @author  Reid Roberts
 */
public class HeapSort {
    //represents the current size of the heap
    private static int heapSize;

    /**
     * maxHeapify: maintains the max heap property for the subtree rooted at index i
     * This method assumes that the binary trees rooted at left and right 
     * children of i are max heaps, but arr[i] might be smaller than its children.
     *
     * @param arr the array representing the heap
     * @param i   the index of the root of the subtree to heapify
     */
    private static void maxHeapify(int[] arr, int i) {
        // calculate the indices of the left and right children.
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i; // Initialize largest as root index

        // if left child exists and is greater than the current largest, update largest
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        // if right child exists and is greater than the current largest, update largest
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        // if the largest element is not the current element, swap and continue heapifying
        if (largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            maxHeapify(arr, largest);
        }
    }

    /**
     * buildMaxHeap: builds a max heap from an unsorted array
     *
     * @param arr the array to be transformed into a max heap
     * @param n   the number of elements in the array
     */
    private static void buildMaxHeap(int[] arr, int n) {
        // set the initial heap size
        heapSize = n;
        // start from the last non-leaf node and heapify each node
        for (int i = n / 2; i >= 0; i--) {
            maxHeapify(arr, i);
        }
    }

    /**
     * sort: sorts an array in ascending order using the heap sort algorithm.
     *
     * @param arr the array to be sorted
     * @param n   the number of elements in the array
     */
    public static void sort(int[] arr, int n) {
        // build the initial max heap from the input array
        buildMaxHeap(arr, n);
        // extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // move the current largest element (at index 0) to the end of the array
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            // decrement the heap size and restore the max heap property
            heapSize--;
            maxHeapify(arr, 0);
        }
    }
}
