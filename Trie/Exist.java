import java.util.*;

public class Exist {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. This que is word search problem
 * 2. it gives a board with a word
 * 3. need to check if that word exist in the board
 * 4. you can check horrizontally (left, right) and vertically (up, down)
 * 5. Basic thing is I need to use recursion function, hashmap to check if that block is visited or not
 * 
 * 
 * Pattern :
 * 
 * 1. first thing first get values of m and n
 * 2. Declare a boolean visited to check if the block is visited or not
 * 4. Declare a result to store boolean value of final result
 * 3. start 2 for loops (i = 0 < m) and (j = 0 < n)
 *      - call recursion function 
 *      - will pass i, j, start = 0, board, visited, word, result  -> start is used to traverse charecter of word  
 * 4. Recursion call
 *      - let's think abt when will have to return result
 *          if (start == word.length()) -> means we have check full word so now return result = true
 *      - Base Case : 
 *          If block is visited we need to skip it 
 *          If !block doesn't have that charecter then we need to skip and move to next
 *      - otherwise will mark this block [i,j] as visited for that word
 *      - Call recursions for other side.. left, right, up , down
 * 
 *                         [i - 1 , j]
 *  
 *                              |            
 *        [i, j - 1]       _____|_____           [i, j + 1]        
 *                              |                                    
 *                              |
 * 
 *                         [i + 1 , j]
 * 
 *      - And at the end backtrack that visited block if we need to check it again for other letters
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * function exist(board, word){
 * 
 *      m = row,
 *      n = col
 *      
 *      boolean[][] visited = false
 *      boolean result = false
 *      int start = 0
 * 
 *      for(i = 0 < m){
 * 
 *          for(j = 0 < n){
 *                  
 *              dfs(i, j, start, board, word, visited, result)
 *          }
 *      }
 *      
 *      return result
 * }
 * 
 * 
 * fnction dfs(i, j, start, board, word, visited, result){
 * 
 *      // Base Case :
 *      if(board[i][j] != word) -> return
 *      if(visted[i][j] = true) -> return
 * 
 *      // check if word is completed or not..
 *      if(start == word.length) -> result = true
 * 
 *      // OtherWise
 *      visited[i][j] = true
 * 
 *      // Check recursion for other sides
 *      dfs(i+1, j, start+1, board, word, visited, result)
 *      same for other directions
 * 
 *      // backtrack the visiting block
 * 
 * }
 * 
 * 
 */