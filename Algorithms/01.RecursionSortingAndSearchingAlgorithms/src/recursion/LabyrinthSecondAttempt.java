package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LabyrinthSecondAttempt {
    private static List<String> path = new ArrayList<>();
    private static int rowSize;
    private static int colSize;
    private static List<String> markedTerritory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rowSize = Integer.parseInt(scanner.nextLine());
        colSize = Integer.parseInt(scanner.nextLine());
        String[][] labyrinth = new String[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            String[] line = scanner.nextLine().split("");
            for (int j = 0; j <colSize; j++) {
                labyrinth[i][j] = line[j];
            }
        }
        findPath(labyrinth,0,0,"");
    }

    public static void findPath(String[][] labyrinth, int row, int col,String direction) {
        if (!isPathValid(row, col)) {
            return;
        }
        if(labyrinth[row][col].equals("*")){
            return;
        }
        path.add(direction);
        if (labyrinth[row][col].equals("e")) {
            System.out.println(String.join("", path));
            path.remove(path.size()-1);
            return;
        }

        mark(row,col);
        findPath(labyrinth, row - 1, col,"U");
        findPath(labyrinth, row + 1, col,"D");
        findPath(labyrinth, row, col + 1,"R");
        findPath(labyrinth, row, col - 1,"L");
        unmark(row,col);
    }


    private static void unmark(int row, int col) {
        markedTerritory.remove(row+""+col);
        path.remove(path.size()-1);
    }

    private static void mark(int row, int col) {
        markedTerritory.add(row+""+col);

    }


    private static boolean isPathValid(int row, int col) {
        boolean test= (!markedTerritory.contains(row + "" + col)
                && row >= 0 && row < rowSize
                && col >= 0 && col < colSize);
        return test;
    }

}
