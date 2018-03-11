package limited_memory.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main  {
    public static void main(String[] args) {
        LimitedMemoryCollection<String, Integer> map = new LimitedMemoryCollection<>(4);

        map.set("A",1);
        map.set("B",2);
        map.set("C",3);
        map.set("D",4);

        for (Pair<String, Integer> stringIntegerPair : map) {
            System.out.println(stringIntegerPair.getKey()+"->"+stringIntegerPair.getValue());
        }

        List<Pair<String,Integer>> list = new LinkedList<>();
        Pair<String,Integer> pair = new Pair<>("A",2);
        Pair<String,Integer> pair1 = new Pair<>("B",3);
        list.add(pair);
        list.add(pair1);
        list.remove(pair);
        System.out.println(list.size());
    }
}
