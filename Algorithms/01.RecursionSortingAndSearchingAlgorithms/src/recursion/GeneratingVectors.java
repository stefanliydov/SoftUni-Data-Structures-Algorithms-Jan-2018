package recursion;

import java.util.Scanner;

public class GeneratingVectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        generate(0, arr);
    }
    public static void generate(int index, int[] arr){
        if(index == arr.length){
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
        }else {
            for (int i = 0; i <= 1; i++) {
                arr[index] = i;
                generate(index + 1, arr);
            }
        }
    }

}
