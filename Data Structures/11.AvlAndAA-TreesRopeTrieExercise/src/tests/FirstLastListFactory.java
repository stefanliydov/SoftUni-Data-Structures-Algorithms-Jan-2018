package tests;

public class FirstLastListFactory {
    public static <T extends Comparable<T>> tests.IFirstLastList<T> create() {
    	return new tests.FirstLastList<T>();
    }
}
