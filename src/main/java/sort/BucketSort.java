package main.java.sort;

/**
 * Created by: Josh
 * On: 4/20/13 4:20 PM
 */
public class BucketSort implements Sorter<Integer> {
    private Integer minimumElement;
    private Integer maximumElement;

    public BucketSort(Integer minimumElement, Integer maximumElement) {
        if (maximumElement < minimumElement) {
            throw new IllegalArgumentException("Maximum element cannot be less than the minimum element.");
        }
        this.minimumElement = minimumElement;
        this.maximumElement = maximumElement;
    }

    @Override
    public void sort(Integer[] array) {
        int[] buckets = new int[maximumElement - minimumElement];

        for (Integer i : array) {
            ++buckets[i - minimumElement];
        }

        int sortedArrayPointer = 0;

        for (int i = 0; i < buckets.length; ++i) {
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
