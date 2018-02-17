package SumAndAverage;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(String.format("Sum=%d; Average=%.2f"
                , arr.length > 0 ? Arrays.stream(arr).sum() : 0, Arrays.stream(arr).average().isPresent() ?Arrays.stream(arr).average().getAsDouble():0));
        }catch (NumberFormatException e){
            System.out.println("Sum=0; Average=0.00");
        }



    }
}
