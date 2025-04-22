import java.lang.reflect.Array;
import java.util.*;

public class WordDictionary {
    
    // Basic TrieNode Structure :
    private static class TrieNode {

        // Array is create of all alphabets with null as initial value
        TrieNode[] child = new TrieNode[26];

        // to check if word is ended at that letter
        boolean isEndOfWord = false;
    }

    // Globally Declare root
    TrieNode root;

    public WordDictionary() {
        
        // Assign the value for root
        root = new TrieNode();

        System.out.println(" Starting of Iteration...");

    }
    
    public void addWord(String word) {
        
        TrieNode node = root;
    
        for(char ch : word.toCharArray()){

            int indexOfChar = ch - 'a';
            
            // If there is no letterplaces at that index place it
            if (node.child[indexOfChar] == null) {
                node.child[indexOfChar] = new TrieNode();
                System.out.println("    -> New node created for letter: " + ch);
            }
           
            // Move to the next node
            node = node.child[indexOfChar];

        }

        node.isEndOfWord = true;

        System.out.println("   -> Marked end of word.\n");
        return;
    }
    
    public boolean search(String word) {

        // Use recursion
        return dfs(word, 0, root);
    }

    // Recursion Function : for wildcard Search
    private boolean dfs(String word, int index, TrieNode node){

        System.out.println("  ~ Visiting ( " + word + " , " + index + " )");
        if (index == word.length()) {
            System.out.println("    -> index (" + index + ") is similar to word's length (" + word.length() + ")");
            return node.isEndOfWord;
        }

        char ch = word.charAt(index);
        System.out.println("    Check Charecter at " + index + " : " + ch);

        if (ch == '.') {
            
            for(TrieNode kid : node.child){
                if (kid != null && dfs(word, index + 1, kid)) {
                    return true;
                }
            }

            return false;
        }
        else {
            
            int indexOfChar = ch - 'a';

            System.out.println("    Charecter at " + indexOfChar + " : " + (char) (indexOfChar + 'a'));

            return node.child[indexOfChar] != null && dfs(word, index + 1, node.child[indexOfChar]);
        }

    }

    public static void main (String[] args){
        
        WordDictionary solution = new WordDictionary();

        // First Example
        System.out.println("Final Result : ");

        System.out.println("  1st Iteration : Word Inserted");
        solution.addWord("bad");
       
        System.out.println("  2nd Iteration : Word Inserted");
        solution.addWord("dad");
       
        System.out.println("  3rd Iteration : Word Inserted");
        solution.addWord("mad");
         
        System.out.println("  4th Iteration : " + solution.search("pad") + "\n");  // Output: false
        System.out.println("  5th Iteration : " + solution.search("bad") + "\n");  // Output: true
        System.out.println("  6th Iteration : " + solution.search(".ad") + "\n");  // Output: true
        System.out.println("  7th Iteration : " + solution.search("b.."));  // Output: true
        
    }
}


/*
 * 
 * 
 * Intuitions :
 * 
 * 1. We need to create a word dictionary
 * 2. WordDictionary() Initializes the object.
 * 3. void addWord(word) Adds word to the data structure, it can be matched later.
 * 4. bool search(word) Returns true if there is any string that matches word
 * 5. Word may contain . where dot can be any letter
 * 6. will use dfs to search
 * 7. This pattern is called WildCard Search
 * 
 * Pattern :
 * 
 * 1. Create a struct for TrieNode with HashMap or Array
 * 2. We create a TrieNode class that contains:
 *      - An array of size 26 for children nodes (a to z)
 *      - A boolean flag isEndOfWord to mark the end of a word
 * 3. Why?
 *      - Arrays give O(1) access time since we map characters using ch - 'a'
 *      - It’s faster and more memory predictable than HashMap
 * 3. WordDrictonary() -> assign value to globally declared root 
 * 4. void addWord(word) -> use inseartion code here..
 *      - declare a node to track starting point of word
 *      - Loop through each character in the word.
 *      - Convert character to index using: int idx = ch - 'a'
 *      - If node.children[idx] is null → create a new TrieNode
 *      - else move node to the next node
 *      - After loop, mark isEndOfWord = true
 * 5. bool search(word) -> it's same as we did before just new thing is . 
 *      - We use a recursive dfs() function to handle . wildcard.
 *      - If current index equals word length → return if current node marks as endOfWord.
 *      - If current character is . → try all 26 children.
 *      - Otherwise → follow the path at the specific character index.
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * TrieNode {
 *      TrieNode[] child = new TrieNode[26]
 *      bool isEndOfWord = false
 * }
 * 
 * // Globally declare root to track starting points
 * TrieNode root
 * 
 * WordDictionary(){
 *      - Assign value to root
 * }
 * 
 * void addWord(word){
 *      node = root
 * 
 *      for(char ch : word){
 *          
 *          idexOfChar = ch - 'a'
 * 
 *          if(node.child[idexOfChar] == null) -> add ch to the array
 *          
 *          // Move node to next
 *          node = node.child[idexOfChar] 
 *      }
 *      
 *      isEndOfWord = true
 *      
 * }
 * 
 * 
 * bool search(word) {
 *      dfs(word, 0, root)
 * }
 * 
 * bool dfs(word, index, node){
 * 
 *      if(index == word.length) return node.isEndOfWord
 *      
 *      // take charecter at that index
 *      ch = word.charAt(index)
 * 
 *      if(ch == '.') {
 *          
 *          // Check all alphabets till we get next letter same as we are searching
 *          for(TrieNode child : node.child) {
 *              return child != null && dfs(word, index + 1, child)
 *          }
 * 
 *          return false
 *      }
 *      else {
 *          idexOfChar = ch - 'a';
 *          
 *          return child != null && dfs(word, index + 1, node.child[idexOfChar])
 *      }
 * }
 * 
 */