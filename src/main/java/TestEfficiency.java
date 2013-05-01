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

        //Print out the header for the table.
        Sorter[] sorters = getSortersForTest(0, 0);
        System.out.printf(COLUMN_FORMAT, "Test Type");
        for (Sorter<Integer> sorter : sorters) {
            System.out.printf(COLUMN_FORMAT, sorter.getSorterDescription());
        }
        System.out.println();

        //Go through each previously created test case.
        for (TestCase<Integer> testCase : testCases) {
            //Print out what test case this is.
            System.out.printf(COLUMN_FORMAT, testCase.getDescription());
            //Create the sorter objects given this test case.
            sorters = getSortersForTest(testCase.getMinimumElement(), testCase.getMaximumElement());
            for (Sorter<Integer> sorter : sorters) {
                //Create a new copy of the array as to not sort it before the other methods get to it.
                Integer[] array = copyArray(testCase.getArray());

                //Keep track of how long the sorting takes.
                Date startTime = new Date();
                sorter.sort(array);
                long timeTook = new Date().getTime() - startTime.getTime();
                //Print out the result in milliseconds for this sorter.
                System.out.printf(COLUMN_FORMAT, timeTook);

                //Verify that the array was actually sorted correctly.
                checkArraySorted(array);
            }
            System.out.println();
        }
    }

    /*
     * Creates an array of size length filled with values between + and - maxValue.
     */
    private static Integer[] createArray(int length, int maxValue) {
        Integer[] array = new Integer[length];

        for (int i = 0; i < length; ++i) {
            array[i] = random.nextInt(2 * maxValue + 1) - maxValue;
        }

        return array;
    }

    /*
     * Create a non-random array that has all elements between minimum element and maximum
     * element in the reverse order.
     */
    private static Integer[] createReverseSortedArray(int minimumElement, int maximumElement) {
        Integer[] array = new Integer[maximumElement - minimumElement + 1];

        for (int i = 0; i < array.length; ++i) {
            array[i] = maximumElement - i;
        }

        return array;
    }

    /*
     * Returns a deep copy of the original array, that is, a new instance with all elements
     * in the same order as the original array.
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
            //Check that it is properly sorted.
            if (array[i] < priorElement) {
                throw new IllegalArgumentException("Array is not sorted");
            }
            priorElement = array[i];
        }
    }

    /*
     * Creates and returns list of all types of sorters to be tested. Ensures the same order
     * when printing the column headers and when actually performing the tests.
     */
    private static Sorter<Integer>[] getSortersForTest(int minimumElement, int maximumElement) {
        Sorter[] sorters = {new QuickSort(500),
                new QuickSort(250),
                new QuickSort(10),
                new QuickSort(1),
                new BucketSort(minimumElement, maximumElement)};

        return (Sorter<Integer>[]) sorters;
    }
}
