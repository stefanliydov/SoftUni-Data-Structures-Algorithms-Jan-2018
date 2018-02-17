public class Main2 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(8);
        tree.insert(10);
        tree.insert(12);
        tree.insert(11);
        tree.insert(6);
        tree.deleteMax();
    }
}
