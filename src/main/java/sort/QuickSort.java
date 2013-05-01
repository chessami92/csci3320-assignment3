package main.java.sort;

/**
 * Created by: Josh
 * On: 4/16/13 10:48 PM
 */
public class QuickSort<T extends Comparable<T>> implements Sorter<T> {
    private int minimumQuickSort;

    /*
     * Defaults the quicksort cutoff to be 20.
     */
    public QuickSort() {
        this(20);
    }

    /*
     * Constructor that sets the cutoff point for when the sorting algorithm should
     * switch from quicksort to insertion sort.
     */
    public QuickSort(int minimumQuickSort) {
        if (minimumQuickSort < 1) {
            throw new IllegalArgumentException("Cannot have quicksort cutoff less than 1.");
        }
        this.minimumQuickSort = minimumQuickSort;
    }

    @Override
    public String getSorterDescription() {
        return String.format("Quicksort, X=%d", minimumQuickSort);
    }

    /*
     * Entry point for sorting the array. Calls the recursive sorting function.
     */
    @Override
    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    /*
     * Recursive function that determines a pivot, partitions, then calls the same sort
     * method with each partition.
     * If the array size is less than the cutoff, an insertion sort is performed on the
     * remaining array.
     */
    private void sort(T[] array, int left, int right) {
        //Perform an insertion sort if the array size is too small.
        if ((right - left) <= minimumQuickSort) {
            insertionSort(array, left, right);
        } else {
            //Determine a median for partitioning.
            T pivot = medianOfThree(array, left, right);

            //Pointers for partitioning.
            int i = left;
            int j = right - 1;

            while (i < j) {
                //Increase i until an element is found that is greater than the pivot.
                while (array[++i].compareTo(pivot) < 0) {
                }
                //Decrease j until an element is found that is less than the pivot.
                while (array[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    swapLocations(array, i, j);
                }
            }

            //Restore the pivot to the center of the array
            swapLocations(array, i, right - 1);

            //Recursively sort each partition
            sort(array, left, i - 1);
            sort(array, i, right);
        }
    }

    /*
     * Takes the array and sorts the left, center, and right locations
     * in place. Puts the median at the second to last place (right - 1).
     * Returns the median element.
     */
    private T medianOfThree(T[] array, int left, int right) {
        int center = (left + right) / 2;
        //Sort the left, center, and right positions in place.
        if (array[center].compareTo(array[left]) < 0) {
            swapLocations(array, left, center);
        }
        if (array[right].compareTo(array[left]) < 0) {
            swapLocations(array, left, right);
        }
        if (array[right].compareTo(array[center]) < 0) {
            swapLocations(array, right, center);
        }

        //Place the median in the second to last place
        swapLocations(array, center, right - 1);

        return array[right - 1];
    }

    /*
     * Swap two references in an array.
     */
    private void swapLocations(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /*
     * Basic insertion sort method for use when the divided array size is small enough.
     * Array segment is returned fully sorted.
     */
    public void insertionSort(T[] array, int left, int right) {

        for (int p = left + 1; p <= right; p++) {
            int j;
            T temp = array[p];
            //Swap with the position to the left until it is in the correct place in the sorted array.
            for (j = p; j > 0 && temp.compareTo(array[j - 1]) < 0; --j) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }
}
