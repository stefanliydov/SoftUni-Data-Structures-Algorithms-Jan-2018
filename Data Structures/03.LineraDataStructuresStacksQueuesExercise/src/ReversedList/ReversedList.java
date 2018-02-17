import java.util.Iterator;

public class ReversedList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 2;

    private E[] arr;
    private int count;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ReversedList() {
        count = 0;
        capacity = DEFAULT_CAPACITY;
        arr = (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public ReversedList(int capacity) {
        this.capacity = capacity;
        count = 0;
        arr = (E[]) new Object[capacity];
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return this.capacity;
    }

    public void add(E item) {
        if(this.count ==this.capacity){
            this.resize();
        }
        this.arr[this.count++] = item;

    }

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newArray = (E[]) new Object[this.capacity*2];
        this.capacity*=2;
        System.arraycopy(arr, 0, newArray, 0, arr.length);
        this.arr = newArray;
    }

    public E removeAt(int index) {

        E element = this.get(index);
        this.shift(index);
        this.count--;
        return element;
    }

    private void shift(int index) {
        System.arraycopy(this.arr, index + 1, this.arr, index, this.count - index);
    }

    public E get(int index) {
        if(index < 0 || index >= this.count){
            throw new IllegalArgumentException();
        }
        return this.arr[this.count-1-index];
    }

    public void set(int index, E element) {
        if(index < 0 || index >= this. count){
            throw new IllegalArgumentException();
        }
        this.arr[this.count-1-index] = element;
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<E>{

        private int index;
        public ReverseIterator() {
            this.index = count()-1;
        }

        @Override
        public boolean hasNext() {
            return index>=0;
        }

        @Override
        public E next() {
            return arr[index--];
        }
    }
}
