package recursion;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Arrays;
        import java.util.Scanner;

public class RecursiveArray {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Sum(arr,0));
    }
    public static int Sum(int[] arr, int index){
        if(index==arr.length-1){
            return arr[index];
        }
        return arr[index]+Sum(arr,index+1);
    }
}
