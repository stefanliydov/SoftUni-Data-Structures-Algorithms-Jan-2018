package balanced_tree;

public class Main {
    public static void main(String[] args) {

        Tree<Integer> tree = new BalancedTree<>();

      tree.insert(15);
      tree.insert(20);
      tree.insert(8);
      tree.insert(10);
      tree.insert(5);
      tree.insert(16);
      tree.insert(13);
      tree.insert(2);
      tree.insert(12);
      tree.insert(1);
      tree.insert(-1);
      tree.insert(21);
      tree.insert(9);
      tree.insert(3);
      tree.insert(2);




        tree.print();
        tree.printInOrder();

        String debug = "";

    }
}
