package main.java;

import main.java.sort.QuickSort;
import main.java.sort.Sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by: Josh
 * On: 4/16/13 10:46 PM
 */
public class TestEfficiency {
    private static final Random random = new Random();

    public static void main(String[] args) {
        List<Sorter<Integer>> sorters = new ArrayList<Sorter<Integer>>();
        sorters.add(new QuickSort<Integer>());

        Integer[] test = createArray(1000, 500);

        for (Sorter<Integer> sorter : sorters) {
            sorter.sort(test);
            printSortedArray(test);
            System.out.println(sorter.getSorterDescription());
        }
    }

    private static Integer[] createArray(int length, int maxValue) {
        Integer[] array = new Integer[length];

        for (int i = 0; i < length; ++i) {
            array[i] = random.nextInt(2 * maxValue + 1) - maxValue;
        }

        return array;
    }

    /*
     * Print out the array to the screen and also check to make sure it is sorted.
     */
    private static void printSortedArray(Integer[] array) {
        int lowest = array[0];

        System.out.print("[");
        for (int i = 1; i < array.length - 1; ++i) {
            if (array[i] < lowest) {
                throw new IllegalArgumentException("Array is not sorted");
            }
            lowest = array[i];
            System.out.printf("%s, ", array[i]);
        }
        System.out.printf("%s]\n", array[array.length - 1]);
    }
}
