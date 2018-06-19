package recursion;

import java.util.*;

public class ConnectedAreas {
    private static List<Area> areas = new ArrayList<>();
    private static int rowSize;
    private static int colSize;
    private static List<String> markedPath = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rowSize = Integer.parseInt(scanner.nextLine());
        colSize = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            String[] line = scanner.nextLine().split("");
            System.arraycopy(line, 0, matrix[i], 0, colSize);
        }
        findAreas(matrix, 0, 0);
        String d = "";
        int count = 1;
        Collections.sort(areas);
        System.out.printf("Total areas found: %d\n",areas.size());
        for (Area area : areas) {
            System.out.printf(("Area #%d at %s\n"), count, area.toString());
            count++;
        }
    }

    private static void findAreas(String[][] matrix, int row, int col) {
        if (col == colSize) {
            findAreas(matrix, row + 1, 0);
        } else if (row == rowSize) {
            return;
        } else {
            if (!matrix[row][col].equals("*") && !markedPath.contains(row + "" + col)) {
                Area area = new Area(row, col);
                int size = findAreaSize(matrix, row, col);
                area.setSize(size);
                areas.add(area);
            }
            findAreas(matrix, row, col + 1);

        }
    }
    private static int findAreaSize(String[][] matrix, int row, int col) {
        if (!isValid(row, col)) {
            return 0;
        }
        if (matrix[row][col].equals("*") || markedPath.contains(row+""+col)) {
            return 0;
        }
        markPath(row,col);
        int size = 1;
        size += findAreaSize(matrix, row, col + 1);
        size += findAreaSize(matrix, row, col - 1);
        size += findAreaSize(matrix, row + 1, col);
        size += findAreaSize(matrix, row - 1, col);

        return size;
    }

    private static void markPath(int row, int col) {
        markedPath.add(row+""+col);
    }


    private static boolean isValid(int row, int col) {
        return (row >= 0 && row < rowSize
                && col >= 0 && col < colSize);
    }

    private static class Area implements Comparable {
        int row;
        int col;
        int size;

        public Area(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }

        @Override
        public int compareTo(Object o) {
            Area newArea = (Area) o;
            return  newArea.getSize()-this.size;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(this.row).append(", ").append(this.col).append("), size: ").append(this.getSize());
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            Area newArea = (Area)obj;
            return (newArea.col == this.col && newArea.row== this.row);
        }
    }
}
