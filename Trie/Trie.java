import java.util.*;

public class Trie {
    
   
    
}


/*
 * 
 * 
 * Intutions :
 * 
 * 1. Trie is pronouced as try it's also called prefix tree 
 * - it is a tree data structure used to store and retrive keys in dataset of strings
 * 
 * 2. Conditions :
 * - Trie() -> Initialize trie object
 * - void insert(String word) -> Inserts the string word into the trie.
 * - boolean search(String word) -> Returns true if the string word is in the trie, otherwise false
 * - boolean startsWith(String prefix) -> return true if any inserted words prefix is same as this
 * 
 * Pattern :
 * 
 * 1. Will use TrieNode -> as it wants us to insearch and check prefix of string
 * 2. Will first create a TrieNode class 
 *      - Map<Character, TrieNode> children: The next possible letters (branches of the tree).
 *      - boolean isEndOfWord: A flag marking the end of a word.
 * 3. Globally Declare a root node will store letter of words in that root one by one
 * 4. Trie() -
 *      - assign value to the root
 * 5. inseart(string)
 *      - check if the letter already exist ? if no then add new TrieNode
 *      - if yes then continue the for loop
 *      - after insearting full word change isEndOfWord value to true as word ended
 * 6. search(string)
 *      - check in for loop check if that letter is in hashmap or not
 *      - if no then return false
 *      - if yes then continue the for loop
 *      - After completing for loop check is isEndOfWord ? true if yes
 * 7. startWith(prefix) 
 *      - it has same logic as search 
 *      - just we don't need to check isEndOfWord here..
 *      - as we are checking prefix it is possible that prefix is shorter that the word
 * 8. root - The one fixed entry point to the whole Trie. All words connect to it.
 * 
 * Pseudo Code :
 * 
 * 
 * TrieNode {
 *      child = hashmap
 *      isWordEnded = false
 * }
 * 
 * // Globally Declare root : to store the starting point of tree
 * TrieNode root
 * 
 * Trie(){
 *      root = new TrieNode
 * }
 * 
 * inseart(word){
 *  
 *      // Declaring starting point
 *      node = root
 * 
 *      for(ch : word){
 *          if(!node.child.containsKey(ch)) -> add ch : new TrieNode in hashmap
 *          
 *          // Move to next charecter
 *          node = node.child.get(ch)
 *      }
 * 
 *      isWordEnded = true
 * }
 * 
 * search(word) {
 * 
 *      node = root
 *      
 *      for(ch : word){
 *          if(!node.child.containKey(ch)) -> return false
 *          
 *          // Move to next charecter
 *          node = node.child.get(ch)
 *      }
 * 
 *      return node.isWordEnded
 * }
 * 
 * startWith(prefix) {
 * 
 *      node = root
 *      
 *      for(ch : word){
 *          if(!node.child.containKey(ch)) -> return false
 *          
 *          // Move to next charecter
 *          node = node.child.get(ch)
 *      }
 * 
 *      return true
 * 
 * }
 * 
 */
