package bunny_wars.main;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(1,"a");
        map.put(3,"a");
        Integer integer = map.higherKey(3);

        System.out.println(integer);
    }
}
