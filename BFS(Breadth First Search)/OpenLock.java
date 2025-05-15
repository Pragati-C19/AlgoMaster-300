import java.util.*;

public class OpenLock {
    
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
 
 
 
 */