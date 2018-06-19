package list;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        System.out.println(S tring.format("%.2f",0.1231231231));

        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(5);
        list.add(6);
        list.add(3);
        list.add(10);
        list.add(4);
        list.add(1);
        list.add(2);
        list.insertionSort();
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }
}
