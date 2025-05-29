import java.util.*;

public class LastStoneWeightII {
    
}

/*
 * Intuition :
 
    1. We have given a stones array 
        - where stones[i] is the weight of i'th stone
    2. we are playing game with the stones.
        - on each turn, we choose any two stones and smash them together
    3. suppose stone x <= y then result of smash is :
        - x == y, both stones are destroyed
        - x != y, the stone of weight x is destroyed, 
                  and stone of weight y has new weight now -> y - x
    4. at the end of game there is at most one stone left
    5. need to return the smallest possible weight of the left stone
    6. if no stones left return 0
 
 
 * Pattern :

    1. Let's start brute force approach
        - first will convert int[] stones into List<Integer>
        - start a loop stones.size() > 1 till we do below steps
        - will sort stones array in descending order every time bcoz we want to take max nums 
        - then we take first two nums 
            x = stones.remove(0)
            y = stones.remove(1)
        
        - If x and y are not equal, push the difference back
            if(x != y)
                stones.add(y-x)

        - else we are already doing stones remove so it's automatically removing those 2 nums

        - at the end return 0'th index num or if list.size get's zero will return 0 



 * Pseudo Code :
 


 */