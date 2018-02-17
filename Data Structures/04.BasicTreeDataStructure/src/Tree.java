import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private List<Tree<T>> children;


    public Tree(T value, Tree<T>... children) {
        this.value= value;
        this.children = Arrays.asList(children);
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {

        builder.append(String.join("", Collections.nCopies(indent, " "))+this.value).append("\n");
        for (Tree<T> child : this.children) {
            child.print(indent+2,builder);
        }


        return builder.toString();
    }


    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : this.children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new LinkedList<>();
        this.DFS(this,result);
        return result;
    }

    private void DFS(Tree<T> node, List<T> result) {
        for (Tree<T> child : node.children) {
            child.DFS(child, result);
        }
        result.add(node.value);
    }

    public Iterable<T> orderBFS() {
        Deque<Tree<T>> queue = new ArrayDeque<>();
        List<T> result = new LinkedList<>();
        queue.add(this);

        while(queue.size() >0){
            Tree<T> current = queue.removeFirst();
            result.add(current.value);

            for (Tree<T> child : current.children) {
                queue.addLast(child);
            }
        }
        return result;
    }

}