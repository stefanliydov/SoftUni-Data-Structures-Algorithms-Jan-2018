package CountOfOccurrences;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        Map<String, Integer> occurrences = new HashMap<>();
        for (String s : arr) {
            occurrences.putIfAbsent(s,0);
            occurrences.put(s,(occurrences.get(s)+1));
        }
        occurrences.entrySet().forEach((occurrence)->System.out.println(String.format("%s -> %d times",occurrence.getKey(),occurrence.getValue())));
    }
}
