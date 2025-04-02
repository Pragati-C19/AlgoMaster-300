import java.util.*;

public class Partition {
    
}


/*
 * 
 * Things I learned :
 * 1. Java has a substring function from which u can directly get substring
 * 2. Java has reversed function which can reversed any string and store it as a new string
 * 3. It can also easily check is the string is equal to any other string 
 * 
 * Intuitions : 
 * 
 * 1. Need to find partitions as every partitions must be palindrome
 * 2. so we need to check if it's palindrome
 * 3. if yes then add that array to result
 * 4. also for getting palindrom use different function with for loop or if u think it's complexity is high then use reverse() function
 * 5. and to get substring java also have a function use it directly.
 * 
 * Pattern : 
 * 
 * 1. Base Case : if(start == s.length()) return substring
 * 2. for loop will start from i = start to s.length
 * 3. will check for substring : s.substring(start : end) where end = start + 1 = i + 1
    ^ Java's substring(start, end) includes start but excludes end.
 * 
 * Pseudo Code :
 * 
 * function partitions(string s){
 * 
 *      result = new array
 *      current = new array
 *      
 *      backtrack(s, 0, current, result)
 * 
 *      return result
 * }
 * 
 * 
 * function backtrack(s, start, current, result){
 *      
 *      // Base Case
 *      if(start == s.length){
 *          result.add(new array(current))
 *          return;
 *      }
 * 
 *      for(int i = start; i < s.length; i++){
 *          
 *          // Java's substring(start, end) includes start but excludes end.
 *          String substring = s.substring(start : i + 1)
 * 
 *          if(isPalindrome(substring)){
 *              current.add(substring)
 *              backtrack(s, start + 1, current, result)
 *              current.remove(current.length - 1)
 *          }
 *          
 *      }
 * 
 * }
 * 
 * 
 * function isPalindrome(str){
 *      n = str.length
 *      for(int i =0; i < n; i++){
 *          if(str.charAt(i) != str.charAt((n-1) - i)){
 *              return false;
 *          }
 *      }
 *      return true;
 * 
 *      //? Different approach is
 *      
 *      String reversed = new StringBuilder(str).reverse().toString()
 *      if(str.equal(reversed)){
 *          return true;
 *      }
 *      else{
 *          return false;
 *      }
 * }
 * 
 * 
 */