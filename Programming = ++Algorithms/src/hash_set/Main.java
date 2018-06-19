package hash_set;

public class Main {
    public static void main(String[] args) {
        HashList<String> list = new HashList<>();

        list.add("test");
        list.add("tect");
        list.add("abm");
        list.add("test");
        //System.out.println(list.contains("abm"));
        list.remove("abm");
    }
}
