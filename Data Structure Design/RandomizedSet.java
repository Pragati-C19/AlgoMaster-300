import java.util.*;

public class RandomizedSet {
    
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