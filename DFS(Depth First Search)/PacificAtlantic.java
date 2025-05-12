import java.util.*;

public class PacificAtlantic{

}


/*
 * Intuitions :
 
    1. We have given a matrix (m x n)
    2. Pacific Ocean touches the matrix from left and top edge
            Top -> row (i) = 0 and col (j) varies
            Left -> row (i) varies and col (j) = 0
    3. Atlantic Ocean touches the matrix from right and bottom edge
            Bottom -> row (i) = m - 1 and col (j) varies
            right -> row (i) varies and col (j) = n - 1
    4. Goal is to find the cell from which rain water can flow to both pacific and atlantic oceans

 * Pattern :
 
    1. What I need to check at every cell?
        check if any of it's BRUL have less num that him?..
        if yes then will try to find if it can be connected to any oceans
    2. mala itka mahitiye top and left chya edge parynt koni konta cell ala mhnje it can flow to pacific ocean
    3. bottom or right chya edge la paryant ala konta cell mhnje it can flow to atlantic ocean
    4. tr mala chances baghaychet ki donhi paiki kontya ocean la hit hotay ka te
    
    5. Start from the Pacific ocean borders (top and left) and mark all cells that can reach Pacific.
    6. Start from the Atlantic ocean borders (bottom and right) and mark all cells that can reach Atlantic.
    7. The cells that are marked in both sets → are the answer.
    8. Water flows from high to low. But instead of checking from each cell to ocean, you check where ocean water could come from 
        — i.e., reverse the flow logic.


 * Pseudo Code :
    
    function pacificAtlantic(heights) {

        result = new array

        -> Create 2 boolean matrix to check if the cell is visited and going in specific ocean's direction
        pacificOcean
        atlanticOcean 

        -> filled those both matrix as false at start

        -> check left and right why ?.. bcoa both's i is varies
            for(i = 0 to m)

                -> call dfs for left side to check if it goes in pacificOcean?
                    dfs(i, 0, pacificOcean, heights[i][0])
                
                -> call dfs for right side to check if it goes in atlanticOcean?
                    dfs(i, n - 1, atlanticOcean, heights[i][n-1])


        -> check top and bottom now why?... bcoz both j is varies\
            for(j = 0 to n)

                -> call dfs for top side to check if it goes in pacificOcean?
                    dfs(0, j, pacificOcean, heights[i][0])
                
                -> call dfs for bottom side to check if it goes in atlanticOcean?
                    dfs(m-1, j, atlanticOcean, heights[i][n-1])


        -> Now we have two matrix which tells if the cells meets to pacific ocean or atlantic ocean
            we need an result where a cell meets to both
            so let's check which cell is true in both matrix 
            
            for(i = 0 to m)
                for(j = 0 to n)

                    if(pacificOcean && atlanticOcean)
                        result.add(heights[i][j])


        return result
    
    }

    function boolean dfs (i, j, visitedCell, prevHeight, heights, m, n){
    
        -> Base Case :
            It's an end of row or col or cell is already visited or cell's height is greater than prevHeight we don't want to consider it
                if (i < 0 || i >= m || j < 0 || j >= n || heights[i][j] < prevHeights)
                    return

        -> mark this cell as visited
            viaited[i][j] = true

        -> check BRUL
            dfs(i+1, j)
            dfs(i, j+1)
            dfs(i-1, j)
            dfs(i, j-1)
       
        return
    }
 
 */