import java.util.*;

public class OpenLock {
    
    public int openLock(String[] deadends, String target) {
        
        // Declare Variables
        int minTurn = 0;
        Set<String> visitedSet = new HashSet<>();
        
        // used Arrays.aslist here to add all values of deadends in set without using for loop
        Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));   

        Queue<String> queue = new LinkedList<>();

        // add initial value in queue
        queue.add("0000");
        visitedSet.add("0000");

        while (!queue.isEmpty()) {
            
            System.out.println("    -> Updated Queue look like for this level : " + queue);

            // get size of queue for level tracking
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                
                String popString = queue.poll();

                // if u found target
                if (popString.equals(target)) {
                    
                    System.out.println("minTurn so far : " + minTurn);
                    return minTurn;
                }

                // generate all 8 possible strings 
                for (int j = 0; j < 4; j++) {
                    
                    char[] charsOfPopString = popString.toCharArray();

                    char originalChar = charsOfPopString[j];
                    // let's move up
                    charsOfPopString[j] = originalChar == '9' ? '0' : (char) (originalChar + 1);
                    
                    String upString = new String(charsOfPopString);
                    System.out.println("    Up String : " + upString);

                    // let's move down
                    charsOfPopString[j] = originalChar == '0' ? '9' : (char) (originalChar - 1);

                    String downString = new String(charsOfPopString);
                    System.out.println("    Down String : " + downString);
                    
                    // Add those valid strings in queue
                    if (!visitedSet.contains(upString) && !deadendsSet.contains(upString)) {
                        
                        queue.add(upString);
                        visitedSet.add(upString);
                    }

                    if (!visitedSet.contains(downString) && !deadendsSet.contains(downString)) {
                        
                        queue.add(downString);
                        visitedSet.add(downString);
                    }

                }
            }

            minTurn++;
        }

        return -1;
    }

    public static void main(String[] args) {

        OpenLock solutions = new OpenLock();

        String[] deadends1 = {"0201","0101","0102","1212","2002"};
        String target1 = "0202";
        System.out.println("Result 1 -> " + solutions.openLock(deadends1, target1) + "\n");

        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println("Result 2 -> " + solutions.openLock(deadends2, target2) + "\n");

        String[] deadends3 = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target3 = "8888";
        System.out.println("Result 3 -> " + solutions.openLock(deadends3, target3) + "\n");

    }
}

/*
 * Intuitions :
    
    1. we have lock in front of us, with 4 circular wheels
    2. each wheel has 10 slots : 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    3. the wheel can rotate freely and wrap around 
        - means we can turn 9 to be 0 and 0 to be 9
        - wheel is turning only one notch.. that means it can increase +1 or decrease -1
    4. the lock initially starts at '0000', string represent state of 4 wheels
    5. we have given list of deadends
        - means if lock display any of those codes the wheels of the lock will stop turning
        - and u'll be unable to open the lock
    6. target is given 
        - which represent the value of the wheels that will unlock the lock
    7. return the minimum total number of turns required to open the lock 
        or -1 if it is impossible
 
 
 * Pattern :
 
    1. if we find target will check min (resultTurn, currTurn)
    2. will return currTurn


^ soo much different thoughts

    1st Appraoch:
        
        - I'm thinking what can I add in queue.. we are not using matrix here.. so what will come here.. maybe string with next valid moves?.. 
        - our starting point is [0000] so initially queue will be [0000]
        - since each move is +1 or -1 so let's check all valid combinations and add it in queue
                    
                   Digit   |   +1   |   -1   
                -----------|--------|--------
                   pos 0   |    1   |    9  
                   pos 1   |    1   |    9  
                   pos 2   |    1   |    9  
                   pos 3   |    1   |    9  

            queue : [1000, 9000, 0100, 0900, 0010, 0090, 0001, 0009]

        - and then will filter out all the deadends from this neighbors and add it in queue
        - also will mark as visited why?
            Now let’s say you move to "1000", and later from "1000" you could again try to go back to "0000".
            But… you’ve already visited "0000" earlier.

        - initially it's like
            queue.add(0000)
            visited.add(0000)

        - so for now I think every time we check any next state willbe like
            if(!visited.contains(next) && !deadends.contains(next))
                queue.add(next)
                visited.add(next)            



    2nd Approach:

        - my thinking is wandering around what if I fixed first num?... means let me walk u through my thinking

        - if we have target as 0202 and deadends like ["0201","0101","0102","1212","2002"],
        - then will check first abt at which positions deadend nums are?
            1st position -> 0, 1, 2
            2nd position -> 0, 1, 2
            3rd position -> 0, 1
            4th position -> 1, 2

        - then will check abt targets need of increase and decrease..
            to get 0 we can switch to -> 9, 0, 1
            to get 2 we can switch to -> 1, 2, 3
            to get 0 we can switch to -> 9, 0, 1
            to get 2 we can switch to -> 1, 2, 3

        - now let's check only first position which can't have 0, 1, 2 bcoz if it does then it may be possible to hit any of the deadlocks.. so at first position to get 0 we can use 9 to avoid using any 0, 1 there.. 
        - I used same logic for other 2 test cases...too maybe it's working

        - It has it's draw backs
            YES, that intuition makes sense — and it can work.
            But :
                BFS will always find the shortest path.
                Your heuristic might find a valid and short one, too 
                it just doesn't guarantee shortest unless you exhaustively explore.

            Indirectly this approach is not BFS approach it might be brute force


 * Pseudo Code :
 
    function openLock(String[] deadends, target) {
    
        -> Declare variables
            minTurns = Max_Value
            queue                   - to add all posible strings after moving by +1 or -1
            visitedSet              - to check if the string is already visited
            deadendsSet             - using it to easily check is string is in deadend or not
            
        -> let's add 0000 as initial string in queue 
            and also mark it as visited to avoid going in loop
                queue.add(0000)
                visitedSet.add(0000)

        -> now start while loop
            while(!queue.isEmpty)
                
                queueSize = queue.size      - for level tracking

                for(i = 0 to queueSize)

                    popString = queue.poll()

                    -> reached target
                        if(target == popString)
                            minTurn = min(minTurns, currTurns)

                    -> generate all 8 possible combinations from current
                        for(j = 0 to 4)     - why 4? it's a position

                            -> let's get string in char[]
                                char[] chars = popString.toCharArray

                            -> let's move +1
                                chars[j] = chars[j] + 1
                                up = new String(chars)

                            -> let's move down
                                chars[j] = chars[j] - 1
                                down = new String(chars)

                            -> Add those two string in queue if condition sets
                                if(!visitedSet.contains(up) && !deadendsSet.contains(up))
                                    queue.add(up)
                                    visited.add(up) 
                                    
                                if(!visitedSet.contains(down) && !deadendsSet.contains(down))
                                    queue.add(down)
                                    visited.add(down) 
                
                -> after processing current level let's do currTurn++

        -> if(minTurn >= 0 ) return minTurn
            else return -1 
    
    }
 
 
 */