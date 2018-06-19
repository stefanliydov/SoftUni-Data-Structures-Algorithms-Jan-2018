package stack;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>{

    private T[] array;
    private int position;

    @SuppressWarnings("unchecked")
    public Stack() {
        this.array = (T[]) new Object[2];
        this.position = 0;
    }

    public void push(T element){

        this.array[this.position++] = element;

        if(this.position==this.array.length){
            increaseSize();
        }


    }

    private void increaseSize() {
        T[] newArray = (T[]) new Object[this.array.length*2];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
    }

    public T pop(){
        if(!this.isEmpty()) {
            T element = this.array[--position];
            if (this.position <= this.array.length / 2) {
                decreaseSize();
            }
        return element;
        }else {
            throw new IndexOutOfBoundsException();
        }
    }

    private void decreaseSize() {
        T[] newArray = (T[]) new Object[this.array.length/2];
        System.arraycopy(this.array, 0, newArray, 0, newArray.length);
        this.array = newArray;
    }

    public boolean isEmpty(){

        return this.position == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T>{

        private int pos;


        @Override
        public boolean hasNext() {
            return pos<position;
        }

        @Override
        public T next() {
            return array[pos++];
        }
    }
}
