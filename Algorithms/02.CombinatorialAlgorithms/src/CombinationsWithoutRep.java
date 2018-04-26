import java.util.Scanner;

public class CombinationsWithoutRep {
    static String[] elements;
    static String[] comb;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        comb = new String[scanner.nextInt()];
        genComb(0,0);

    }
    public static void genComb(int index,int start){
        if(index>=comb.length){
            System.out.println(String.join(" ",comb));
        }else{
            for (int i = start; i <elements.length ; i++) {
                comb[index] = elements[i];
                genComb(index+1,i+1);
            }
        }
    }
}
