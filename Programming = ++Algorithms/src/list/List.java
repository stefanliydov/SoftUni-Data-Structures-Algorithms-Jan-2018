package list;

import java.util.Iterator;

public interface List<T extends Comparable<T>> extends Iterable<T> {

    void add(T element);

    boolean contains(T element);

    int size();

    int arraySize();

    void deleteAt(int index);

    T get(int index);

    void delete(T element);

    void insert(int index, T element);

    Iterator<T> iterator();

    void bubbleSort();

    void selectionSort();

    void insertionSort();

}
