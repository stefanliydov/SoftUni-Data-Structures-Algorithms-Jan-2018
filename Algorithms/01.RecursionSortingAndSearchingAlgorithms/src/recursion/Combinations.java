package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class Combinations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = Integer.parseInt(scanner.nextLine());
        combinatorics(arr, new int[size],0,0);
    }

    public static void combinatorics(int[] arr,int[] vector,int index, int size) {
        if(vector.length<= size){
            for (int i : vector) {
                System.out.print(i+" ");
            }
            System.out.println();
            return;
        }
        for (int i = index; i <arr.length ; i++) {
            vector[size] = arr[i];
            combinatorics(arr, vector, i+1,size+1);
        }
    }

}
