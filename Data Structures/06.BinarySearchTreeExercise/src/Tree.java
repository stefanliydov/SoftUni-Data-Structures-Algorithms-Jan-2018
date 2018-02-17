import java.util.*;

public  class Tree<T> {
    private T value;
    private List<Tree<T>> children;
    private Tree<T> parent;

    public Tree(T value) {
        this.value = value;
        children = new ArrayList<Tree<T>>();
    }

    public Tree(T value, T... params) {
        this.value = value;
        children = new ArrayList<Tree<T>>((Collection<? extends Tree<T>>) Arrays.asList(params));
    }

    public void addChild(Tree<T> child) {
        this.children.add(child);
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }
    public String print(int indent){
        for (Tree<T> child : this.children) {
            child.print(indent+2);
        }
       // System.out.println(String.join("", Collections.nCopies(indent," "))+this.value);
        return "Deepest node: "+this.value;
    }

    public List<Tree<T>> getChildren() {
        return this.children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }

    public T getValue() {

        return this.value;
    }

    public Tree<T> getParent() {
        return this.parent;
    }
}
