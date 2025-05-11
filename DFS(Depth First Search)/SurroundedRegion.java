import java.util.*;

public class SurroundedRegion {
    
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

        -> check DLUR
        dfs(i+1, j)
        dfs(i, j+1)
        dfs(i-1, j)
        dfs(i, j-1)
    
    }


 */