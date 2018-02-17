package LongestSubsequence;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Integer> list = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        int symbol = list.get(0);
        int longestSequence = 1;
        int currSequence = 1;
        for (int i = 0; i < list.size() - 1; i++) {
            int num = list.get(i);
            int nextNum = list.get(i + 1);
            if (num == nextNum) {
                currSequence++;
                if (currSequence > longestSequence) {
                    longestSequence = currSequence;
                    symbol = num;

                }
            } else {
                currSequence = 1;
            }
        }
        System.out.println(String.join(" ", Collections.nCopies(longestSequence, String.valueOf(symbol))));

    }
}
