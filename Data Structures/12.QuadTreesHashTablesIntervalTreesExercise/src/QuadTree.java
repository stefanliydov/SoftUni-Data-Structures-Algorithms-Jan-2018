import java.util.List;

public class QuadTree<T extends Boundable> {

    public static final int DEFAULT_MAX_DEPTH = 5;

    private int maxDepth;

    private Node<T> root;

    private Rectangle bounds;

    private int count;

    public QuadTree(int width, int height) {
        this(width, height, DEFAULT_MAX_DEPTH);
    }

    public QuadTree(int width, int height, int maxDepth) {
        this.root = new Node<T>(0, 0, width, height);
        this.bounds = this.root.getBounds();
        this.maxDepth = maxDepth;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getCount() {
        return this.count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public boolean insert(T item) {
        if (!item.getBounds().isInside(bounds)) {
            return false;
        }
        int depth = 1;
        Node<T> currentNode = this.root;
        while (currentNode.getChildren()!=null){
            int quadrant = getQuadrant(currentNode,item.getBounds());
        }
        return true;
    }

    private int getQuadrant(Node<T> currentNode, Rectangle bounds) {
        throw new UnsupportedOperationException();
    }

    public List<T> report(Rectangle bounds) {
        throw new UnsupportedOperationException();
    }
}
