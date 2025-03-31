import java.util.*;

public class GenerateParenthesis {

    // Function to generate Parenthesis
    public List<String> generateParenthesis(int n) {
        
        List<String> result = new ArrayList<>();
        
        System.out.println("Starting Backtracking...");
        backtrack(result, 0, 0, n, "");

        System.out.println("Final Result: " + result);
        return result;
    }

    // Helper function to backtrack and create strings to ad in result array recursively 
    private void backtrack(List<String> result, int openCount, int closeCount, int max, String current){

        System.out.println("[backtrack] Current: '" + current + "' | Open: " + openCount + " | Close: " + closeCount);

        // open = close = n
        if (current.length() == max * 2){
            result.add(current);
            System.out.println("[backtrack] Added to result: '" + current + "'");
            return;
        }
        else if (openCount < max) {
            // Adding "(" in String current and increasing the open count
            System.out.println("[backtrack : IF] Current : '" + current + "('");
            backtrack(result, openCount + 1, closeCount, max, current + "(");
        }
        else if (closeCount < openCount) {
            // Adding ")" in String current and increasing the close count
            System.out.println("[backtrack : ELSE] Current : '" + current + ")'");
            backtrack(result, openCount, closeCount + 1, max, current + ")");
        }

    }

    public static void main(String[] args){
        GenerateParenthesis solution = new GenerateParenthesis();

        int n1 = 3;
        List<String> output1 = solution.generateParenthesis(n1);
        System.out.println("Output1 : " + output1 + "\n");

        int n2 = 1;
        List<String> output2 = solution.generateParenthesis(n2);
        System.out.println("Output2 : " + output2);

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. The problem is asking us to generate all possible combinations of
 * well-formed parentheses.
 * 2. We can use a backtracking approach to solve this problem.
 * 3. We have given n and remeber that we have n numbers of open brackets and n
 * numbers of close brackets
 * 4. At start it's compulsory to add open bracket
 * 5. At the end it's compulsory to add close bracket
 * 6. We can add up to n open brackets but when it's time for clossing bracket
 * check if their count is less than open ones
 * - open < n - add " ( " bracket open++
 * - open > close - add " ) " bracket close++
 * - open == close == n - return the array
 * 7. will use recursion
 * 
 * 
 * 
 * 
 * Pattern :
 * 
 * 1. Backtracking = Brute Force + Pruning
 * 2. Identify problems where choices lead to valid solutions.
 * 3. Follow the pattern:
 * - Make a choice ✅
 * - Recurse 🔄
 * - Undo the choice (backtrack) ❌
 * 4. Always use base cases to stop unnecessary recursion.
 * 
 * Pseudo Code:
 * 
 * function generateParenthesis(int n){
 * 
 *      result  = new Array
 *      
 *      backtracking(result, 0, 0, "", n);
 *      
 *      return result;
 * }
 * 
 * function backtracking(int[] result, int openCount, int CloseCount, String current, int n){
 *      
 *      // open == close == n
 *      if(current.length == n * 2){
 *          result.add(current)
 *          return;
 *      }
 *      else if(open < n){
 *          backtracking(result, openCount + 1, closeCount, current + "(", n);
 *      }
 *      else if(close < open){
 *          backtracking(result, openCount, closeCount + 1, current + ")", n);
 *      }
 *      
 * } 
 * 
 */
