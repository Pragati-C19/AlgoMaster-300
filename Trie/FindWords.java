import java.util.*;

public class FindWords {
    
    List<String> result;
    Set<String> foundWords;

    public List<String> findWords(char[][] board, String[] words){
        
        int m = board.length;
        int n = board[0].length;

        boolean[][] visitedBlock = new boolean[m][n];

        result = new ArrayList<>();
        foundWords = new HashSet<>();

        System.out.println("Starting Recurssion...");

        // Added for loops for checking first char
        for (String word : words) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(i, j, 0, board, word, visitedBlock, m, n);
                }
            }
        }
        
        return result;
    }

    // Helper Function  : it's a dfs function used for checking col and row recurssively
    public void dfs(int i , int j, int start, char[][] board, String word, boolean[][] visitedBlock, int m, int n){

        // Check if word it complete or not 
        if (start == word.length()) {
            if (!foundWords.contains(word)) {
                result.add(word);
                foundWords.add(word);
                System.out.println("      ~ Word Found: " + word + " Added in HashSet : " + foundWords);
            }
            return;
        }
        
        // Base Cases :
        if (i < 0 || i >= m || j < 0 || j >= n) {
            System.out.println("        It's an end of row or col...");
            return;    
        }

        System.out.println("    -> Visiting ( " + board[i][j] + " , " + word.charAt(start) + " , " + visitedBlock[i][j] + " , " + result + " )");

        if (visitedBlock[i][j] == true) {
            System.out.println("        Block is already visitedBlock"); 
            return;
         }

        if (board[i][j] != word.charAt(start)){
            System.out.println("        -> Word is not at [" + i + " , " + j + "]");
            return;
        }


        System.out.println("        -> Char found at [" + i + " , " + j + " ]");

        visitedBlock[i][j] = true;

        // Checking other sides
        dfs(i+1, j, start+1, board, word, visitedBlock, m, n);
        
        dfs(i, j+1, start+1, board, word, visitedBlock, m, n);
        
        dfs(i-1, j, start+1, board, word, visitedBlock, m, n);
        
        dfs(i, j-1, start+1, board, word, visitedBlock, m, n);
        
        // backtrack 
        visitedBlock[i][j] = false;
    }


    public static void main(String[] args){

        FindWords solution = new FindWords();

        char[][] board1 = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };               
        
        String[] word1 = {"oath","pea","eat","rain"};
        System.out.println("-> Result 1 : " + solution.findWords(board1, word1) + "\n");


        char[][] board2 = {
            {'a','b'},
            {'c','d'}
        };

        String[] word2 = {"abcb"};
        System.out.println("-> Result 2 : " + solution.findWords(board2, word2) + "\n");

        // String word3 = "ABCB";
        // System.out.println("-> Result 3 : " + solution.exist(board, word3) + "\n");

    }

}


/*
 * 
 * 
 * Intutions :
 * 
 * 1. Will trverse from column,
 * 2. will check if the charecter of word from words array is found at first column?
 * - If yes then check for next charecter of word is present near that block.. (check left, right, up and down)
 *      - if it's there then again check next charecter of that word is present near that block
 * 3. Jr board vr same letters astil and suppose u are checking for eat and tula e bhetla but a tyachya aspass nahiye tr look for another e
 * 4. Let's check exist (Word Search) Problem first then will solve this one.. 
 * 
 * 
 * Pattern :
 * 
 * 1. Using Trie with DFS
 * 2. will create a Trie struct first which will store child and word at the end 
 * 3. wordStore will work just like isEndOfWord bool.. just now we are storing the whole word at that node
 * 4. after insearting all words in trie
 * 5. will traverse matrix as we did in word search que
 * 6. will call dfs in it 
 * 7. our base case to get result will be checking (word != null) or (isEndOfWord = true) 
 * 8. why this?... bcoz for example u are storing oath in trie
 *         o
 *         ├─ a
 *         │  ├─ t ─ h 
 *    
 *    only at h word is stored as oath and isEndOfWord will be true 
 *    other char it will be null and false
 * 9. after checking word != null if yes then add that word in result and change wordStore at that node as null
 * so that u'll not get duplicates (u can use set too to check if block is visited or not)
 * 10. if that charecter at block is in current TrieNode -> then check other sides
 * 
 * 
 * 
 * Appraoch Using Trie :-
 * 
 * 1. Create TrieNode Struct :
 *      - String wordStored -> store complete word here when it's the end
 *      - Child -> It checks if there are any child or not
 *      - isEndOfWord -> checks if any word is ending at this char?
 * 2. Globally declare root -> initial stage of any TrieNode
 * 3. Declare a result List to store String
 * 4. focus on insearting all words in the Trie
 *      -> for(word : words) -> inseart(word)
 *          Traverse through each charecter of that word
 *          create a node if missing
 *          at the end make isEndOfWord = true and wordStored = word
 * 5. Start traversing matrix use for loop here
 *      -> call dfs recursion
 *          Base Cases : 0 > i, j >= board.length || visitedBlock -> return  
 *          If isEndOfWord = true and wordStored != null -> add that to result and wordStored = null return
 *          check if char at board[i][j] is present in TrieNode? -> if yes then check (down, right, up, left) 
 * 6. return result
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * TrieNode {
 *      child = TrieNode[26]
 *      wordStored = null
 *      isEndOfWord = false
 * }
 * 
 * Globally declare root and result
 * 
 * function findWords(board, words){
 * 
 *      -> get length of row and col (m x n)
 * 
 *      -> assign value to root and result
 * 
 *      -> Visited -> to check if block is visited or not
 * 
 *      -> for(word : words) -> call function insert(word)
 * 
 *      -> for(i = 0 < m) inside for( j = 0 < n)
 *          call recursion function dfs(i, j, root, visited, board, m, n)
 * 
 *      -> return result
 *      
 * }
 * 
 * 
 * function insert(word){
 * 
 *      -> node = root;
 *      
 *      -> for(ch : word)
 *              index = ch - 'a'
 *              if(node.child[index] == null){
 *                      node.child[index] = new TrieNode()
 *              }
 *              node = node.child[index]
 *      
 *      -> update node.word and isEndOfWord
 *              node.word = word
 *              node.isEndOfWord = true
 * 
 * }
 * 
 * 
 * function dfs( i, j, node, visited, board, m, n){
 *  
 *      -> Base Case :
 *      if(0 > i, j >= m , n || visited) -> return 
 * 
 *      -> Get result
 *      if(word != null) 
 *          result.add(word) 
 *          word = null
 * 
 *      -> Mark this block as isited
 *          visited = true
 *  
 *      -> Checking if charecter at bloack exist in Trie
 *      
 *      ch = board[i][j]
 *      indexOfChar = ch - 'a'
 *      
 *      if(node.child[indexOfChar] != null){
 *          dfs(i+1, j, node.child[indexOfChar], visited, board, m, n) -> down
 *          dfs(i, j+1, node.child[indexOfChar], visited, board, m, n) -> right
 *          dfs(i-1, j, node.child[indexOfChar], visited, board, m, n) -> up
 *          dfs(i, j-1, node.child[indexOfChar], visited, board, m, n) -> left
 *      }
 * 
 *      visited = false
 *      return
 * }
 * 
 * 
 * 
 */