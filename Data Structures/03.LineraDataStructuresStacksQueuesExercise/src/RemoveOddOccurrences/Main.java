package RemoveOddOccurrences;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> list = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());

        System.out.println(String.join(" ",list.stream().filter((a) -> (list).stream().filter(b -> Objects.equals(a, b)).count() % 2 == 0)
                .collect(Collectors.toList())));
    }
}
