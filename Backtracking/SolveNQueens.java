import java.util.*;

public class SolveNQueens {

    //Driver Function
    public List<List<String>> solveNQueens(int n) {
        
    }

    // Helper Function : Backtrack in recursion
    private void backtrack(int n, int col, Map<Integer, Boolean> checkLeftRows, Map<Integer, Boolean> checkUpperDiagonal, Map<Integer, Boolean> checkLowerDiagonal, List<String> current, List<List<String>> Result) {

        return;
    }

    // Helper Function : As we wanted string in "..Q." format so we need to generate it before pusshing in current
    private String generateString(int n, int row) {

        StringBuilder colStr = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            if (i == row) {
                
                colStr.append("Q");
            
            } else {
                    
                colStr.append(".");
                    
            }
                    
        }
                    
        return colStr.toString();
                
    }

    public static void main(String[] args){
        SolveNQueens solution = new SolveNQueens();

        int n1 = 4;
        System.out.println("Output1: " + solution.solveNQueens(n1) + "\n");

    }

}

/*
 * //? Took help from love Babber video and ig it's nothing different than what logic I was using, but it helped me in pseudo code writing 
 * 
 * Intuitions :
 * 
 * 1. Place n queeens on n x n board
 * 2. Each queen can attack other queens in the same row, column or diagonal
 * 3. We need to find all possible configurations of queens on the board such that no two queens attack each other
 * 4. Coditions : 
 * - each row has 1 queen
 * - each column has 1 queen
 * - No two queen attack each other
 * 5. We'll travese row in one col remaining recursion will do
 * 
 * 
 * Approach 
 * 
 * 1. Without HashMap -
 * We don't need to check Up, Up-Right, Right, Down-Right, Down for isSafe, 
 * bcoz we are traversing from left to right and we haven't place anything on right yet so let's skip it that check
 * - to check Same Row -> Left is safe.. row is same, col--
 * - to check Upper Diagonal -> UP-Left is safe.. row--, col--
 * - to check Lower Diagonal -> Down-Left is safe.. row++, col--
 * 
 * 2. With HashMap -
 * - Map<rowNumber, boolean> checkLeftRows-> which stores rowNumber is occupied by Q or not
 * - Map<row+col, boolean> checkLowerDiagonal -> which stores (row+col) is occupied by Q or not
 * - Map<row-col, boolean> checkUpperDiagonal -> which stores (row-col) is occupied by Q or not
 * 
 * Pattern :
 * 
 * 1. Base Case : if(row == n) result.add(current) 
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
 * Why check row == n instead of queenCount == 0?
 * - We only place one queen per row, so tracking rows is equivalent to tracking the number of placed queens.
 * - If we reach row == n, it means we have successfully placed n queens (one in each row).
 * - If we used queenCount, we'd have to manually decrease it after placing a queen, which adds extra complexity.
 * - Since the recursion naturally moves row by row, we just check if we've placed queens in all n rows → done!
 * 
 * 
 * Pseudo Code :
 * 
 * function solveNQueens(int n){
 * 
 *      result = new array
 *      current = new Array
 * 
 *      // I'm traveling column by column 
 *      int col = 0
 *      
 *      Map<Integer, Boolean> checkLeftRows = new Hashmap  // to track row for Q
 *      Map<Integer, boolean> checkUpperDiagonal = new Hashmap  // to track upper diagonal for Q
 *      Map<Integer, boolean> checkLowerDiagonal = new Hashmap  // to track lower diagonal for Q
 *      
 *      backtrack(n, col, checkLeftRows, checkUpperDiagonal, checkLowerDiagonal, current, result)
 *      
 *      return result;
 * }
 * 
 * 
 * function backtrack(n, col, checkLeftRows, checkUpperDiagonal, checkLowerDiagonal, current, result){
 * 
 *      // Base Case 
 *      if(col == n){
 *          result.add(new Array(current))
 *          return
 *      }
 *  
 *      for(int row = 0; row < n; row++){
 *          
 *          if(checkLeftRows, checkUpperDiagonal, checkLowerDiagonal == true)
 *              continue
 * 
 *          current.add(generatedString(n, row))
 *          checkLeftRows.put(row, true)
 *          checkUpperDiagonal.put(row - col, true)
 *          checkLowerDiagonal.put(row + col, true)
 * 
 *          backtrack(n, col + 1, checkLeftRows, checkUpperDiagonal, checkLowerDiagonal, current, result)
 * 
 *          current.remove(current.length - 1)
 *          checkLeftRows.put(row, false)
 *          checkUpperDiagonal.put(row - col, false)
 *          checkLowerDiagonal.put(row + col, false)
 * 
 *      }
 * 
 * }
 * 
 * function generatedString(int n, int row){
 *      
 *      colStr = "." * n  // Create column of dots
 *      colStr[row] = "Q"   // Placed Q at spacifice row
 *      return colStr
 * 
 *      or 
 * 
 *      StringBuilder colStr = new StringBuilder(".".repeat(n));
 *      colStr.setCharAt(row, 'Q');
 *      return colStr.toString();
 * 
 * }
 * 
 * 
 * 
 */