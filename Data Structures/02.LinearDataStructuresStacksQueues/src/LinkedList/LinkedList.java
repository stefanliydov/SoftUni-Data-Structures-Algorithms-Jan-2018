package LinkedList;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.setSize(0);

    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        Node newNode = new Node(item);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        size++;
    }

    public void addLast(E item) {
        Node newNode = new Node(item);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        E element = this.head.value;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
        }
        size--;
        return element;
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        E element = this.tail.value;
        if(this.size ==1){
            this.head = null;
            this.tail = null;
        } else{
            Node parent = this.head;
            while(parent.next !=this.tail){
                parent = parent.next;
            }
            this.tail = parent;
            this.tail.next =null;
        }
        size--;
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();

    }
    private class CustomIterator implements Iterator<E> {
        private Node node;

        public CustomIterator(){
            this.node = head;
        }
        @Override
        public boolean hasNext() {
            return this.node!=null;
        }

        @Override
        public E next() {
            Node lastNode= this.node;
            node = lastNode.next;
            return lastNode.value;
        }
    }

    private class Node {
        public E value;
        public Node next;


        public Node(E value) {
            this.value = value;
        }

    }
}
