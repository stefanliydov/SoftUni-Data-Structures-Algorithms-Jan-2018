package list;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

public class ArrayList<T extends Comparable<T>> implements List<T> {

    private static final int INITIAL_SIZE = 2;

    private T[] arr;
    private int position;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.arr = (T[]) new Comparable[INITIAL_SIZE
                ];
        this.position = 0;
    }

    @Override
    public void add(T element) {
        this.arr[this.position++] = element;
        if (this.position == this.arr.length) {
            this.increaseSize();
        }

    }

    @Override
    public boolean contains(T element) {
        for (T t : this.arr) {
            if (t == element) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void insert(int index, T element) {
        if (index > position) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = position; i > index; i--) {
            this.arr[i] = this.arr[i - 1];
        }
        this.arr[index] = element;
        position++;

        if (position >= this.arr.length) {
            this.increaseSize();
        }

    }

    @Override
    public int size() {
        return this.position;
    }

    @Override
    public int arraySize() {
        return this.arr.length;
    }

    @Override
    public void deleteAt(int index) {
        if (index >= position) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < position - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }
        position--;

        if (position < this.arr.length / 2) {
            this.decreaseSize();
        }

    }


    @Override
    public T get(int index) {
        return this.arr[index];
    }

    @Override
    public void delete(T element) {
        throw new NotImplementedException();

    }

    @SuppressWarnings("unchecked")
    private void decreaseSize() {
        T[] newArr = (T[]) new Comparable[this.arr.length / 2];
        System.arraycopy(this.arr, 0, newArr, 0, position);
        this.arr = newArr;
    }

    @SuppressWarnings("unchecked")
    private void increaseSize() {
        T[] newArr = (T[]) new Comparable[this.arr.length * 2];
        System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
        this.arr = newArr;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public void bubbleSort() {
        int length = this.size() - 1;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int j = 0; j < length; j++) {
                int comp = this.arr[j].compareTo(this.arr[j + 1]);
                if (comp > 0) {
                    swap(j, j + 1);
                    swapped = true;
                }
            }
            length--;
        }


    }

    @Override
    public void selectionSort() {

        for (int i = 0; i < this.size() - 1; i++) {
            T smallest = this.arr[i];
            int index = 0;
            for (int j = i + 1; j < this.size(); j++) {
                int comp = smallest.compareTo(this.arr[j]);
                if (comp > 0) {
                    smallest = this.arr[j];
                    index = j;
                }
            }

            if (smallest != this.arr[0]) {
                swap(i, index);
            }
        }


    }

    @Override
    public void insertionSort() {

        for (int i = 1; i < this.size(); i++) {
            T element = this.arr[i];
            int index = -1;
            for (int j = 0; j < i; j++) {
                int comp = this.arr[j].compareTo(element);
                if (comp > 0) {
                    index = j;
                    break;
                }
            }
            if (index > -1) {
                insertAndRemoveElement(index, i);
            }
        }


    }

    private void insertAndRemoveElement(int index, int i) {
        T element = this.arr[i];
        this.deleteAt(i);
        this.insert(index, element);
    }

    private void swap(int j, int i) {
        T temp = this.arr[j];
        this.arr[j] = this.arr[i];
        this.arr[i] = temp;
    }

    private class ArrayListIterator implements Iterator<T> {

        private int pos;

        ArrayListIterator() {
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            return arr[pos] != null;
        }

        @Override
        public T next() {
            return arr[pos++];
        }
    }
}
