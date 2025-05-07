import java.util.*;

public class Candy {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. Each child must have at least one candy.
 * 2. Children with a higher rating than their immediate neighbor must get more candies than that neighbor.
 * 3. If a child has a higher rating than an immediate neighbor, they must get more candies than that neighbor
 *      - If rating[i] > rating[i-1] means candies[i] = candies[i-1] + 1
 *      - candies should be greater than neighboring once
 * 
 * 
 * Pattern :
 * 
 * 1. If rating[i] > rating[i-1] then give more candy to child[i] 
 *      - that means candies[i] = candies[i-1] + 1
 * 2. 
 * 
 * Pseudo Code :
 * 
 * function candy(ratings) {
 *      
 *      -> let's sort the array we only care about count of candies so
 *              ratings.sort
 * 
 *      -> create a candies array to store candies for each kid
 *              candies = new int[ratings.length]
 * 
 *      -> fill values in candies array
 *              using for loop or by short cut
 * 
 *          - for(i = 0 to candies.length) -> candies[i] = 1
 *          - Arrays.fill(candies, 1)
 *          
 *      -> Declare a ariable to store prevRating
 *              prevRating = ratings[0]
 *              prevChildCandies = candies[0]
 *          
 *      -> for(i = 1 to ratings.length)
 *              
 *              if(rating[i] > prevRating) 
 *                  candies[i] = prevChildCandies + 1
 * 
 *      -> Sum of all nums in candies
 *          
 *              for(candy : candies) 
 *                  totalCandy = totalCandy + candy
 * 
 * 
 *      return totalCandy;
 *          
 * }
 * 
 * 
 */