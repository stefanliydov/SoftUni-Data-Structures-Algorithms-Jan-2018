package list;

import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements List<T> {

    private Node head;
    private int size;

    public LinkedList() {
        this.size =0;
    }

    @Override
    public void add(T element) {
        if(this.head == null){
            this.head = new Node(element);
            size++;
            return;
        }


        Node current = this.head;


        while(current.nextNode !=null){
            current = current.nextNode;
        }
        current.nextNode = new Node(element);
        size++;
    }

    @Override
    public T get(int index) {
        if(index> size){
            throw new IndexOutOfBoundsException("Index was out of list bounds!");
        }

        Node temp = head;
        for (int i = 0; i <index-1; i++) {
            temp = temp.nextNode;
        }
        return temp.value;
    }

    @Override
    public boolean contains(T element){
        Node current = this.head;
        while(current!=null){
            if(current.value == element){
                return true;
            }
            current = current.nextNode;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int arraySize() {
        return this.size;
    }

    @Override
    public void deleteAt(int index) {
        if(index > this.size){
            throw new  IndexOutOfBoundsException();
        }

        Node tempParent = this.head;
        Node temp = this.head.nextNode;

        for (int i = 0; i <index-1 ; i++) {
            tempParent = temp;
            temp = temp.nextNode;
        }
        tempParent.setNextNode(temp.nextNode);
    }

    @Override
    public void delete(T element) {

        Node tempParent = this.head;
        Node temp = this.head.getNextNode();
        if(tempParent.getValue() == element){
            this.head = temp;
        }
        while(temp!=null){
            if(temp.getValue() == element){
                tempParent.setNextNode(temp.nextNode);
            }
            tempParent =temp;
            temp = temp.getNextNode();

        }


    }

    @Override
    public void insert(int index, T element) {
        if(index> this.size){
            throw new IndexOutOfBoundsException();
        }

        Node temp = this.head;

        for (int i = 0; i <index ; i++) {
            temp = temp.getNextNode();
        }

        Node spotNode = temp.getNextNode();
        temp.setNextNode(new Node(element));
        temp.getNextNode().setNextNode(spotNode);

    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(this.head);
    }

    @Override
    public void bubbleSort() {

    }

    @Override
    public void selectionSort() {

    }

    @Override
    public void insertionSort() {

    }

    private class LinkedListIterator implements Iterator<T>{

        private Node node;

        public LinkedListIterator(Node head) {
            this.node = new Node(null);
            this.node.setNextNode(head);
        }

        @Override
        public boolean hasNext() {
            return node.nextNode!=null;
        }

        @Override
        public T next() {
            node = node.nextNode;
            return this.node.getValue();
        }
    }


    private class Node{
        private T value;
        private Node nextNode;

        public Node(T value) {
            this.value =value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
