package balanced_tree;

public interface Tree<T extends Comparable<T>> {

    T search(T key);

    boolean insert(T key);

    void delete(T key);

    void print();

    void printInOrder();
}
