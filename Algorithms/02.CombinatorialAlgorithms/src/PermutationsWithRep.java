import java.util.Scanner;

public class PermutationsWithRep {
    static String[] elements;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");

        gen(0);
    }

    public static void gen(int index) {

        if (index >= elements.length) {
            System.out.println(String.join(" ", elements));
        } else {
            gen(index+1);
            for (int i = index+1; i <elements.length ; i++) {
                swap(index,i);
                gen(index+1);
                swap(index,i);
            }
        }
    }
    public static void swap(int indexFirst, int indexSecond){
        String temp = elements[indexFirst];
        elements[indexFirst] = elements[indexSecond];
        elements[indexSecond] =temp;
    }
}
