import java.util.*;

public class RandomizedSet {
    
    // Declare Global Variables 


    public RandomizedSet() {
        
    }
    
    public boolean insert(int val) {
        
    }
    
    public boolean remove(int val) {
        
    }
    
    public int getRandom() {
        
    }

    public static void main(String[] args){

        RandomizedSet solution = new RandomizedSet();

        System.out.println("Final Result : ");
        System.out.println("  1st Iteration : " + solution.insert(1) + "\n");       // true
        System.out.println("  2nd Iteration : " + solution.remove(2) + "\n");       // false
        System.out.println("  3rd Iteration : " + solution.insert(2) + "\n");       // true
        System.out.println("  4th Iteration : " + solution.getRandom() + "\n");     // 1 or 2
        System.out.println("  5th Iteration : " + solution.remove(1) + "\n");       // true
        System.out.println("  6th Iteration : " + solution.insert(2) + "\n");       // false
        System.out.println("  7th Iteration : " + solution.getRandom() + "\n");     // 2

    }

}

/*
 * 
 * //?  Learn How to randomized the hashset.. get random value from hashset
 * 
 *  
 * Intutions :
 * 
 * 1. RandomizedSet() Initializes the RandomizedSet object.
 * 2. bool insert(int val)
 *      if(not present) -> add in set and return true
 *      else return false
 * 3. bool remove(int val)
 *      if(present) -> remove it from set and return true
 *      else return false
 * 4. int getRandom()
 *      return random element from that set
 *      maybe I can do like just set.get
 * 
 * Pattern :
 * 
 * 1. will use hashset here
 * 2. will store val in that set
 * 3. and will check if that val exist or not from that set
 * 
 * Pseudo Code :
 * 
 * 1. Gloablly Declare variables
 *      - Set<Integer> randomizeSet
 * 
 * 2. RandomizedSet()
 *      - randomizeSet = new HashSet
 * 
 * 3. bool insert(int val)
 *      - if(!randomizeSet.contains(val)) 
 *          set.add(val)
 *          return true
 *      - return false
 * 
 * 4. bool remove(int val)
 *      - if(randomizeSet.contains(val))
 *          set.remove(val)
 *          return true
 *      - return false
 * 
 * 5. int getRandom()
 *      - I need to get random then it can be any number soo
 *      - to get random nums we need to use Random Function
 *      - Covert HashSet into ArrayList 
 *          List<Integer> hashToList
 *      - Generate random index
 *          randomIndex = random.nextInt(hashToList.size())
 *      - Get the random element from hashToList now
 *          randomVal = hashToList.get(randomIndex)
 * 
 * 
 */