package main.java.sort;

/**
 * Created by: Josh
 * On: 4/20/13 4:20 PM
 */
public class BucketSort implements Sorter<Integer> {
    private Integer minimumElement;
    private Integer maximumElement;

    /*
     * Constructor that sets the minimum and maximum elements in the array to be sorted.
     */
    public BucketSort(Integer minimumElement, Integer maximumElement) {
        if (maximumElement < minimumElement) {
            throw new IllegalArgumentException("Maximum element cannot be less than the minimum element.");
        }
        this.minimumElement = minimumElement;
        this.maximumElement = maximumElement;
    }

    /*
     * Sorting method that creates the buckets, fills them, then outputs the result
     * back into the array to create a sorted array.
     */
    @Override
    public void sort(Integer[] array) {
        int[] buckets = new int[maximumElement - minimumElement + 1];

        //Count in the buckets how many times each element appears.
        for (Integer i : array) {
            ++buckets[i - minimumElement];
        }

        int sortedArrayPointer = 0;

        //Go through each of the buckets.
        for (int i = 0; i < buckets.length; ++i) {
            //For the count in each bucket, put that many of the bucket number into the array.
            for (int j = 0; j < buckets[i]; ++j) {
                array[sortedArrayPointer++] = i + minimumElement;
            }
        }
    }

    @Override
    public String getSorterDescription() {
        return "Bucket sort";
    }
}
