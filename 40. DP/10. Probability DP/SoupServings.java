import java.util.*;

public class SoupServings {
    
    // Globally Declare variables
    int[][] operations = {
        {100, 0},
        {75, 25},
        {50, 50},
        {25, 75}
    };
    double operationProbability = 0.25;     // given in que

    // Driver Function 
    public double soupServings(int n) {
        
        double totalProbability = probabilityWhen(n, n);

        return totalProbability;
    }

    // Recursion Function : to get probability when (A, B) are
    private double probabilityWhen(int A, int B) {

        System.out.println(" Visiting A and B as : (" + A + ", " + B + ")");
        
        // Let's check event base cases 
        if (A <= 0 && B <= 0) {
            
            // Event2 : soup A and B both become empty at same time (A <= 0 && B <= 0)
            System.out.println("\tEvent 2 so return 0.5 ");

            return 0.5;
        }
        else if (A <= 0) {
            
            // Event1 : soup A becomes empty first (A <= 0)
            System.out.println("\tEvent 1 so return 1 ");

            return 1;
        }
        else if (B <= 0) {
            
            // Event3 : soup B becomes empty first (B <= 0)
            System.out.println("\tEvent 3 so return 0 ");

            return 0;
        }

        
        // declare a currProbability variable
        double currProbability = 0;

        // let's do all operations on curr quanitiy of soup A and B
        for (int[] operation : operations) {
            
            // pratek operations chya veles serving kiti havyat tyachi value ghe
            int serveA = operation[0];
            int serveB = operation[1];

            // let's substract serveA from A and serveB from B
            // and call recursion for it 
            // add that recursions value in currProbability

            currProbability += probabilityWhen(A - serveA, B - serveB);

            System.out.println("\t    - currProb (" + currProbability + ") = probabiltiyWhen(" + (A - serveA) + ", " + (B - serveB) + ")");
        }


        // get total Probability so far
        double totalProbability = currProbability * operationProbability;
        System.out.println("\tTotalProb (" + totalProbability + ") = currProb (" + currProbability + ") * (" + operationProbability + ")");

        return totalProbability;
    }

    public static void main(String[] args) {

        SoupServings solution = new SoupServings();

        System.out.println(" Result 1 -> " + solution.soupServings(50) + "\n");       // 0.625000
        System.out.println(" Result 2 -> " + solution.soupServings(100) + "\n" );    // 0.71875
        // System.out.println(" Result 3 -> " + solution.soupServings(824883294) + "\n" );    // Getting TLE for this

    }

}

