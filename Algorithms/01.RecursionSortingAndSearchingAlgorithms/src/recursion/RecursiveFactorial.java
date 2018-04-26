package recursion;

import java.util.Scanner;

public class RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(factorial(n));
    }

    private static long factorial(int n) {
        if(n==0){
            return 1;
        }
        return n*factorial(n-1);
    }
}
