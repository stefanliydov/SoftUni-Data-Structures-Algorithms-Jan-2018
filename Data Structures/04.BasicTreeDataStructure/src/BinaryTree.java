import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> {

    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;


    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.value = value;
        this.leftChild = child;

    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightCHild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightCHild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        this.printIndentedPreOrder(this, indent, builder);
        return builder.toString();
    }

    private void printIndentedPreOrder(BinaryTree<T> node, int indent, StringBuilder builder) {
        if (node == null) {
            return;
        }
        builder.append(String.join("", Collections.nCopies(indent, " "))).append(node.value).append("\n");
        this.printIndentedPreOrder(node.leftChild, indent + 2, builder);
        this.printIndentedPreOrder(node.rightChild, indent + 2, builder);
    }

    public void eachInOrder(Consumer<T> consumer) {

        this.eachInOrder(this, consumer);
    }

    private void eachInOrder(BinaryTree<T> node, Consumer<T> consumer) {
        if(node == null){
            return;
        }
        this.eachInOrder(node.leftChild,consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.rightChild,consumer);
    }

    public void eachPostOrder(Consumer<T> consumer) {
        this.eachPostOrder(this,consumer);
    }
    private void eachPostOrder(BinaryTree<T> node, Consumer<T> consumer){
        if(node == null){
            return;
        }
        this.eachPostOrder(node.leftChild,consumer);
        this.eachPostOrder(node.rightChild,consumer);
        consumer.accept(node.value);
    }
}
