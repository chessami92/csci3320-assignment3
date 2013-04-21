package main.java;

/**
 * Created by: Josh
 * On: 4/21/13 12:59 PM
 */
public class TestCase<T extends Comparable<T>> {
    private T minimumElement;
    private T maximumElement;
    private T[] array;

    public TestCase(T minimumElement, T maximumElement, T[] array) {
        this.minimumElement = minimumElement;
        this.maximumElement = maximumElement;
        this.array = array;
    }

    public T getMinimumElement() {
        return minimumElement;
    }

    public T getMaximumElement() {
        return maximumElement;
    }

    public T[] getArray() {
        return array;
    }
}
