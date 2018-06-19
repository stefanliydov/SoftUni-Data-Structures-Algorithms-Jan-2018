package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class Rectangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] heights = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(findRectangle(heights,0));

    }

    private static int findRectangle(int[] arr, int index) {

        if (index >= arr.length - 1) {
            return 0;
        }

        int currNumber = arr[index];
        int multiplier = 1;
        for (int i = index + 1; i < arr.length; i++) {
            if (currNumber <= arr[i]) {
                multiplier++;
            } else {
                break;
            }
        }
        int biggest = currNumber * multiplier;
        multiplier = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (currNumber <= arr[i]) {
                multiplier++;
            } else {
                break;
            }
        }
        biggest += multiplier * (currNumber);
        return Math.max(biggest, findRectangle(arr, index + 1));
    }


}
