package main.java;

/**
 * Created by: Josh
 * On: 4/21/13 12:59 PM
 */
public class TestCase<T extends Comparable<T>> {
    private T minimumElement;
    private T maximumElement;
    private T[] array;
    private String description;

    public TestCase(T minimumElement, T maximumElement, T[] array, String description) {
        this.minimumElement = minimumElement;
        this.maximumElement = maximumElement;
        this.array = array;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
