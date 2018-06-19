package double_linked_list;

import java.util.Iterator;

public class DoublyLinkedList<T> implements List<T> {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(T element) {
        if (this.head == null) {
            this.head = new Node(element);
            this.tail = this.head;
            size++;
            return;
        }

        Node lastNode = this.tail;
        this.tail = new Node(element);
        lastNode.setNext(this.tail);
        this.tail.setPrev(lastNode);
        size++;
    }

    @Override
    public void remove(T element) {

        Node current = this.head;
        // if(current.getData() == element){
        //     this.head = this.head.next;
        //     size--;
        // }
        while (current != null) {
            if (current.getData() == element) {
                Node parent = current.getPrev();
                Node child = current.getNext();
                size--;
                if (parent == null) {
                    this.head = this.head.next;
                } else if (child == null) {
                    parent.next = null;
                    this.tail = parent;
                } else {
                    parent.next = child;
                    child.prev = parent;
                }
            }

            current = current.next;
        }
    }

    @Override
    public void sort() {
        
    }

    @Override
    public void print() {

    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(this.head);
    }

    private class ListIterator implements Iterator<T> {

        private Node node;

        public ListIterator(Node head) {
            this.node = new Node(null);
            this.node.setNext(head);
        }

        @Override
        public boolean hasNext() {
            return this.node.next != null;
        }

        @Override
        public T next() {

            this.node = this.node.next;
            return this.node.getData();
        }
    }

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return this.prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}
