package double_linked_list;

import java.util.Iterator;

public interface List<T> extends Iterable<T>{

    void add(T element);
    void remove(T element);
    void sort();
    void print();
    boolean contains(T element);
    int size();

    @Override
    Iterator<T> iterator();
}
