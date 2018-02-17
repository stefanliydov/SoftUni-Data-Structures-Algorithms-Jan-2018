package CircularQueue;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CircularQueue<E> implements Iterable<E>{
    private static final int DEFAULT_SIZE = 4;

    private int size;
    private int count;
    private int head;
    private E[] arr;
    public CircularQueue() {
        this.size = DEFAULT_SIZE;
        this.head = 0;
        this.count = 0;
        this.arr =  (E[]) new Object[size];
    }

    public CircularQueue(int initialCapacity) {
        this.size=initialCapacity;
        this.count = 0;
        this.arr =  (E[]) new Object[size];
    }

    public int size() {
        return this.count;
    }

    private  void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if(this.count ==this.size){
            this.resize();
        }
        int index = (this.count+this.head)%this.size;
        this.arr[index] =element;
        this.count++;
    }

    private void resize() {
        E[] newArray = (E[]) new Object[this.size*2];
        this.copyAllElements(newArray);
        this.size*=2;

        this.arr = newArray;
    }

    private void copyAllElements(E[] newArray) {
        int startIndex = 0;
        for (int i = 0; i < this.count ; i++) {
            int index = (i+this.head)%this.size;
            newArray[i] = this.arr[index];
        }

    }


    public E dequeue() {
        if(this.count == 0){
            throw new IllegalArgumentException("Queue empty, operation cannot be performed!");
        }
        E element = this.arr[this.head];
        this.head = (this.head+1)%this.size;
        this.count--;
        return element;
    }

    public E[] toArray() {
       E[] newArray = (E[]) new Object[this.count];
       this.copyAllElements(newArray);
       return newArray;

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
