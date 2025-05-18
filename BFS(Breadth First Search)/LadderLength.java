import java.util.*;

public class LadderLength {
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
     
        return 0;
    }

    public static void main(String[] args) {

        LadderLength solution = new LadderLength();

        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Result 1 -> " + solution.ladderLength(beginWord1, endWord1, wordList1) + "\n");

        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println("Result 2 -> " + solution.ladderLength(beginWord2, endWord2, wordList2) + "\n");
        
    }

}

/*
 * Intuitions :
 
    1. we have given a beginWord and endWord with the list of words
    2. end word of list of words is same as endWord
    3. return number of words in it's shortest transformation sequeunce 
    4. return 0 if u don't fid any sequence

 * Pattern :
 
    1. what to add in queue?
    2. Basic code will be :
        initially add beginWord in queue and mark it as visited
        while(!queue.isEmpty)
            get level size or queue size
            for(i = 0 to levelSize)
                popout top word
                if(popOutWord == endWord) -> return wordCount
                ^ now logic for how to get words to add in queue need to wrote here 
            wordCount++
        return 0

    3. logic for how to get words to add in queue
        if we start to trace for loop (j = 0 to wordList.length)
            diff = s[j] - popOutWord
                if(diff == 1 && !visited.contains(s[j])) 
                    queue.add(s[j])
                    visited.add(s[j])
                if(diff == 2)
                    break the for loop bcoz after that word kontach word nahiye jyachi length 1 asel

    4. Now just one thing is how to get difference of those strings?
        -> let's use a function called stringDifference(s1, s2)
            diff = 0
            for(i = 0 to s1.length)     // string length will be equal as per constraints endWord.length == wordList[i].length == beginWord.length
                if(s1[i] != s2[i])      // if char is not equal for both string count it as a difference
                    diff++
            return diff

        -> to get string difference there is also a one liner code
            - Count differing characters at the same position
                return sum(c1 != c2 for c1, c2 in zip(word1, word2))

            word1 = "hit"
            word2 = "hot"

                - zip gives [('h', 'h'), ('i', 'o'), ('t', 't')]
                - Comparisons: 'h' != 'h' → False (0)
                -              'i' != 'o' → True (1)
                -              't' != 't' → False (0)
                                   
            So sum = 1

    5. ohk there is only one problem I'm having so far with this code while tracing the it on paper
        - the wordCount
            je me lihilay tyani wordCount will eaqual to wordList.length
            me wordCount jr level size chya for loop nantr lavla tr maybe counting change hou shakte.. 
            code karu tevha baghu
 
 
 * Paseudo code :
 
    function ladderLength (beginWord, endWord, wordList) {
    
        -> Declare variables
            queue 
            visitedSet

        -> initially add string in queue and mark it as visited
            queue.add(beginWord)
            visitedSet.add(beginWord)

        -> start while loop
            while(!queue.isEmpty)

                - check length of queue which means the level size

                - start for loop ( i = 0 to queueSize)

                    - popTopString = queue.pop

                    - check if popString is equal to endWord 
                        if yes then return wordCount

                    - start for loop (str = 0 to wordList.length)

                        int stringDiff = call stringDifference (wordList[str], popTopString) function

                        if(stringDiff == 1 && !visitedSet.contains(wordList[str]))
                            - add that word in queue and mark it as visited
                        
                        if(stringDiff == 2)
                            break the for loop early to avoid TLE
                
                - increase wordCount

        -> return 0 if we don't find endWord
    
    }

    function stringDifference (str1, str2) {
    
        diff = 0
        
        -> length of both string will be same as per constraints
            for(i = 0 to str1.length)
                if(str1[i] != str2[i])
                    diff++
        
        return diff
            
    }

 
 */