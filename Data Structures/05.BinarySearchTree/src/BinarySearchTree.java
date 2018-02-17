import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }
    private BinarySearchTree(Node node) {
        this.copy(node);
    }

    private void copy(Node node) {
        if(node == null){
            return;
        }
        this.insert(node.value );
        this.copy(node.left);
        this.copy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            int compare = current.value.compareTo(value);

            if (compare > 0) {
                parent = current;
                current = current.left;
            } else if (compare < 0) {
                parent = current;
                current = current.right;
            } else {
                return;
            }

        }
        Node newNode = new Node(value);
        if (parent.value.compareTo(value) > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

    }

    public boolean contains(T value) {

        Node current = this.root;

        while (current != null) {
            int compare = current.value.compareTo(value);
            if (compare > 0) {
                current = current.left;
            } else if (compare < 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;

        while (current != null) {
            int compare = current.value.compareTo(item);
            if (compare > 0) {
                current = current.left;
            } else if (compare < 0) {
                current = current.right;
            } else {
                return new BinarySearchTree<>(current);
            }
        }
        return null;
    }
    public void eachInOrder(Consumer<T> consumer) {
    }
    private void eachInOrder(Node node, Consumer<T> consumer){
        if(node ==null){
            return;
        }
        this.eachInOrder(node.left,consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right,consumer

        );
    }


    public Iterable<T> range(T from, T to) {
        throw new UnsupportedOperationException();
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

