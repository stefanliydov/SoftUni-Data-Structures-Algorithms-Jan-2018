import java.math.BigInteger;
import java.util.Scanner;

public class NChooseKCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =Integer.parseInt(scanner.nextLine());
        int k =Integer.parseInt(scanner.nextLine());
        int diff = n-k;
        BigInteger first = factorial(n);
        BigInteger second = factorial(k);
        BigInteger third = factorial(diff);

        BigInteger result = factorial(n).divide(factorial(k).multiply(factorial(n-k)));
        System.out.println(result);

    }
    public static BigInteger factorial(long n){
        if(n ==1){
            return BigInteger.valueOf(1);
        }

        return factorial(n-1).multiply(BigInteger.valueOf(n));
    }
}
