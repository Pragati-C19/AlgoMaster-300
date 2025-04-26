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
        minHeap.add(num);
        maxHeap.add(num);
    }
    
    public double findMedian() {
        
        double median = (minHeap.peek() + maxHeap.peek()) / 2.0;
        return median;
    }

    public static void main (String[] args) {

        MedianFinder solution = new MedianFinder();

        System.out.println("Final Result : ");
        System.out.println("  1st Iteration : ");
        solution.addNum(1);  
        System.out.println("  2nd Iteration : ");  
        solution.addNum(2);
        System.out.println("  3rd Iteration : " + solution.findMedian());  // Output: 1.5
        System.out.println("  4th Iteration : ");
        solution.addNum(3);  
        System.out.println("  5th Iteration : " + solution.findMedian());  // Output: ]2.0
        
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
 * 3. findMedian() 
 *      - if(arr.length == 1) -> return arr[0]
 *      - if(arr.length == 2) -> (arr[0] + arr[1]) / 2
 *      - Use Binary search to find median
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
 * Pseudo Code :
 * 
 * 
 * 
 */