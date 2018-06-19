package queue;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.put(5);
        queue.put(6);
        queue.put(7);
        queue.put(8);
        queue.put(9);
        queue.put(9);

       // System.out.println(queue.peek());
        while(queue.peek()!=null){
            System.out.println(queue.remove());
        }
    }
}
