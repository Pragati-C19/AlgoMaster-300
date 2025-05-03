import java.util.*;

public class LRUCache {
 
       
    // Globally Declare variables 
    LinkedHashMap<Integer, Integer> cacheMap;
    
    public LRUCache(int capacity) {
        
        // Assign value to global variables
        cacheMap = new LinkedHashMap<>(capacity, 0.75f, true);

        // I didn't knew what to write at leadFactor I wrote random thing.. bcoz it was necessary
    }
    
    public int get(int key) {
        
        if (cacheMap.containsKey(key)) {

            System.out.println("    -> cahcheMap : " + cacheMap);
            return cacheMap.get(key);
        }

        return -1;
    }
    
    public void put(int key, int value) {
        
        cacheMap.put(key, value);

        System.out.println("    -> After update cahcheMap : " + cacheMap);

        return;
    }

    public static void main(String[] args){

        LRUCache solution = new LRUCache(2);

        System.out.println("Final Result : ");

        solution.put(1, 1);
        System.out.println("  1st Iteration \n");

        solution.put(2, 2);
        System.out.println("  2nd Iteration \n");

        System.out.println("  3rd Iteration : " + solution.get(1) + "\n");    // 1

        solution.put(3, 3);
        System.out.println("  4th Iteration \n");

        System.out.println("  5th Iteration : " + solution.get(2) + "\n");    // 2

        solution.put(4, 4);
        System.out.println("  6th Iteration \n");

        System.out.println("  7th Iteration : " + solution.get(1) + "\n");    // -1

        System.out.println("  8th Iteration : " + solution.get(3) + "\n");    // 3

        System.out.println("  9th Iteration : " + solution.get(4) + "\n");    // 4

    }

}

/* 
 * 
 * //? Took help from this video to understand the problem
 * https://youtu.be/81h8O-0U5oo?si=154q6DXDhsASbnpz
 * 
 * //? Learn LinkedHash Map here
 * https://youtu.be/Qd-3Xj8h0EE?si=FFq1SqjxwSGQudYi
 * 
 * 
 * Intutions :
 * 
 * 1. LRUCache -> Initialize the LRU cache with positive size capacity
 * 2. int get (int key) -> return value of the key 
 *      - if key is not there return -1
 * 3. void put(int key, int value) -> 
 *      - updated the value of key if key exists
 *      - otherwise add it in cache 
 *      - if cache size exceeds the capacity then remove least recently used key
 *      - remove least recently used means jyala use karun khup jast vel zalay
 * 
 * 
 * Pattern :
 * 
 *  
 * 1. will use Doubly-LinkedList and Hashmap here... why?
 *   - ya que madhe mhnlay je apan recenlty use nahi kelet te remove kara and new add kara jr size kami asel tr
 *   - example : 
 *        me 1 takla 2 takla sadhya 2 ha recently use ahe so me ha change nahi karnar
 *        ata achank me 1 la visit kel now this is the recently used one right?
 *        jevha time yeto to add 3 ata me 2 remove karun tithe 3 takel  
 *   - so basically kay hotay na.. apan konti key visit keli ki tila end la taku array chya for safer side
 *   - jevha new konta add karaycha asel tevha first la jo asel tyala remove karu
 *   - ata kasay na array use karnar ani apan first remove karnar nehmi tr tyanantrche sagle nums shift karave lagtil right?
 *   - to avoid that issue apan linkedList use kartoy
 *   - now whyy doubly?.. bcoz mala backward pn travel karav lagel
 *   - to change one num at specific possition mala tyachya prev la tyachya next shi attach karav lagel..
 *   - tyamul me doubly linkedlist use kartey
 * 
 *   - Hashmap madhe kay store karnar?
 *   - tyat apan key and tyacha address in doubly linkedlist store karnar.
 *   - yani aplyala to find one key purn list nahi check karat basavi lagnar.. 
 *   - apan direct address gheu map madhun and direct tithe jau linkedlist madhe tyacha prev la attach karu next shi simple num delete zala
 *  
 *   - Still there is one issue.. that what abt value? ti kuthe store kartoy?
 *   - will add value in pair of address in hashmap.. so hashmap looks like key -> [address, value]
 * 
 * 2. LinkedHashMap - Now Java has a bestt syntax or function which can help us here
 *   
 *  -> what is LinkedHashMap?
 *      - jasa c++ madhe linkedList madhe add front add back or erase(x) karan sop asat by c++ STL dll
 *      - tasach ithe pn ahe LinkedHashMap tech kam karto..
 *      - for example me store kel
 *              (orange, 10)
 *              (apple, 50)
 *              (mango, 20)
 *      - jr hashmap use kela tr tyat yachi order me lihiliye tashi nasel.. it can me random or maybe upside down
 *      - but jr LinkedHashMap use kel tr ti 10 -> 50 -> 20 ch asel.. no change
 *      - ohk he zal order ch ajun ek yacha fayda ahe..
 *      - u remeber me ya que madhe rrcently use vala secure karat hote and end la takat hote
 *      - java madhe LinkedHashMap does that thing with syntax
 *           = new LinkedHashMap<>(initialCapacity, loadFactor, accessOrder);
 *              
 *               initialCapacity -> hashmap la size declare karan 
 *               loadfactor -> internal array asel tyachi size kevha double karaychiye
 *               accessOrder -> hach king ahe ithe.. he jr true kel tr jevha pn me any num get karel or work karel
 *                              to acutomatically without any effort direct khali jail at the end
 *               insertion-order -> it's by defalut from front jr access order false asel tr
 * 
 *              
 *      - LinkeHashMap use karayla same ahe hahmap sarkhach just yat thode benifit ahet like this ki je access kel te end la jail 
 *      - ata recently use khali jatoy.. mg mazi size jevha exceed hot asel I can say remove top maybe.. 
 *      - still samjal nasel tr watch that second video it's so simple and so easy to solve
 * 
 * 3. to remove Old num
 *      - here will use override which is removeEldestEntry 
 *      - retrun ur condition there like when u want to remove old entry 
 *      - for our condition when size() > capacity
 * 
 * 
 * 
 */