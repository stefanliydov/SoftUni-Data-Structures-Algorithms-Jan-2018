package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        Deque<Integer> deque = new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());


        for (Integer integer : stack) {
            System.out.println(integer);
        }


    }
}
