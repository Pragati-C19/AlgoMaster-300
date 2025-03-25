import java.util.*;

public class PickIndex {
    
    public PickIndex(int[] w) {
        
    }
    
    public int pickIndex() {
        
    }

    public static void main(String[] args){
        int[] weights = {1, 3};
        PickIndex solution = new PickIndex(weights);

        // Simulate multiple calls to pickIndex()
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
    }

}

/*
 * 
 * 
 * Understanding the problem :
 * 
 * 1. Given an array w where each number represents a "weight" for that index.
 * 2. The higher the weight, the more likely that index should be picked
 * randomly.
 * 3. Example:
 * Take w = [1, 3].
 * - Index 0 has weight 1 (lighter).
 * - Index 1 has weight 3 (heavier).
 * The total weight is 1 + 3 = 4.
 * 
 * The probability of picking each index is:
 * - index 0: 1/4 = 0.25 (25% chance)
 * - index 1: 3/4 = 0.75 (75% chance)
 * So, if you call pickIndex() many times, index 1 should appear more often —
 * roughly 3 times as frequently as index 0.
 * 
 * 
 * Intuitions :
 * 
 * 1. this que is like below 
 * 2. if u have 1 ticket of red color and 3 ticket of blue color
 * and now u are blind fold the probability of u picking blue ticket is more right?
 * 3. 
 * 
 * Pattern :
 * 
 * 1. Identify what controls the probability — here, it's the weights array.
 * 2. Convert weights to a form where equal random picking works — in brute force, we expand weights into a long list.
 * 3. Randomly select from the new data — any basic random picker (like Math.random() or Random in Java) will now behave correctly.
 * 
 * Pseudo Code :
 * 
 * 1. Brute Force:
 * 
 * Declare new list 
 * 
 * // Constructor: Expands weights into a list of indices
 * function Solution (int[] w){
 *      for(int i = 0; i<w.length; i++){
 *          for(int j = 0; j < w[i]; j++){
 *              list.add(i);    // Add index 'i' multiple times based on weight
 *          }
 *      }
 * }
 * 
 * // Pick Random index
 * function pickIndex(){
 *      Declare Random 
 *      
 *      int randomIndex = random.nextInt(list.size())
 *      or int randomIndex = (Math.random() * list.size());
 * 
 *      return list.get(randomIndex)
 * }
 * 
 * 
 * Binary Search :
 * 
 * 
 * 
 */
