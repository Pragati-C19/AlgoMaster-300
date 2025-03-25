import java.util.*;

public class MinEatingSpeed {

}

/*
 * 
 * Understand the Question :
 * 
 * 1. Koko can control how fast she eats.
 * 2. She picks a number k -> number of bananas she tries to eat per hour
 * i.e. If Koko picks k = 5, she plans to eat 5 bananas per hour.
 * 3. Koko picks one pile per hour (not multiple piles in an hour).
 * 4. So, if the pile has 10 bananas and k = 5, she eats 5 from that pile,
 * leaving 5 behind.
 * i.e. One pile per hour — no mixing piles within an hour!
 * 5. If Koko picks a pile with only 3 bananas and her eating speed k = 5, she
 * can’t magically eat more bananas from another pile.
 * 6. She just eats the 3 bananas and wastes the rest of the hour — she doesn’t
 * roll over the remaining 2 bananas to another pile.
 * 7. No combining leftovers from different piles!
 * - Leftovers stay in that pile — she can’t take them to another pile or carry
 * over extra time..
 * 9. Find the smallest minimum integer k so Koko can finish all the bananas in h hours or less.
 * 
 * 
 * 
 * Example :
 * 
 * Let’s say piles = [3, 6, 7, 11] and k = 4:
 * - Hour 1: Koko eats 4 from pile 3. But since the pile only has 3, she
 * finishes
 * the whole pile and stops.
 * 
 * - Hour 2: Koko eats 4 from pile 6. Now 6 - 4 = 2 banana remains.
 * - Hour 3: eat the remaining 2 bananas from pile 2
 * 
 * - Hour 4: Koko eats 4 from pile 7. Now 7 - 4 = 3 banana remains.
 * - Hour 5: eat the remaining 3 bananas from pile 3
 * 
 * - Hour 6: Koko eats 4 from pile 11. Now 11 - 4 = 7 banana remains.
 * - Hour 7: Koko eats 4 from pile 11. Now 7 - 4 = 3 banana remains.
 * - Hour 8: Eat the remaining 3 from pile 4.
 * 
 * return smallest k which is 4
 * 
 * 
 * 
 * Pattern :
 * 
 * - A low: smallest value k will be 1
 * - A high: highest value k will be max of piles bcoz maybe koko can eat entire pile too in an hr so took the max one which is last
 * - now focus only on range of (low k , high k)
 * - Find the mid of it
 * - if (taken_hrs <= hrs) is less than hrs then think it's at low side so update high as mid
 * - else think it's at high side so update low as mid + 1
 * - repeat this process until low = high
 * - return low k
 * 
 * How to find taken hrs :
 * 
 * - see for example I know few taken hrs for k like
 * for [3, 6, 7, 11] hrs = 8, range of k is (1, 11)
 * - k = 1: taken hrs = 27hrs
 * - k = 2: taken hrs = 15hrs
 * - k = 3: taken hrs = 10hrs
 * - k = 4: taken hrs = 8hrs
 * - k = 5: taken hrs = 7hrs
 * - k = 6: taken hrs = 6hrs  
 * - k = 7: taken hrs = 5hrs
 * - k = 8: taken hrs = 5hrs
 * - k = 9: taken hrs = 5hrs
 * - k = 10: taken hrs = 5hrs
 * - k = 11: taken hrs = 4hrs
 * 
 * mid = (low + high) / 2        low     high    if(timeTaken <= hrs)
 *                                1       11          
 *  6 = (1 + 11) / 2              1        6          (6 <= 8)
 *  3 = (1 + 6) / 2           3 + 1 = 4    6          (10 > 8)
 *  4 = (4 + 5) / 2               4        4          (8 <= 8)
 * 
 * low = high so return low which is 4
 *          
 * 
 * 
 * Pseudo Code :
 * 
 * function minEatingSpeed(piles, h):
 * 
 *      low = 1;    // smallest value k can possibly be
 *      high = maximum of piles     // highest value k can possibly be
 *      
 *      // Basic setup of binary search
 *      while low < high : 
 *          // mid of low and high of k
 *          mid = (low + high) / 2
 *          
 *          if( takenHrs <= hrs ){
 *              high = mid;
 *          }
 *          else {
 *              low = mid + 1;
 *          }
 *      
 *      return low
 *      
 * // TODO : think of how to find taken_hrs
 * 
 * 
 * 
 * 
 */