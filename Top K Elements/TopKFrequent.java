import java.util.*;

public class TopKFrequent {
    
    public int[] topKFrequent(int[] nums, int k) {
     
        return nums;
    }

    public static void main(String[] args){

        TopKFrequent solution = new TopKFrequent();

        int[] nums1 = {1,1,1,2,2,3};
        int k1 = 2;
        System.out.println("Result 1 : " + Arrays.toString(solution.topKFrequent(nums1, k1)) + "\n"); 

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("Result 2 : " + Arrays.toString(solution.topKFrequent(nums2, k2)) + "\n"); 

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. nums and a integer k is given
 * 2. we need to find k numbers who has hight frequency count
 * 
 * Pattern :
 * 
 * 1. I need Hashmap here -> to store nums count or u can say frequency
 *      - map<Integer, Integer> freqMap = new HashMap<>()
 *      - for (num : nums) -> freqMap.put(num, freqMap.getorDefault(num, 0) + 1);
 *      - above line will store all nums in hashmap and it's frequency 
 * 2. Now let's think of how to get k number with maximum frequency from this hashmap 
 *      - maybe I was working on heap problem so rn I'm thinking of using maxheap there
 *      - in maxHeap will store key and value from hashmap
 *      - and will priorotised this maxHeap by max to min value count from hashmap
 *      - PQ<int[]> maxHeap = new PQ<>((a, b) -> b - a)
 *      - ok I need to check the syntax for it..
 * //!  - here is the ans
 *      -> use Map.Entry<Integer, Integer> instead of int[] 
 *              if u remember we used to get entry in for loop there we use that entries type this is that
 *      -> use a.getValue and b.getValue instead of only a and b or even freqMap.get(a) freqMap.get(b)
 *              it will make sure that heap will priorotised by value in map
 *          
 * 3. after doing that will pop out k elements from heap
 *      - for(i = 0 to k) -> result[i] = maxHeap.poll()
 * 
 * 
 * bugs in my thinking
 * 
 * - Hashmap madhe tr ready ahe maze mazya key and value but tyatle max k kashe kadhnar
 * bcoz Hashmap sorted nasto TreeMap sarkha.. and second thing is value cha max havay mala 
 * - yachyanantr mala fact ekach yetey ki I should use heap
 *      tyat problem ahe mala nahi mahit me heap kasa define karu?.. 
 *      jevha updatedArray karayche tevha [0], [1], [2] ni place taku shakat hote.. but ata kashi taku?
 * - add kuthe value in heap? mala tyasathi pn for loop lagel?
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * function topKElements(nums, k){
 * 
 *      Map<Integer, Integer> freqMap = new HashMap<>()
 *      PQ<Map.Entry<Integer, Integer>> maxHeap = PQ<>( (a, b) -> 
 *          b.getValu() - a.getValue();
 *      )
 *      
 *      result = new int[k]
 * 
 *      for(num : nums )    
 *          freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
 *      
 *      // Now add all entries from map into heap
 *      heap.addAll(map.entrySet());
 * 
 *      for(i = 0 to k)
 *          result[i] = maxHeap.poll()
 * 
 * 
 *      return result;
 *      
 * }
 * 
 */
