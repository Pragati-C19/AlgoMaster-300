import java.util.*;

public class MinDistance {
    
    public int minDistance(String word1, String word2) {
        
        // Declare variables
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m][n];     // Dp will store min value we get if we did operation
        int operationsCount = 0;        // it will store operation count till end


        // let's check each letter of both words
        // I can't skip 0 bcoz I need it to comapre 
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                int char1 = word1.charAt(i);
                int char2 = word2.charAt(j);

                // if i is out of bound
                if (i == m-1) {

                    // Number of insertions = length of remaining target = word2.length - j
                    operationsCount = n - j;
                    System.out.println("    We have reach to the end of word1 so inserting all remaining letters from word2 : " + operationsCount);

                    // as we have reach to the end we need to return count
                    return operationsCount;
                }

                // if j is out of bound
                else if (j == n-1) {
                    
                    // Number of deletions = length of remaining target = word1.length - i
                    operationsCount = m - i;
                    System.out.println("    We have reach to the end of word2 so deleting all remaining letters from word1 : " + operationsCount);

                    // as we have reach to the end we need to return count
                    return operationsCount;
                }

                // if both letters are same will skip
                if (char1 == char2) {
                    
                    System.out.println("    - Both are same words so skipping" + Arrays.deepToString(dp));
                    continue;
                }

            

            }
        }
        


        return 0;
    }

    public static void main (String[] args) {

        MinDistance solution = new MinDistance();

        String word11 = "horse";
        String word21 = "ros";
        System.out.println("Result1 -> " + solution.minDistance(word11, word21) + "\n");    // 3

        String word12 = "intention";
        String word22 = "execution";
        System.out.println("Result2 -> " + solution.minDistance(word12, word22) + "\n");    // 5

    }
}


