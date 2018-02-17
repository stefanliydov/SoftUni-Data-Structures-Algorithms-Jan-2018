package main;

import java.util.*;
import java.util.stream.Collectors;

public class Hierarchy<T> implements IHierarchy<T>,Iterable<T> {

    private Node root;
    private HashMap<T, Node> nodeByValue;

    public Hierarchy(T element) {
        this.root = new Node(element);
        this.nodeByValue = new HashMap<>();
        this.nodeByValue.put(element, this.root);
    }

    public void add(T parent, T child) {
        if (!this.nodeByValue.containsKey(parent)) {
            throw new IllegalArgumentException("No such parent element exists!");
        }
        if (this.nodeByValue.containsKey(child)) {
            throw new IllegalArgumentException("Child already exists!");
        }

        Node parentNode = this.nodeByValue.get(parent);
        Node childNode = new Node(child, parentNode);
        parentNode.addChild(childNode);
        this.nodeByValue.put(child, childNode);
    }

    public int getCount() {
        return this.nodeByValue.size();
    }

    public void remove(T element) {
        if(!this.nodeByValue.containsKey(element)){
            throw new IllegalArgumentException("No such element exists");
        }
        Node current = this.nodeByValue.get(element);
        if(current.parent == null){
            throw new IllegalStateException("You cannot delete the root!");
        }

        for (Node child : current.children) {
            child.parent = current.parent;
            current.parent.addChild(child);
        }

        current.parent.children.remove(current);
        nodeByValue.remove(element);

    }

    public boolean contains(T element) {
        return this.nodeByValue.containsKey(element);
    }

    public T getParent(T element) {
        if (!this.nodeByValue.containsKey(element)) {
            throw new IllegalArgumentException("No such element exists!");
        }
        return this.nodeByValue.get(element).parent == null? null : this.nodeByValue.get(element).parent.getValue();

    }

    public Iterable<T> getChildren(T element) {
        if (!this.nodeByValue.containsKey(element)) {
            throw new IllegalArgumentException("No such element exits!");
        }
        return nodeByValue.get(element).getChildren().stream().map(Node::getValue).collect(Collectors.toList());
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other) {
        List<T> commonNodes = new ArrayList<>();
        for (T node :
                this.nodeByValue.keySet()) {
            if (other.contains(node)) {
                commonNodes.add(node);
            }
        }

         return commonNodes;
    }

    @Override
    public Iterator<T> iterator() {
        // return nodeByValue.keySet().iterator();
        Deque<Node> queue = new ArrayDeque<>();
        Node current = this.root;
        queue.add(current);
        List<T> elementsByOrder = new LinkedList<>();
        while(queue.size() > 0){
            current = queue.removeFirst();
            elementsByOrder.add(current.value);
            for (Node child : current.getChildren()) {
                queue.add(child);
            }
        }
        return elementsByOrder.iterator();
    }
    private class Node {
        private T value;
        private Node parent;
        public List<Node> children;


        private Node(T value) {
            this.value = value;
            this.children = new ArrayList<>();
            this.parent = null;
        }

        private Node(T value, Node parent) {
            this.value = value;
            this.children = new ArrayList<>();
            this.parent = parent;
        }

        private void addChild(Node child) {
            this.children.add(child);
        }


        public List<Node> getChildren() {
            return Collections.unmodifiableList(children);
        }

        public T getValue() {
            return value;
        }
    }
}
