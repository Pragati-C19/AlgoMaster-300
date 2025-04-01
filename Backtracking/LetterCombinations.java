import java.util.*;

public class LetterCombinations {
    
    // Function to find all combinations of a phone number
    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<>();
        String current = "";
        int n = digits.length();
        
        //? Writing a HashMap : In java need to use .put to write all values in map. No other way   
        HashMap<Character, String> telephoneMap = new HashMap<>();

        telephoneMap.put('2', "abc");
        telephoneMap.put('3', "def");
        telephoneMap.put('4', "ghi");
        telephoneMap.put('5', "jkl");
        telephoneMap.put('6', "mno");
        telephoneMap.put('7', "pqrs");
        telephoneMap.put('8', "tuv");
        telephoneMap.put('9', "wxyz");

        // Base Case : If String is null
        if (digits == null || n == 0) {
            return result;
        }

        // telephoneMap.forEach((key, value) -> System.out.println(key + " -> " + value)); 
        
        // System.out.println("Letters At specific Index : " + telephoneMap.get(digits.charAt(0)));
        
        backtrack(digits, n, telephoneMap, current, result, 0);

        return result;

    }

    // Helper recursive function
    private void backtrack(String digits, int n, HashMap<Character, String> telephoneMap, String current, List<String> result, int index){

        // Base Case 
        if (current.length() == n) {
            result.add(current);
            return;
        }
        
        // Strings in Java are not directly iterable, .toCharArray() converts the string to a char[] array, which is iterable.
        
        String lettersAtIndex = telephoneMap.get(digits.charAt(index));
        
        for(char letter : lettersAtIndex.toCharArray()){
            backtrack(digits, n, telephoneMap, current + letter, result, index + 1);
        }
    }

    public static void main(String[] args){
        LetterCombinations solution = new LetterCombinations();

        String digits1 = "23";
        System.out.println("Output1 : " + solution.letterCombinations(digits1) + "\n");
        
        String digits2 = "";
        System.out.println("Output2 : " + solution.letterCombinations(digits2) + "\n");

        String digits3 = "2";
        System.out.println("Output2 : " + solution.letterCombinations(digits3) + "\n");

    }

}

/**
 * 
 * Took help to create Hashmap and for few syntax like converting string to char[] array
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
