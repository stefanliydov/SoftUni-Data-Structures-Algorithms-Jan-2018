package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class GeneratingCombinations {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int border = Integer.parseInt(bf.readLine());
        generateCombinations(new int[border],arr, 0, 0);
    }

    private static void generateCombinations(int[] vector, int[] set, int index, int border) {
        if(index>=vector.length){
            for (int i : vector) {
                System.out.print(i+" ");
            }
            System.out.println();
        }else {
            for (int i = border; i < set.length; i++) {
                vector[index] = set[i];
                generateCombinations(vector, set, index+1, i+1);
            }
        }
    }
}
