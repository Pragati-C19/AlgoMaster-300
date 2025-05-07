import java.util.*;

public class Candy {
    
    public int candy(int[] ratings) {
           
        int n = ratings.length;
        int totalCandyCount = 0; 

        // sort the array 
        // If you sort the ratings and assign candies based on that, you'll lose the index relationships 
        // i.e., who is next to whom. This problem is not about global values, it’s about local neighbors.
        // Arrays.sort(ratings);
        // System.out.println("Sorted Rating Array : " + Arrays.toString(ratings));

        int[] candies = new int[n];

        Arrays.fill(candies, 1);
        System.out.println("   -> Candies filled with initial Value 1 : " + Arrays.toString(candies));

        int prevRating = ratings[0];
        int prevChildCandies = candies[0];

        for (int i = 0; i < n; i++) {
            
            if (ratings[i] > prevRating) {
                
                candies[i] = candies[i] + 1;
                System.out.println("    -> Rating of " + i + " is > than Rating of " + (i - 1));
                System.out.println("        -> Candies of " + i + " : " + candies[i]);
            }

            prevRating = ratings[i];
            prevChildCandies = candies[i];
            System.out.println("    -> Updated prevRating and prevChildCandies : " + prevRating + " , " + prevChildCandies);

            System.out.println("    -> Candies Array so Far : " + Arrays.toString(candies));

        }

        System.out.println("Candies Array so Far : " + Arrays.toString(candies));
        
        for (int candy : candies) {
            totalCandyCount = totalCandyCount + candy;
        }

        return totalCandyCount;
    }

    public static void main(String[] args){

        Candy solution = new Candy();

        int[] ratings1 = {1, 0, 2};
        System.out.println("Result 1: " + solution.candy(ratings1) + "\n");

        
        int[] ratings2 = {1, 2, 2};
        System.out.println("Result 2: " + solution.candy(ratings2) + "\n");

    }

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