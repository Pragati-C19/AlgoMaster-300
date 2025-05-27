import java.util.*;

class GameOfLife {
    public void gameOfLife(int[][] board) {
        int rows = board.length, cols = board[0].length;

        // Directions for 8 neighbors
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };

        // Step 1: Mark cells with intermediate states
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int liveNeighbors = 0;

                // Count live neighbors
                for (int[] d : directions) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && Math.abs(board[nr][nc]) == 1) {
                        liveNeighbors++;
                    }
                }

                // Apply the rules
                if (board[r][c] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[r][c] = 2; // Alive -> Dead
                }
                if (board[r][c] == 0 && liveNeighbors == 3) {
                    board[r][c] = 3; // Dead -> Alive
                }
            }
        }

        // Step 2: Convert intermediate states to final states
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 2) board[r][c] = 0;
                if (board[r][c] == 3) board[r][c] = 1;
            }
        }
    }

    public static void main(String[] args) {
        GameOfLife solution = new GameOfLife();

        int[][] board = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        };

        solution.gameOfLife(board);

        // Print output
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}

