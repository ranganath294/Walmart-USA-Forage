import java.util.Arrays;
import java.util.NoSuchElementException;

public class PowerHeap {
    private double x; // Power parameter for calculating parent index
    private int size; // Current number of elements in the heap
    private int[] heapArray; // Array to store heap elements

    // Constructor to initialize the heap with a given capacity and power parameter x
    public PowerHeap(double x, int capacity) {
        this.x = x;
        this.size = 0;
        this.heapArray = new int[capacity];
        Arrays.fill(heapArray, -1); // Fill the heap array with a placeholder value
    }

    // Helper method to calculate the index of the parent node
    private int parentIndex(int i) {
        return (int) ((i - 1) / Math.pow(2, x));
    }

    // Check if the heap is full
    public boolean isFull() {
        return size == heapArray.length;
    }

    // Insert a new value into the heap
    public void insert(int value) {
        if (isFull()) {
            throw new NoSuchElementException("Heap is full, no space to insert a new element.");
        }
        heapArray[size++] = value; // Insert the new element at the end of the heap
        heapifyUp(size - 1); // Restore the heap property by moving the element up
    }

    // Heapify up to maintain the heap property after insertion
    private void heapifyUp(int i) {
        int currentValue = heapArray[i];
        while (i > 0 && currentValue > heapArray[parentIndex(i)]) {
            heapArray[i] = heapArray[parentIndex(i)]; // Move the parent down
            i = parentIndex(i);
        }
        heapArray[i] = currentValue; // Place the current value in its correct position
    }

    // Remove and return the maximum value (root) from the heap
    public int popMax() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty, no elements to remove.");
        }

        int maxItem = heapArray[0]; // Root of the heap is the maximum element
        heapArray[0] = heapArray[--size]; // Replace root with the last element
        heapArray[size] = -1; // Clear the last element

        heapifyDown(0); // Restore the heap property by moving the element down

        return maxItem;
    }

    // Heapify down to maintain the heap property after removal
    private void heapifyDown(int i) {
        int currentValue = heapArray[i];
        int largestChildIndex;

        while (i < size) {
            largestChildIndex = leftChildIndex(i); // Start with the left child

            // Check if the right child exists and is larger than the left child
            if (largestChildIndex + 1 < size && heapArray[largestChildIndex + 1] > heapArray[largestChildIndex]) {
                largestChildIndex++;
            }

            // If the current value is larger than or equal to the largest child, stop
            if (currentValue >= heapArray[largestChildIndex]) {
                break;
            }

            // Move the largest child up
            heapArray[i] = heapArray[largestChildIndex];
            i = largestChildIndex;
        }

        heapArray[i] = currentValue; // Place the current value in its correct position
    }

    // Helper method to calculate the index of the left child
    private int leftChildIndex(int i) {
        return 2 * i + 1;
    }

    // Print the elements of the heap
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i] + ",");
        }
        System.out.println();
    }

    // Main method to demonstrate the usage of PowerHeap
    public static void main(String[] args) {
        double x = 2; // Example value for x
        int capacity = 10; // Example capacity

        PowerHeap heap = new PowerHeap(x, capacity);
        heap.insert(5);
        heap.insert(10);
        heap.insert(3);

        heap.print(); // Output the heap

        int maxItem = heap.popMax();
        System.out.println("Max item: " + maxItem);

        heap.print(); // Output the heap after removal
    }
}
