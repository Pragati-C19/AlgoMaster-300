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
 * 5. let's dive into this
 * 
 * Pattern :
 * 
 * 1. Wrote exact same code as I wrote for search Word.. just added one for loop [word : words]
 * 1. Declare a result List to store String
 * 2. 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 * 
 */