package main.java;

import main.java.sort.BucketSort;
import main.java.sort.QuickSort;
import main.java.sort.Sorter;

import java.util.Date;
import java.util.Random;

/**
 * Created by: Josh
 * On: 4/16/13 10:46 PM
 */
public class TestEfficiency {
    private static final Random random = new Random();
    private static final String COLUMN_FORMAT = "%20s|";

    public static void main(String[] args) {
        //Create the three test cases: 1000 integers between -1000 and 1000, 1000 integers between -1 and 1,
        //and 1000 integers reverse sorted from 1000 to 1.
        TestCase[] testCases = {new TestCase<Integer>(-100, 100, createArray(1000, 100), "Range -100 to 100"),
                new TestCase<Integer>(-1, 1, createArray(1000, 1), "Range -1 to 1"),
                new TestCase<Integer>(1, 1000, createReverseSortedArray(1, 1000), "Reverse sorted")};

        Sorter[] sorters = getSortersForTest(0, 0);
        System.out.printf(COLUMN_FORMAT, "Test Type");
        for (Sorter<Integer> sorter : sorters) {
            System.out.printf(COLUMN_FORMAT, sorter.getSorterDescription());
        }
        System.out.println();

        for (TestCase<Integer> testCase : testCases) {
            System.out.printf(COLUMN_FORMAT, testCase.getDescription());
            sorters = getSortersForTest(testCase.getMinimumElement(), testCase.getMaximumElement());
            for (Sorter<Integer> sorter : sorters) {
                Integer[] array = copyArray(testCase.getArray());

                Date startTime = new Date();
                sorter.sort(array);
                long timeTook = new Date().getTime() - startTime.getTime();
                System.out.printf(COLUMN_FORMAT, timeTook);

                //Verify that the array was actually sorted correctly.
                checkArraySorted(array);
            }
            System.out.println();
        }
    }

    private static Integer[] createArray(int length, int maxValue) {
        Integer[] array = new Integer[length];

        for (int i = 0; i < length; ++i) {
            array[i] = random.nextInt(2 * maxValue + 1) - maxValue;
        }

        return array;
    }

    private static Integer[] createReverseSortedArray(int minimumElement, int maximumElement) {
        Integer[] array = new Integer[maximumElement - minimumElement + 1];

        for (int i = 0; i < array.length; ++i) {
            array[i] = maximumElement - i;
        }

        return array;
    }

    /*
     * Returns a deep copy of the original array.
     */
    private static Integer[] copyArray(Integer[] array) {
        Integer[] newArray = new Integer[array.length];

        for (int i = 0; i < array.length; ++i) {
            newArray[i] = array[i];
        }

        return newArray;
    }

    /*
     * Verify that the passed array is sorted. If it is not sorted, an illegal argument
     * exception is thrown.
     */
    private static void checkArraySorted(Integer[] array) {
        int priorElement = array[0];

        for (int i = 1; i < array.length - 1; ++i) {
            if (array[i] < priorElement) {
                throw new IllegalArgumentException("Array is not sorted");
            }
            priorElement = array[i];
        }
    }

    private static Sorter<Integer>[] getSortersForTest(int minimumElement, int maximumElement) {
        Sorter[] sorters = {new QuickSort(500),
                new QuickSort(250),
                new QuickSort(10),
                new QuickSort(1),
                new BucketSort(minimumElement, maximumElement)};

        return (Sorter<Integer>[]) sorters;
    }
}
