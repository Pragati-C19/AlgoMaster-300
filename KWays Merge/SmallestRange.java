import java.lang.reflect.Array;
import java.util.*;

public class SmallestRange {
    
    public int[] smallestRange(List<List<Integer>> nums) {
        
        int kLists = nums.size();
        int[] resultRange = new int[2];
        int prevDiff = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        // Declare a heap to store minValue inorder by a[0] which is num
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // will store only 0'th index from all list in heap
        for (int i = 0; i < kLists; i++) {
            
            // used double get here bcoz type of nums is List<List<Integer>>
            int num = nums.get(i).get(0);   // num
            int numList = i;        // num store in this list 
            int numListIndex = 0;   // num's Index in it's list
            
            minHeap.add(new int[]{num, numList, numListIndex});
            
            maxValue = Math.max(maxValue, num);

        }  
        // print int[] Heap like this 
        System.out.println("MinHeap After adding 0'th Index : " + Arrays.deepToString(minHeap.toArray()));
        System.out.println("MaxValue after adding 0'th index : " + maxValue);

        while (!minHeap.isEmpty()) {
            
            int[] currMinValue = minHeap.poll();

            int minValue = currMinValue[0];
            int minValueList = currMinValue[1];
            int minValueListIndex = currMinValue[2];

            int currDiff = maxValue - minValue;
            System.out.println("   -> Current Difference of (" + minValue + ", " + maxValue + ") : " + currDiff);

            if  (currDiff < prevDiff){

                resultRange[0] = minValue;
                resultRange[1] = maxValue;
                prevDiff = currDiff;

                System.out.println("       ~ Current Result Range with Difference " + Arrays.toString(resultRange) + " : " + prevDiff);
            }

            int changeMinValueListIndex = minValueListIndex + 1;

            if(changeMinValueListIndex < nums.get(minValueList).size()){

                int nextNum = nums.get(minValueList).get(changeMinValueListIndex);

                minHeap.add(new int[] {nextNum, minValueList, changeMinValueListIndex});
                
                maxValue = Math.max(maxValue, nextNum);

                System.out.println("            MinHeap After adding 0'th Index : " + Arrays.deepToString(minHeap.toArray()));
                System.out.println("            MaxValue after adding 0'th index : " + maxValue);
            }
            else {
                break;
            }

        }

        return resultRange;
    }

    public static void main(String[] args){

        SmallestRange solution = new SmallestRange();

        List<List<Integer>> nums1 = new ArrayList<>();
        nums1.add(Arrays.asList(4, 10, 15, 24, 26));
        nums1.add(Arrays.asList(0, 9, 12, 20));
        nums1.add(Arrays.asList(5, 18, 22, 30));
        System.out.println("Result1 -> " + Arrays.toString(solution.smallestRange(nums1)) + "\n");

        List<List<Integer>> nums2 = new ArrayList<>();
        nums2.add(Arrays.asList(1, 2, 3));
        nums2.add(Arrays.asList(1, 2, 3));
        nums2.add(Arrays.asList(1, 2, 3));
        System.out.println("Result2 -> " + Arrays.toString(solution.smallestRange(nums2)) + "\n");       


    }
}

