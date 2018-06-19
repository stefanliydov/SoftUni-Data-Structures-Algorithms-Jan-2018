package double_linked_list;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new DoublyLinkedList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);


        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
