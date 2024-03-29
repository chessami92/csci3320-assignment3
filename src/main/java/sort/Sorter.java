package main.java.sort;

/**
 * Created by: Josh
 * On: 4/16/13 10:56 PM
 */
public interface Sorter <T extends Comparable<T>> {
    public void sort(T[] array);
    //A description of the sorting algorithm.
    public String getSorterDescription();
}
