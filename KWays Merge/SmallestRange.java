import java.util.*;

public class SmallestRange {
    
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
 * Pseudo Code :
 * 
 * function smallestRange(lists){
 * 
 *       
 * }
 * 
 */