import java.util.Scanner;

public class VariationsWithRep {
    static String[] elements;
    static String[] variations;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        int k = Integer.parseInt(scanner.nextLine());
        variations= new String[k];
        gen(0);
    }
    public static void gen(int index){
        if(index>=variations.length){
            System.out.println(String.join(" ",variations));
        }else{
            for (int i = 0; i <elements.length ; i++) {
                    variations[index] = elements[i];
                    gen(index+1);
            }
        }
    }
}
