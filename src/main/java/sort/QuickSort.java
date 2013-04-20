package main.java.sort;

/**
 * Created by: Josh
 * On: 4/16/13 10:48 PM
 */
public class QuickSort<T extends Comparable<T>> implements Sorter<T> {
    private int minimumQuickSort;

    public QuickSort() {
        this(20);
    }

    public QuickSort(int minimumQuickSort) {
        if (minimumQuickSort < 1) {
            throw new IllegalArgumentException("Cannot have quicksort cutoff less than 1.");
        }
        this.minimumQuickSort = minimumQuickSort;
    }

    @Override
    public String getSorterDescription() {
        return String.format("Quicksort, insertion sort if n<%d", minimumQuickSort);
    }

    @Override
    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(T[] array, int left, int right) {
        if ((right - left) <= minimumQuickSort) {
            insertionSort(array, left, right);
        } else {
            T pivot = medianOfThree(array, left, right);

            int i = left;
            int j = right - 1;

            while (i < j) {
                while (array[++i].compareTo(pivot) < 0) {
                }
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

    private void swapLocations(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void insertionSort(T[] array, int left, int right) {
        int j;

        for (int p = left + 1; p <= right; p++) {
            T temp = array[p];
            for (j = p; j > 0 && temp.compareTo(array[j - 1]) < 0; --j) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }
}
