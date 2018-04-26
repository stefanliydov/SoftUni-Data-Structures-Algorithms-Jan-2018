package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    public static List<String> path = new ArrayList<>();
    public static int rows = 0;
    public static int cols = 0;
    public static List<String> markedTerritory = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
         rows = Integer.parseInt(bf.readLine());
         cols = Integer.parseInt(bf.readLine());
        String[][] labyrinth = new String[rows][cols];

        for (int i = 0; i <rows ; i++) {
            String[] row = bf.readLine().split("");
            for (int j = 0; j <cols ; j++) {
                labyrinth[i][j] = row[j];
            }
        }

        findPath(labyrinth,0,0,"");
    }

    private static void findPath(String[][] labyrinth,int row, int col, String dir) {
        if(!isInBounds(row,col)){
            return;
        }
        path.add(dir);

        if(labyrinth[row][col].equals("e")){
            printPath();
        }
        else if(!isVisited(row,col) && isFree(labyrinth,row,col)){
            mark(row,col);
            findPath(labyrinth,row+1,col,"D");
            findPath(labyrinth,row-1,col,"U");
            findPath(labyrinth,row,col+1,"R");
            findPath(labyrinth,row,col-1,"L");
            unMark(row,col);
        }
        path.remove(path.size()-1);



    }

    private static void printPath() {
        System.out.println(String.join("",path));
    }

    private static boolean isFree(String[][] labyrinth,int row, int col) {
        return labyrinth[row][col].equals("-");
    }

    private static boolean isVisited(int row, int col) {
       return markedTerritory.contains(row+""+col);
    }

    private static void mark(int row, int col) {
        markedTerritory.add(row+""+col);
    }

    private static void unMark(int row, int col) {
        String step = row+""+col;
        markedTerritory.remove(step);
    }

    private static boolean isInBounds(int row, int col) {
        if(row<0
                || col<0
                || row>=rows
                || col>=cols){
            return false;
        }
        return true;

    }
}
