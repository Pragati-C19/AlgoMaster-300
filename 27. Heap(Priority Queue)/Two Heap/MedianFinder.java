import java.util.*;

public class MedianFinder {
    
    // Globally declare heaps
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
        
        maxHeap.add(num);
        System.out.println("        [Start] Max Heap : " + maxHeap);

        minHeap.add(maxHeap.poll());
        System.out.println("        [Start] Min Heap : " + minHeap);

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());

            System.out.println("        [MAX < MIN] Max Heap : " + maxHeap);
            System.out.println("        [MAX < MIN] Min Heap : " + minHeap);            
        }
        
        System.out.println("        [Final] Max Heap : " + maxHeap);
        System.out.println("        [Final] Min Heap : " + minHeap);
        
    }
    
    public double findMedian() {
        
        if (maxHeap.size() > minHeap.size()) {
           
            System.out.println("        - [MAX > MIN] Max Heap After Peek : " + maxHeap);
            System.out.println("        - [MAX > MIN] Min Heap After Peek : " + minHeap);

            return maxHeap.peek();

        } else {

            System.out.println("        - [MAX <= MIN] Max Heap After Peek : " + maxHeap);
            System.out.println("        - [MAX <= MIN] Min Heap After Peek : " + minHeap);
            
            return (maxHeap.peek() + minHeap.peek()) / 2.0;

        }

    }

    public static void main (String[] args) {

        MedianFinder solution = new MedianFinder();

        System.out.println("Final Result : ");

        System.out.println("  1st Iteration : ");
        solution.addNum(6);

        System.out.println("  2nd Iteration : " + solution.findMedian());

        System.out.println("  3rd Iteration : ");
        solution.addNum(10);

        System.out.println("  4th Iteration : " + solution.findMedian());

        System.out.println("  5th Iteration : ");
        solution.addNum(2);

        System.out.println("  6th Iteration : " + solution.findMedian());

        System.out.println("  7th Iteration : ");
        solution.addNum(6);

        System.out.println("  8th Iteration : " + solution.findMedian());

        System.out.println("  9th Iteration : ");
        solution.addNum(5);

        System.out.println("  10th Iteration : " + solution.findMedian());

        System.out.println("  11th Iteration : ");
        solution.addNum(0);

        System.out.println("  12th Iteration : " + solution.findMedian());

        System.out.println("  13th Iteration : ");
        solution.addNum(6);

        System.out.println("  14th Iteration : " + solution.findMedian());

        System.out.println("  15th Iteration : ");
        solution.addNum(3);

        System.out.println("  16th Iteration : " + solution.findMedian());

        System.out.println("  17th Iteration : ");
        solution.addNum(1);

        System.out.println("  18th Iteration : " + solution.findMedian());

        System.out.println("  19th Iteration : ");
        solution.addNum(0);

        System.out.println("  20th Iteration : " + solution.findMedian());

        System.out.println("  21st Iteration : ");
        solution.addNum(0);

        System.out.println("  22nd Iteration : " + solution.findMedian());

        // System.out.println("  1st Iteration : ");
        // solution.addNum(1);  
        // System.out.println("  2nd Iteration : ");  
        // solution.addNum(2);
        // System.out.println("  3rd Iteration : " + solution.findMedian());  // Output: 1.5
        // System.out.println("  4th Iteration : ");
        // solution.addNum(3);  
        // System.out.println("  5th Iteration : " + solution.findMedian());  // Output: ]2.0
        
    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Find the median here.. 
 * 2. median is the mean of two middle value
 * 3. Example 
 *      [1, 2, 3] then median is 2
 *      [2, 3] then median is (2 + 3) / 2 = 2.5
 * 
 * Pattern :
 * 
 * First thought in my mind 
 * 
 * 1. Globally declare variable
 *      - List<Integer> arr
 * 1. medianFinder()
 *      - assign value to globally declare array variable
 * 2. addNum(int num) 
 *      - arr.add(num)
 *      - sort arr -> to find median we always sort array
 * 3. findMedian() 
 *      - if(arr.length == 1) -> return arr[0]
 *      - if(arr.length == 2) -> (arr[0] + arr[1]) / 2
 *      - if even length then : ( (arr.length / 2) + ((arr.length / 2) - 1) ) / 2 
 *      - if odd length then : arr.length / 2
 * 
 * Need to use Two heap as it specifically said in topic so
 * 
 * 1. will create 2 heaps
 *      - maxHeap -> it will return maximum num first
 *      - minHeap -> it will return minimum num first
 * 2. medianFinder() 
 *      - assign value here
 * 3. addNum(int num)
 *      - add nums in both heaps
 * 4. findMedian()
 *      - (minHeap.peek() + maxHeap.peek()) / 2
 * 
 * 
 * Understand the code written :
 * 
 * //? check out this video to understand better
 * https://www.youtube.com/watch?v=jnj87BSi9Is&ab_channel=codestorywithMIK
 * 
 * 1. why use mean and max heap and why gave that value only?..
 * 
 * 2. take example [3, 4, 5, 8, 9, 10] this is a sorted even length array now to check median will only need 5 and 8 here
 * -> how to get 5 or 8 easily?
 *      - jr me maxHeap use kela and tyat left side che nums store kele till 5 tr mala easy jail 5 pop out karayla? 
 *              maxHeap = [5, 4, 3]
 *      - jr me minHeap use kela and tyat Right side che nums store kele from 8 to 10 tr mala easy jail 8 pop out karayla?
 *              minHeap = [8, 9, 10]
 * 
 * 3. take example [3, 4, 5, 9, 10] this is a sorted odd length array now to check median will only need 5
 * -> how to get 5 easily?
 *      - jr odd number length ahe tr ek queue madhe jast elements astil ani eka yachya kami right?
 *          like ekat 3 element ahe ekat 2
 *      - let's assume ki maxHeap madhe ek element jast asel nehmi
 *          like maxHeap = [5, 4, 3] and minHeap = [9, 10]
 *      - so ata odd madhe kay karu?.. left side cha heap means maxHeap cha top element return karu
 *      
 * 4. take one final example with data streame
 *  -> [0, 1, 3, 4, 5, 8, 9]
 * 
 *      step 1: [0]
 *              maxHeap = [0]  minHeap = []
 * 
 *      step 2: [1]  -> 0 < 1
 *              maxHeap = [0]  minHeap = [1]
 * 
 *      step 3: [3]  -> 0 < 3 so minHeap madhe 3 add kel
 *              maxHeap = [0]  minHeap = [1, 3]
 *         but apan bagh kay mhnt hoto ki maxHeap vala motha thevnar apa right?.. remember that second example?
 *         tyamul kay kel minHeap cha top vapas maxHeap madhe takla 
 *              maxHeap = [1, 0]  minHeap = [3]
 * 
 *      step 4: [4]  -> 1 < 4 
 *              maxHeap = [1, 0]  minHeap = [3, 4]
 * 
 *      step 5: [5]  -> 1 < 5 
 *              maxHeap = [1, 0]  minHeap = [3, 4, 5]
 *          maxHeap chi size minHeap pekshya choti ahe so apan parat add karu minHeap cha top to maxHeap
 *              maxHeap = [3, 1, 0]  minHeap = [4, 5]
 * 
 *      step 6: [8]  -> 3 < 8 
 *              maxHeap = [3, 1, 0]  minHeap = [4, 5, 8]
 * 
 *      step 7: [9]  -> 3 < 9
 *              maxHeap = [3, 1, 0]  minHeap = [4, 5, 8, 9]
 *          maxHeap chi size minHeap pekshya choti ahe so apan parat add karu minHeap cha top to maxHeap
 *              maxHeap = [4, 3, 1, 0]  minHeap = [5, 8, 9]
 * 
 * 
 * 
 * 
 */