import java.util.*;

public class SolveNQueens {

}

/*
 * 
 * Intuitions :
 * 
 * 1. Place n queeens on n x n board
 * 2. Each queen can attack other queens in the same row, column or diagonal
 * 3. We need to find all possible configurations of queens on the board such that no two queens attack each other
 * 
 * 
 * Pattern :
 * 
 * 1. Base Case : if(queenCount == 0) result.add(current) 
 * 2. We need to place first queen in on first column
 * 3. then mark all rows and colums and digonals corresponed to it as used
 * eg. If queen is placed at (0 x 0) 
 *  - then mark row(0) as used
 *  - then mark column(0) as used
 *  - then mark leftDiagonal(row + col) as used : to get leftDiagnals blocks check addition of row + col should be same as addition of (row x col) where queen is placed
 *  - then mark rightDiagona(row - col) as used : to get leftDiagnals blocks check substraction of row - col should be same as substraction of (row x col) where queen is placed
 * 4. It's giving output as row wise
 * - means we need to loop till end of the row and then add that string in current
 * 
 * 
 * Pseudo Code :
 * 
 * function solveNQueens(int n){
 * 
 *      result = new array
 *      current = new Array
 *      int queenCount = n
 *      
 *      backtrack(n, queenCount, current, result)
 *      
 *      return result;
 * }
 * 
 * 
 * function backtrack(int n, ){
 * 
 *      // Base Case 
 *      if(queensCount == 0){
 *          result.add(new Array(current))
 *          return
 *      }
 *  
 *      for(){
 *          
 *      }
 * 
 * }
 * 
 * 
 * 
 */