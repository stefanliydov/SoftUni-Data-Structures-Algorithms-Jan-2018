package balanced_tree;

import java.util.Collections;

public class BalancedTree<T extends Comparable <T>> implements Tree<T>  {
    private Node root;


    public T search(T key) {
        return search(key, root);
    }

    private T search(T key, Node node) {
        if (node == null) {
            System.out.println("Елементът не е намерен!");
            return null;
        }
        int compare = key.compareTo(node.value);
        if(compare<0){
            return search(key, node.leftChild);
        }else if(compare>0){
            return search(key, node.rightChild);
        }

        System.out.println("Елементът е намерен!");
        return key;
    }

    public boolean insert(T key) {
        if(this.root == null){
            this.root = new Node(key);
            System.out.println("Елементът със стойност "+key+" е добавен успешно!");
            return true;
        }
        return insert(key,null, this.root);



    }


    private boolean insert(T key,Node parent, Node node) {
        if(node == null){
            int compare = key.compareTo(parent.value);
            node = new Node(key);
            if(compare<0){
                parent.leftChild =node;
            }else{
                parent.rightChild=node;
            }
            System.out.println("Елементът със стойност "+key+" е добавен успешно!");
            return true;
        }
        int compare = key.compareTo(node.value);
        if(compare<0){
            return insert(key,node, node.leftChild);
        }else if(compare>0) {
            return insert(key,node, node.rightChild);
        }
        System.out.println("Елементът със стойност "+key+" е вече в дървото!");
        return false;
    }

    @Override
    public void delete(T key) {
        root = delete(key, this.root);
    }

    private Node delete(T key, Node node) {
        if(node == null){
            System.out.println("Елементът не съществува!");
            return node;
        }
        int compare = key.compareTo(node.value);
        if(compare<0){
            node.leftChild = delete(key, node.leftChild);
        }else if(compare>0){
            node.rightChild = delete(key, node.rightChild);
        }

        // Element was found!!!
        else {
            if (node.rightChild == null && node.leftChild == null) {
                return null;
            } else if (node.rightChild == null) {
                return node.leftChild;
            } else if (node.leftChild == null) {
                return node.rightChild;
            } else {
                node.value = minValue(node.rightChild);
                node.rightChild = delete(node.value, node.rightChild);
            }
        }
        return node;
    }

    private T minValue(Node root){
        T minValue = root.value;
        while (root.leftChild!=null){
            minValue = root.leftChild.value;
            root = root.leftChild;
        }
        return minValue;
    }

    @Override
    public void print() {
        print(this.root,0);
    }

    private void print(Node root,int spaces) {
        if(root!=null) {
            System.out.println(String.join("", Collections.nCopies(spaces, " ")) + root.value);
            print(root.leftChild, spaces + 2);
            print(root.rightChild, spaces + 2);
        }
    }

    @Override
    public void printInOrder() {

        printInOrder(this.root);
    }

    private void printInOrder(Node root) {
        if(root!=null) {
            printInOrder(root.leftChild);
            System.out.print(root.value + " ");
            printInOrder(root.rightChild);
        }

    }

    private class Node {
        private T value;
        private Node leftChild;
        private Node rightChild;

         Node(T value) {
            this.value = value;
        }

    }
}
