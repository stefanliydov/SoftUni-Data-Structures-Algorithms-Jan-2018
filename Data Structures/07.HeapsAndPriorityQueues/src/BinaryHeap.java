import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int size;

    public BinaryHeap() {

        this.heap  = new ArrayList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public void insert(T element) {

        this.heap.add(element);
        this.heapifyUp(element, heap.size()-1);
    }

    private void heapifyUp(T element, int i) {

        Integer parentIndex = (i-1)/2;
        if(parentIndex<0){
            return;
        }
        T parent = this.heap.get(parentIndex);
        int compare = parent.compareTo((element));
        if(compare<0){
            this.swap(parentIndex, i);
            heapifyUp(this.heap.get(parentIndex), parentIndex);
        }
    }

    private void swap(Integer parentIndex, int i) {
        Collections.swap(this.heap,parentIndex,i);
    }

    public T peek() {
        if(this.heap.size()==0){
            throw new IllegalArgumentException();
        }
        return this.heap.get(0);
    }

    public T pull() {
        if(this.heap.size()==0){
            throw new IllegalArgumentException();
        }
        T element = this.heap.remove(0);
        return element;
    }
}
