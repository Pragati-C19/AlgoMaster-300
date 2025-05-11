import java.util.*;

public class SurroundedRegion {
    
    public void solve(char[][] board) {
        
        int m = board.length;
        int n = board[0].length;

        System.out.println("\n Initially board looks like : ");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }

        // mark 'O' at edge as 'S' safe, checking row by row
        for (int i = 0; i < m; i++) {
            
            // j = 0 is constant 
            if (board[i][0] == 'O') {
                dfs(i, 0, board, m, n);
            }

            // j = n-1 is constant
            if (board[i][n-1] == 'O') {
                dfs(i, n-1, board, m, n);
            }
        }

        // mark 'O' at edge as 'S' safe, checking col by col
        for (int j = 0; j < n; j++) {
            
            // j = 0 is constant 
            if (board[0][j] == 'O') {
                dfs(0, j, board, m, n);
            }

            // j = n-1 is constant
            if (board[m-1][j] == 'O') {
                dfs(m-1, j, board, m, n);
            }
        }

        System.out.println("\n After marking Safe board looks like : ");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (board[i][j] == 'O') {

                    // If any cell has value 'O' then replace it with 'X'
                    board[i][j] = 'X';
                }
                
                if (board[i][j] == 'S') {
                    
                    // If any cell has value 'S' then replace it with 'O'
                    board[i][j] = 'O';
                }
            }
        }

        System.out.println("\n At End board looks like : ");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        
        return;
    }

    // Recursion Function : to replace '0' with 'X'
    private void dfs(int i, int j, char[][] board, int m, int n) {

        // Base Case :
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            
            System.out.println("        It's an end of row or col or block has char other than 'O'...");
            return;
        }

        System.out.println("       -> Visiting block is (" + i + ", " + j + ") : " + board[i][j]);

        // if it's '0' chnage it to 'X'
        board[i][j] = 'S';

        // Check Down Left Up Right of curr block
        dfs(i+1, j, board, m, n);
        dfs(i, j+1, board, m, n);
        dfs(i-1, j, board, m, n);
        dfs(i, j-1, board, m, n);

        return;
    }

    public static void main(String[] args){

        SurroundedRegion solution = new SurroundedRegion();

        char[][] board1 = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        System.out.println("\nResult1 -> ");     
        solution.solve(board1);

        char[][] board2 = {
            {'X'}
        };
        System.out.println("\nResult2 -> ");      
        solution.solve(board2);
        
    }
}

/*
 * Intuitions :
 
    1. we have given a matrix m x n contains 'X' and '0'
    2. Connect : A cell is connected to adj cells horizontally and vertically
    3. Region : to form a region connect every '0' cell
    4. Surround : the region is surrounded by 'X' cells if u can connect the region with 'X' cells
                and none of the region cells are on the edge 
    5. To capture surrounde region replace all '0' with 'X'


 * Pattern :
 
    1. Let's think of replacing all '0' with 'X'
    2. traverse through matrix (down, left, up, right)
    3. This que is almost same as numsIsLands
    4. just we need to check for '0' and replace it instead of just mark it as visited

 * Pseudo Code :
 
    function surroundedRegion (board){
    
        -> traverse matrix col by col
        
        for(i = 0 to m)
            for(j = 0 to n)

                -> call dfs function
                dfs(i, j, board, m, n)

        return
    
    }


    function dfs(i, j, board, m, n){
    
        -> Base Case : 
            It's an end of row or col or block has char 'X' we don't want to consider it
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == 'X')
            return;

        -> change '0' with 'X'
        board[i][j] = 'X'

        ~ need to check abt edge : if '0' is at edge then we can't replace it

        -> check DLUR
        dfs(i+1, j)
        dfs(i, j+1)
        dfs(i-1, j)
        dfs(i, j-1)
    
    }


 * Improvement :
 
    1. So here is a big and huge improvement
    2. will set edge 'O' as safe 'S'
    3. A cell (i, j) is on the border if:
        - It is in the first or last row → i == 0 or i == m - 1
        - It is in the first or last column → j == 0 or j == n - 1

    4. apan adhi i and j = 0 and j = n - 1 vale safe karu    
        for(i = 0 to m)
            if(board[i][0] == 'O') dfs(i, 0, board, m, n)
            if(board[i][n - 1] == 'O') dfs(i, n - 1, board, m, n)

    5. same apan j and i = 0 and i = m - 1 sathi karu 
        for(j = 0 to n)
            if(board[0][j] == 'O') dfs(0, j, board, m, n)
            if(board[m - 1][j] == 'O') dfs(m - 1, j, board, m, n)

    6. Then will flip and restore
        traverse whole matrix col by col and row by row

            if we find 'O' then will chnage it to X
            if we find 'S' then will change it to 'O' again
            we have marked 'S' for safety so we need to return it 'O' again

    7. in recursion function only one change is there
        board[i][j] = 'S'   instead of 'O'

 */