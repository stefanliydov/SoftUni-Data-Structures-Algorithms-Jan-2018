package recursion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecursiveFactorial {
    public static void main(String[] args) {


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(2);
        list1.add(2

        );
        list.retainAll(list1);

        for (Integer integer : list) {
            System.out.println(integer);
        }


        BigInteger[] arr = new BigInteger[5];
        String[] arr1 = new String[]{"12312", "1231231231", "1241213123", "123125151", "5151231"};
        for (int i = 0; i < arr1.length; i++) {
            arr[i] = new BigInteger(arr1[i]);
        }

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(factorial(n));
    }

    private static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
