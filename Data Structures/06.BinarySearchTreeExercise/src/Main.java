import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static HashMap<Integer, Tree<Integer>> tree = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfNodes = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfNodes-1; i++) {
            int[] nodes = Arrays
                    .stream(scanner
                            .nextLine()
                            .split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int parent = nodes[0];
            int child = nodes[1];
            if (!tree.containsKey(parent)) {
                tree.put(parent, new Tree<>(parent));
            }
            if (!tree.containsKey(child)) {
                tree.put(child, new Tree<>(child));
            }
            Tree<Integer> parentNode = tree.get(parent);
            Tree<Integer> childNode = tree.get(child);
            parentNode.addChild(childNode);
            childNode.setParent(parentNode);
        }
        Integer target = Integer.parseInt(scanner.nextLine());
      // List<String> root =  tree.values().stream().filter(x -> x.getChildren().size() >0 && x.getParent()!=null).map(Tree::getValue).sorted().map(String::valueOf).collect(Collectors.toList());
       // System.out.println("Middle nodes: "+String.join(" ",root));
        List<Tree<Integer>> root =  tree.values().stream().filter(x-> x.getParent() ==null).collect(Collectors.toList());
        Tree<Integer> currRoot = root.get(0);

        System.out.println("Subtrees of sum "+ target+":");
        subTreeDFS(currRoot,target);

    }

    public static void subTreeDFS(Tree<Integer> node,int sum ){

        int currentSum = node.getValue();
        for (Tree<Integer> integerTree : node.getChildren()) {
            currentSum+=integerTree.getValue();
            subTreeDFS(integerTree,sum );
        }
        if(currentSum == sum){
            Deque<String> subtree = new ArrayDeque<>();
            getSubTree(node,subtree);
            System.out.println(String.join(" ",subtree));
        }
    }

    private static void getSubTree(Tree<Integer> node, Deque<String> subtree) {
        subtree.addLast(String.valueOf(node.getValue()));
        for (Tree<Integer> integerTree : node.getChildren()) {
            getSubTree(integerTree,subtree);
        }
    }

    static int longestPath = 0;
    static Deque<Integer> queue = new ArrayDeque<>();
    public static void findLongestPath(Tree<Integer> node,int sum){

        sum+=node.getValue();
        if(sum>longestPath){
        queue.clear();
        longestPath =sum;
            updatePath(node);
        }
        for (Tree<Integer> integerTree : node.getChildren()) {
            findLongestPath(integerTree,sum);
            break;
        }
    }

    private static void updatePath(Tree<Integer> node) {
        Tree<Integer> start = node;
        queue.addFirst(start.getValue());
        while(start.getParent() !=null){
            start = start
                    .getParent();
            queue.addFirst(start.getValue());
        }
    }



    public static void dfs(Tree<Integer> node, int target, int sum){

        sum +=node.getValue();
        if(sum == target) {
            printPath(node);
        }
        for (Tree<Integer> child : node.getChildren()) {
            dfs(child,target,sum);
        }

    }

    private static void printPath(Tree<Integer> node) {

        Deque<Integer> list = new ArrayDeque<>();
        Tree<Integer> start = node;
        list.addFirst(start.getValue());
        while(start.getParent() !=null){
            start = start
                    .getParent();
            list.addFirst(start.getValue());
        }

        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
        System.out.println();
    }

}