/*
 * Intuitions :
 
    1. we have two types of soup: type A and type B
    2. we have n ml of each type of soup
    3. Operations :
        - Serve 100 ml of soup A and  0 ml of soup B
        - Serve  75 ml of soup A and 25 ml of soup B
        - Serve  50 ml of soup A and 50 ml of soup B
        - Serve  25 ml of soup A and 75 ml of soup B
    4. for each turn, You randomly pick one of 4 serving options, each with equal chance (0.25)
    5. If there's not enough soup, serve as much as you can 
        - example, if A has 30ml left and you try to serve 50ml, you serve just 30ml
    6. will stop serving when either or both soup is empty or negative 
    7. need to return probability that
        - A becomes empty before B
        - Or Both A and B becomes empty at the same time
        - Probability that A becomes empty first + 1/2 × Probability that A and B become empty at the same time


 
 * Pattern :
 
    1. we need an array or variable which will tell us how much ml soup is remain on soup A and soup B
    2. at any state(a, b) = remaining ml of soup A and B
        - we try all 4 possible servings from that point
        - each path contributes to the final probability
        - will return probabilty 

    3. How our states are when will serve ?

        | Option | A Served | B Served | New State      |
        | ------ | -------- | -------- | -------------- |
        |   1    |    100   |    0     |  (a-100, b)    |
        |   2    |    75    |    25    |  (a-75, b-25)  |
        |   3    |    50    |    50    |  (a-50, b-50)  |
        |   4    |    25    |    75    |  (a-25, b-75)  |

    4. Base Cases : when to stop and what to return 
        - If A ≤ 0 and B ≤ 0 -> return 0.5 (both empty at same time)
        - If A ≤ 0 and B > 0 -> return 1.0 (A becomes empty first)
        - If A > 0 and B ≤ 0 ->  return 0.0 (B becomes empty first)

    5. u may ask why 0.25, 0.5, 1.0 where this number came from?
        
        -> why 0.25 as equal probability of all 4 operations 
            - All four operations are equally likely
            - That means the probability of picking any one operation is 1/4 = 0.25

        -> why 0.5 if both A and B are empty
            -  we are spliting the credit in half


    6. check out this video
        https://www.youtube.com/watch?v=BiA4x5Gj7io&ab_channel=codestorywithMIK
 
    7. as per video I understood few things
        - all operations will give same probability which is 0.25
        - mala dilay que madhe mala return kay karaychy?
            P(Event1) + 1/2 of P(Event2)

            where, 
                Event1 : soup A becomes empty first (A <= 0)
                Event2 : soup A and B both become empty at same time (A <= 0 && B <= 0)

        - Event1 will return 1 karan it's 100% possible 
        - Event2 will return 1 as well  
            but ques says half of P(Event2) so instead of returning 1 will directly return
                1/2 of (1) = 0.5
        - at the end there will be a Event 3 too
            if any of the Event1 and Event 2 happens will check this Event 3
            Event3 : soup B becomes empty first (B <= 0)
            will return 0 at that time
            karan mala tevhachi probability pahijech nahi 

    ^ Dry Run

        n = 50 

        - n = 50 means A = 50 and B = 50
        - Operation 1 : Need(100, 0)
            ata pahil tr A nahiye itke 100ml so as per que apan jitka ahe titka serve karu shakto
            so A - 100 = 50 - 100 = -50 
            which is <= 0, hence return 1

            P1 = Operation(A - 100, B) = 1

        - Operation 2 : Need(75, 25)
            A doesn't have 75ml so will use all of it
            A - 75 = 50 - 75 = -25
            B - 25 = 50 - 25 = 25

            A <= 0 && B > 0    so return 1

            P2 = Operation(A - 75, B - 25) = 1

        - Operation 3 : Need(25, 75)
            B doesn't have 75ml so will need to use all of it
            A - 25 = 50 - 25 = 25
            B - 75 = 50 - 75 = -25

            A > 0 && B <= 0     b gets empty first so return 0
            
            P3 = Operation(A - 25, B - 75) = 0

        - Operation 4 : Need(50, 50)
            A and B both have 50 ml
            A - 50 = 50 - 50 = 0
            B - 50 = 50 - 50 = 0
            
            A <= 0 && B <= 0       both are <=0 at same time so return 0.5

            P4 = Operation(A - 50, B - 50) = 0.5
            

        - we need to add all probabilities of operations
            eventProbability = P1 + P2 + P3 + P4 
                             = 1  + 1  + 0  + 0.5
                             = 2.5

        - also aplyala ya probabilities tr fact Events chya bhetlyat
            but per operation 0.25 probability ahe we need to use that too
            so ji pn eventProbability sum ahe tyat apan operationProbability add karu

            totalProbability = eventProbability * operationProbability
                             =       2.5        *        0.25
                             = 0.625


 
 * Pseudo Code :
 
    Globally Declare Variables
    
    int[][] operations  - create same matrix array we used to do for directionsMatrix
                            just ithe operations sathi ahe
    operationProbability = 0.25

    function soupServings (int n) {
    
        - call recursion function probabilityWhen(n, n)
    
    }


    function probabilityWhen (int A, int B) {
    
        - Here our order is event2 -> event1 -> event 3 why?
            karan me event1 -> event2 kel tr bcoz of A <= 0 to kadhi he check karnarch nahi B <= 0 pn ahe ka te 
            and bcoz of that we can get wrong probability 

            Event2 : soup A and B both become empty at same time (A <= 0 && B <= 0)
                else if(A <= 0 && B <= 0)   
                    return 0.5

            Event1 : soup A becomes empty first (A <= 0)
                if(A <= 0)  
                    return 1

            Event3 : soup B becomes empty first (B <= 0)
                else if(B <= 0) 
                    return 0

        
        - declare a variable currProbability = 0 to get probability for curr operation

        - call recursion probabilityWhen for all 4 operations
            for(operation : operations)

                - pratek operations chya veles serving kiti havyat tyachi value ghe
                    serveA = operation[0]
                    serveB = operation[1]
                    
                - call recursion for A - serveA and B - serverB
                - then add that value in currProbability 


        - at the end aplyala total probability return karavi lagel so
            totalProbability = currProbability * operationProbability
    
    }



 */