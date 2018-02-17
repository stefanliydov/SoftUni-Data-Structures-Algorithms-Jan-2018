package LinkedList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        LinkedList linkedList = new LinkedList<Integer>();
        ArrayList list = new ArrayList<Integer>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        java.util.LinkedList list2 = new java.util.LinkedList<Integer>();

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.removeFirst();
        linkedList.removeLast();
        for (Object num:
             linkedList) {
            System.out.println(num);
        }
    }
}
