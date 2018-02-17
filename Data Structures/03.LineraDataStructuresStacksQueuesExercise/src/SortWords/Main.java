package SortWords;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> arr = Arrays.asList(scanner.nextLine().split(" "));

        System.out.println(String.join(" ",arr.stream().sorted(String::compareTo).collect(Collectors.toList())));
    }
}
