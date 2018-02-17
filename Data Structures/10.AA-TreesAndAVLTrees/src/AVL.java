import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            node.left = this.insert(node.left, item);
        } else if (cmp > 0) {
            node.right = this.insert(node.right, item);
        }
        int balance = this.height(node.left) - this.height(node.right);
        if(balance <= 1 && balance > -1){
            return node;
        }
        if(balance >= 2){
            int childBalance = this.height(node.left.left) - this.height(node.right.right);
            if(balance <0){
                node.left= this.RotateLeft(node.left);
            }
        }
        if(balance <= -2){

        }

        return node;
    }

    private Node<T> RotateLeft(Node<T> node) {
        Node<T> newRoot =node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        newRoot.height = 1 +this.height(newRoot.left)+ this.height(newRoot.right);
        return newRoot;
    }

    private int height(Node<T> node) {
        if(node == null){
            return 0;
        }
        return node.height;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        }

        return node;
    }
}
