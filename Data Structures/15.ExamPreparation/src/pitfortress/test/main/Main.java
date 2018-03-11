package pitfortress.test.main;

public class Main {
    public static void main(String[] args) {

        //    HashMap<String, List<Integer>> map = new HashMap<>();
        //    map.put("A", new ArrayList<>());
        //    map.put("B", new ArrayList<>());
        //    map.get("A").add(1);
        //    map.get("A").add(2);
        //    map.get("A").add(3);
        //    map.get("B").add(3);
        //    map.get("B").add(1);
        //    map.get("B").add(1);
        //    map.get("B").add(1);
        //    map.get("B").add(1);


        //    System.out.println(map.values().stream().mapToInt(List::size).sum());
        PitFortressCollection collection = new PitFortressCollection();
        collection.addMinion(1);
        collection.addMinion(2);
        collection.addMinion(2);
        collection.addMinion(2);
        collection.addMinion(2);
        collection.addMinion(2);
        collection.addMinion(3);
        collection.addMinion(4);
        System.out.println(collection.getMineCount());
    }
}
