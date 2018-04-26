import java.util.Scanner;

public class VariationsWithoutRep {
    static String[] elements;
    static String[] variations;
    static boolean[] used;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        int k = Integer.parseInt(scanner.nextLine());
        variations= new String[k];
        used = new boolean[elements.length];
        gen(0);
    }
    public static void gen(int index){
        if(index>=variations.length){
            System.out.println(String.join(" ",variations));
        }else{
            for (int i = 0; i <elements.length ; i++) {
                if(!used[i]){
                    used[i] = true;
                    variations[index] = elements[i];
                    gen(index+1);
                    used[i] = false;
                }
            }
        }
    }
}