/*
 * Intuitions :
 
    1. We have given two words
    2. return the min number of operation required to convert word1 to word2
    3. Operation :
        - insert one charecter
        - delete one charecter
        - replace one charecter
 
 
 * Pattern :
 
    ^ Trace Example :

        word1 : horse ,  word2 : ros 

        - we can replace h with r word1 will be
            rorse

        - then will check o 
            after r there is o in word2 so skip

        - then check r
            in word2 there is s at third place so there we can do 2 things
            1. replace r with s then word1 will be rosse
            2. delete r from word1 then it will be rose

        - if we follow first operation 
            we need to do 2 more operations to remove s and e at end

        - if we follow second operation 
            we need to do 1 more operation to remove e

        - so we want minimum number of operations will take the second path
 

    Approach :

        1. apan word1 == word2 karnyasathi 3 main operations karu shakto nehmi
            - direct insert karat raha
                horse needs to convert to ros na 
                    - h and r are differet so -> insert r in word1 : rhorse
                    - h and o are differet so -> insert o in word1 : rohorse
                    - h and s are differet so -> insert s in word1 : roshorse

            - direct replace karat raha
                horse needs to convert to ros na 
                    - h and r are differet so -> replace h in word1 : rorse
                    - o and o are same so -> no need to replace o in word1 : rorse
                    - r and s are differet so -> replace r in word1 : rosse

            - delete tr apan fact end lach use karu shakto maybe.. to delete extra letters
                roshorse 
                    - delete h in word1 : rosorse
                    - delete o in word1 : rosrse
                    - delete r in word1 : rosse

                rosse
                    - delete s in word1 : rose
                    - delete e in word1 : ros

        2. so bagh doing 
            insertion + deletion gives -> 8 operations
            replaced + deletion gives -> 5 operations

            using insertion + replaced + deletion gives -> 3 operations
                - h and r are differet so   -> replace h in word1 : rorse
                - o and o are same so       -> no need to replace o in word1 : rorse
                - r and s are differet so   -> delete r in word1 : rose
                    but we have s next to r in word1 
                    so delete r
                - s and s are same so       -> no need to replace s in word1 : rose
                - at end we don't need e    -> delete e in word1 : ros

        3. Will use i for word1 and j for word2
            start for loops for both words
                for(i = 0 to m)
                    for(j = 0 to n)

            why ?
                                horse,  ros

                                i = 0, j = 0
                                    (h,r)
                     _________________|_________________
                    |                 |                 | 
                insert r      replace h with r       delete h 
                rhorse              rorse              orse

                (i=0, j=1)        (i=1, j=1)        (i=1, j=0) 
                  (h,o)             (o,o)             (o,r)

                 (i, j+1)         (i+1, j+1)         (i+1, j)


            kay hotay te nit samja ata..
                - apan donhi words ch letter by letter check karnar ahe right? so we need an index to check it
                - samaj apan index i ghetla to check horse and j ghetla to check ros
                - me index i vrchya letter vr 3 operations lavel 
                    insert lavla ki me j+1 karel ka?
                        karan me h chya agodr r lihun ghetlay ata mala word2 madhla next letter sathi shidhaychy
                    replace lavla ki me i+1 and j+1 karel ka?
                        karan me h la replace kartey r ni mhnje mala word2 cha ek fix letter bhetla..
                        ata mala next letter check karava lagel for next letter of word1
                        ata apan direct word1 replce thodi karat basnar ahe?
                        apan fact index pudhe pudhe gheu like mark as found kelya sarkh
                    delete lavla ki me i+1 karel
                        apan delete lavtoy karan me mhntey ohk h = r nahiye so me kay karte delete karte
                        till mala r = r bhetat nahi 
                        to bhetla ki me word2 cha next letter check karel

                - ata apan he 3 gosti check karnar tr pratek index sathi mala different counts bhettil
                - I need min so me kay karte yach tinhi cha min ghet jail
                    
                    minOfOperations = min ( dp[i][j+1], dp[i+1][j+1], dp[i+1][j])

                    dp[i][j] = 1 + minOfOperation

        4. Issues in my thinking
            - me for loop lihite tr ahe but i+1 and j+1 kela tr out of Bound hoil
                same i-1 and j-1 kel tr zero pekshya kami nahi gheta yenar
                so for loop will start at i = 1 and j = 1

            - for i out of bound thing 
                maza i+1 tevhach out of bound hoil jevha i = m hoil ani mazyakde ajun ahet word2 madhe letter right
                    so tasa asel tr me kay karte je pn letters rahilele astil word2 madhle te insert karel

            - for j out of bound thing 
                maza j+1 tevhach out of bound hoil jevha j = n hoil ani mazyakde ajun ahet word1 madhe letter right
                    so tasa asel tr me kay karte je pn letters rahilele astil word1 madhle te delete karel

 
 * Pseudo Code :

    1. Used int[][] dp
        but checking as top-bottom not bottom-up so getting wrong ans

    public int minDistance(String word1, String word2) {
        
        // Declare variables
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m][n];     // Dp will store min value we get if we did operation
        int operationsCount = 0;        // it will store operation count till end


        // let's check each letter of both words
        // I can't skip 0 bcoz I need it to comapre 
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                int char1 = word1.charAt(i);
                int char2 = word2.charAt(j);

                // if i is out of bound
                if (i == m-1) {

                    // Number of insertions = length of remaining target = word2.length - j
                    operationsCount = n - j;
                    System.out.println("    We have reach to the end of word1 so inserting all remaining letters from word2 : " + operationsCount);

                    // as we have reach to the end we need to return count
                    return operationsCount;
                }

                // if j is out of bound
                else if (j == n-1) {
                    
                    // Number of deletions = length of remaining target = word1.length - i
                    operationsCount = m - i;
                    System.out.println("    We have reach to the end of word2 so deleting all remaining letters from word1 : " + operationsCount);

                    // as we have reach to the end we need to return count
                    return operationsCount;
                }

                // if both letters are same will skip
                if (char1 == char2) {
                    
                    System.out.println("    - Both are same words so skipping" + Arrays.deepToString(dp));
                    continue;
                }

            

            }
        }
        


        return 0;
    }



 */