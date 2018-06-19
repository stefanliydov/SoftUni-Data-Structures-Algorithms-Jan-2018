package recursion;

import java.util.Scanner;

public class Permutations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");

        generate(0, arr);
    }

    public static void generate(int index, String[] arr) {
        if (index >= arr.length - 1) {
            for (String s : arr) {
                System.out.print(s+" ");
            }
            System.out.println();
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            generate(index + 1, arr);
            swap(arr, index, i);

        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

}
