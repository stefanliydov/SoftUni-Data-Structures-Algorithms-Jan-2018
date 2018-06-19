package recursion;

public class EightQueenProblem {
    static final int size = 8;
    static boolean[][] attackedPlaces = new boolean[size][size];
    static String[][] chessboard = new String[size][size];

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                chessboard[i][j] = "-";
            }
        }

        putQueens(0, 0);
    }

    static void putQueens(int row, int queensPut) {
        if (queensPut == size) {

            for (String[] printRow : chessboard) {

                System.out.println(String.join(" ", printRow));

            }
            System.out.println();
        } else {
            for (int r = row; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if (!attackedPlaces[r][c]) {
                        chessboard[r][c] = "*";
                        queensPut++;
                        setAttackedPlaces(r, c);
                        putQueens(row + 1, queensPut);
                        chessboard[r][c] = "-";
                        queensPut--;
                        removeAttackedPlaces();
                    }
                }
            }
        }
    }

    private static void setAttackedPlaces(int r, int c) {
        for (int i = 0; i < 8; i++) {
            attackedPlaces[r][i] = true;
            attackedPlaces[i][c] = true;
        }
        int currRow = r - 1;
        int currCol = c - 1;
        while (currRow >= 0 && currCol >= 0) {
            attackedPlaces[currRow][currCol] = true;
            currRow--;
            currCol--;
        }
        currRow = r + 1;
        currCol = c + 1;
        while (currRow < 8 && currCol < 8) {
            attackedPlaces[currRow][currCol] = true;
            currRow++;
            currCol++;
        }
        currRow = r + 1;
        currCol = c - 1;
        while (currRow < 8 && currCol >= 0) {
            attackedPlaces[currRow][currCol] = true;
            currRow++;
            currCol--;
        }

        currRow = r - 1;
        currCol = c + 1;
        while (currCol < 8 && currRow >= 0) {
            attackedPlaces[currRow][currCol] = true;
            currRow--;
            currCol++;
        }
    }

    private static void removeAttackedPlaces() {
        attackedPlaces = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (chessboard[i][j].equals("*")) {
                    setAttackedPlaces(i, j);
                }
            }
        }
    }


}