/*
 * 
 * //? Check out below video it help me to understand que
 * https://youtu.be/0IqFMBatlhU?si=CF-bI68sntELPSoq
 * 
 * 
 * Intuitions :
 * 
 * 1. K sorted lists are given 
 * 2. need to find smallest range which includes at least one num from each list
 * 3. How can we say range is smallest?
 *      - [a, b] -> [4, 5] , [c, d] -> [24, 20]
 *      - if diff(b - a) < diff(d - c)  : (5-4) < (20-24)
 *      - if difference of 2 range is same then will check if a < c : 4 < 24
 *      - also one more thing num from that range should be available in all list 
 * 
 * 
 * Pattern :
 * 
 * 1. use hashmap to store range that is valid
 * 2. How will u get best range if u have given some numbers like -> 4, 7, 1, 2, 9
 *      - it will be (min, max) -> (1, 9) right? in that range all numbers given will come
 * 3. I thought of using treeMap but in TreeMap or HashMap we can't store 3 things
 *    so let's use heap and add 3 ranges in heap
 *      (min, max, diff)
 * 4. now let's think what we don't know so far
 *     - there are k lists not just three to specifically use i, j, k only
 *     - how to increase those index's as per how I think
 *          - for three list I thought we can do while(i,j,k < lists1,list2,list3.length)
 *          - then use if(min's index is i or j or k) increase that spacific index 
 * 
 * Basically what is happening in the problem see
 * 
 * example : [1,4,5],[1,3,4],[2,6]
 *  
 *  - will take numbers from each lists one by one i, j, k -> at start it will be (0, 0, 0)
 *      where i is tracking index of first lists
 *            j is tracking index of second lists
 *            k is tracking index of third lists
 *  - then will find (min, max) this will be it's range
 *  - then check it's difference
 *  - and now will change min value to next number from that list
 *      why? bcoz we have sorted list.. 
 *      if we change max then all values of it's next are greater
 *      and it will give us maximum difference
 * 
 * 
 *      (i, j, k)    |   l1[i], l2[j], l3[k]    |     Range      |   Diff    |            Ans
 *   ----------------|--------------------------|----------------|-----------|--------------------------
 *      (0, 0, 0)    |        [4, 0, 5]         |     (0,5)      |     5     |                 (0,5)
 *      (0, 1, 0)    |        [4, 9, 5]         |     (4,5)      |     5     |      0 < 4    : (0,5)
 *      (1, 1, 0)    |        [10, 9, 5]        |     (5,10)     |     5     |      0 < 5    : (0,5)
 *      (1, 1, 1)    |        [10, 9, 18]       |     (9,18)     |     9     |   d(5) < d(9) : (0,5)
 *      (1, 2, 1)    |        [10, 12, 18]      |     (10,18)    |     8     |   d(5) < d(8) : (0,5)
 *      (2, 2, 1)    |        [15, 12, 18]      |     (12,18)    |     6     |   d(5) < d(6) : (0,5)
 *      (2, 3, 1)    |        [15, 20, 18]      |     (15,20)    |     5     |      0 < 15   : (0,5)
 *      (3, 3, 1)    |        [24, 20, 18]      |     (18,24)    |     6     |   d(5) < d(6) : (0,5)
 *      (3, 3, 2)    |        [24, 20, 22]      |     (20,24)    |     4     |   d(4) < d(5) : (20,24)
 *      
 *    - after this we need to increase jth index but there no value for list2 now
 *    - that means next range we can't take
 *    - in result take the smallest difference value..
 *    - if difference is same for any range check it's min value if the min value is smaller than other's take that range as smallest
 * 
 * 
 * Let's think fresh based on what we know we created a story let's code it
 * 
 * 1st approach without heap u can say it's a brute force
 * 
 * 1. we need a array (combinedNum) whose size will be k -> it stores given list's index (like i,j,k) in it
 *      - at start it will store [0, 0, 0...] 
 *      - tbh we are storing [listIndex, num, elmentIndex]
 * 2. Declare a resultRange -> to store range
 * 3. Do below steps while(true) -> bcoz we want it to loop till we say break
 * 4. for(i = 0 to k)
 *      - listIndex = i -> kontya list madhe present ahe
 *      - numIndex = combinedNum[i] -> num chi list madhali index deto he 
 *      - num = nums[listIndex][numIndex] -> value of that element
 *      - if(num < minValue) -> 
 *          minValue = element 
 *          minValueIndex = listIndex
 *      - maxValue = max(maxValue, num)
 * 5. currDiff = maxValue - minValue
 * 6. if(currDiff < prevDiff)
 *      resultRange[0] = minValue
 *      resultRange[1] = maxValue
 *      prevDiff = currDiff
 * 7. changedMinValueIndex = combinedNum[minValueIndex]+1
 * 8. if(changedMinValueIndex >= nums[minValueIndex].size()) -> break
 * 9. combinedNum[minValueIndex] = changedMinValueIndex
 * 
 * 10. return resultRange
 * 
 * 
 * //? let's learn heap approach fully
 * 
 * 2nd Approach with minHeap
 * 
 * 1. Declare below things
 *      - int[] resultRange = new int[2] -> will declare a resultRange whose size is 2 to store min and max
 *      - prevDiff = Max_Value     -> for prevDiff set Initial value to Infinity
 * 2. we will use minHeap to store [num, numsList, numListIndex]
 * 3. at start add 0'th index value in heap for all lists
 *    we are not creating any array now we are dirctly adding those values in minHeap
 *      - numList = i -> this is the list in which element is store
 *      - numListIndex = 0 -> list madhe kitva index la ahe to element?
 *      - num = nums[i][0] -> this is element value
 *      -> minHeap.add(num, numList, numListIndex)
 *      -> maxValue = max(maxValue, num)
 * 4. while(!minHeap.isEmpty)
 *      - currMinValue = minHeap.poll()    - this will give minValue [num, numsList, numsListIndex]
 *      - minValue = currMinValue[0]
 *      - numsList = currMinValue[1]
 *      - numsListIndex = currMinValue[2]
 *      - maxValue                         - this we already know for 0'th index nums now
 *      - currDiff = maxValue - minValue
 *      -> if(currDiff < prevDiff)
 *              resultRange[0] = currMinValue[0]
 *              resultRange[1] = currMaxValue
 *              prevDiff = currDiff
 *      -> if(currDiff == prevDiff)
 *              
 *      - changedNumsListIndex = numsListIndex + 1
 *      - if(changedNumsListIndex < nums[numsList].size())
 *              nextNum = nums[numsList][changedNumsListIndex]
 *              minHeap.add(nextNum, numsList, changedNumsListIndex)
 *              maxValue = max(maxValue, nextNum)
 *      - else break
 * 
 * 5. return resultRange
 * 
 * 
 * Pseudo Code :
 * 
 * function smallestRange(lists){
 * 
 *      int[] resultRange = new int
 *      prevDiff = MAX_VALUE
 *      maxValue = MIN_VALUE
 *  
 *      minHeap = new pq((a,b) -> a[0] - b[0])
 * 
 *      for(i = 0 to k)
 *          numList = i
 *          numListIndex = 0  // bcoz we are adding only 0'th index of each list for now
 *          num = nums[i][0]
 *  
 *          minHeap.add(num, numList, numListIndex)
 *          
 *          maxValue = max(maxValue, num)
 *       
 *      while(!minHeap.isEmpty)
 *          
 *          currMin = minHeap.poll
 *          
 *          minValue = currMin[0]
 *          minValueList = currMin[1]
 *          minValueListIndex = currMin[2]
 * 
 *          currDiff = maxValue - minValue
 *          
 *          if(currDiff < prevDiff)
 *              resultRange[0] = minValue
 *              resultRange[1] = maxValue
 *              prevDiff = currDiff
 * 
 *          changedListIndex = minValueListIndex + 1
 *          if(changedListIndex < nums[minValueList].size)
 *              
 *              nextNum = nums[minValueList][changedListIndex]
 *              minHeap.add(nextNum, minValueList, changedListIndex) 
 *              maxValue = max(MaxValue, nextNum)
 * 
 *          else break
 * 
 *      retrun resultRange
 * }
 * 
 */