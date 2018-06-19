package queue;

import java.util.Iterator;

public class Queue<T>{

    private T[] array;
    private int front;
    private int rear;
    private boolean isEmpty;
    @SuppressWarnings("unchecked")
    public Queue() {
        this.array = (T[]) new Object[8];
        this.isEmpty = true;
    }

    public void put(T element){
        if(rear == front && !isEmpty) {
            System.out.println("Опашката е пълна!");
            return;
        }
            array[rear++] = element;
            this.isEmpty = false;
            if (this.rear >= this.array.length) {
                this.rear = 0;
            }
    }

    public T remove(){
        if(rear ==  front && isEmpty){
            System.out.println("Опашката е празна!");
            return null;
        }
        T element = this.array[front++];
        if(this.front >= this.array.length){
            this.front = 0;
        }
        if(this.front == this.rear){
            this.isEmpty = true;
        }
        return element;
    }

    public T peek(){
        if(this.isEmpty){
            return null;
        }
        return this.array[front];
    }


}
