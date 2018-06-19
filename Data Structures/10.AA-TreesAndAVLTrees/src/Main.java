import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        TestClass t = new TestClass();
        TestClass a= t;

        Object ob = new Object();
        Set<Point> points = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        String command =scanner.next();

        switch (command){
            case "Pause":
                System.out.println("Puase");
                break;
            case "Stop":
                System.out.println("Stop");
                break;
            case "Start":
                System.out.println("Start");
                break;

        }



        Point a1 = new Point(1,1);
        Point a2 =  a1;
        Point a3 = new Point(2,1);
        Point a4 = new Point(1,1);
        Point a5 = new Point(2,1);
        points.add(a1);
        points.add(a2);
        points.add(a3);
        points.add(a4);
        points.add(a5);
        points.add(a5);
        points.add(a5);
        points.add(a5);
        points.add(a5);


        for (int i = 1; i < 5; i++) {
            System.out.println("=>"+i*10+1);
        }

    }

    public static int fuc(int m, int n){
        if(m==n || n==1){
            return 1;
        }
        return fuc(m-1,n-1)+n*fuc(m-1,n);
    }


    public static class Point{
        int a;
        int b;

        public Point(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
