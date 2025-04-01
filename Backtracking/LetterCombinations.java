import java.util.*;

public class LetterCombinations {
    
}

/**
 * 
 * Intuitions : 
 * 
 * 1. The string contains digits from 2-9
 * 2. Each digit is mapped to 3 letters just like on the telephone buttons
 * 3. We need to find all possible combinations made by the digits given to us 
 * 
 * Pattern : 
 * 
 * 1. First we need to map letters fro specific number
 * 2. We will use a string to store the current combination
 * 3. Then will use recursion function backtrack to find combinations:
 * - Base Case : if(current.length == digits.length) add current in result
 * - for loop : will be for letter of that specific digits[start]
 * - and u'll get letters of that gigit from map.. so it will be map[digits[start]]
 * - as this is string so we can't use current.add(letter) now.. will directly pass it in function by current + letter
 * - current.remove(current.length - 1) I'm not sure of it yet.. 
 *  
 * Pseudo Code :
 * 
 * function letterCombinations(String digits){
 * 
 *      List<String> result = new Array
 *      String current = ""
 *      int n = digits.length;
 *     
 *      Map = { 2: "abc" , ... , 9: "wxyz" }
 *      
 *      backtrack(digits, n, map, current, result, 0)
 * 
 *      return result;
 * }
 * 
 * function backtrack(String digits, int n, map, String current, List<String> result, int start){
 * 
 *      Base case :
 *      if(current.length == n) {
 *          result.add(current)
 *          return;
 *      }
 *      
 *      for(int letter : map[digits[start]]){
 *          
 *          backtrack(digits, n, map, current + letter, result, start + 1)
 *  
 *      }
 * }
 * 
 * 
 */
