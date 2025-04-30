import java.util.*;

public class KthSmallest {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. a matrix is given of length (m x n) where rows nd col are sorted in ascending order
 * 2. we need kth smallest element in the sorted order
 * 
 * 
 * Pattern :
 * 
 * 1. we need to make a single array from that 2D matrix..
 *      - assign a flatMatrix List<Integer>
 *      - use for loop for matrix
 *      - add all nums in that flatMatrix
 * 2. then check for smallest kth element
 * 3. use maxHeap to store elements one by one
 * 4. if that maxHeap size incresed from k pop out the top element
 * 5. and at the end return that top element
 * 
 * approach 
 * 
 * 1. use binary search by which we don't have to travel whole array
 *      - just check if midIndex is smaller or greater than k 
 *      - if midIndex is smaller than k (3 < 8) -> we need to check right side from mid
 *      - if midIndex is greater than k (10 > 8) -> we need to check left side from mid
 * 
 * 2. Use maxHeap
 *      - use for loop to store nums one by one in maxHeap
 *      - when that heap size get's greater than k pop out top element
 *      - after ending of loop poll() the top element which is the kth element
 * 
 * Pseudo code :
 * 
 * Binary Search 
 * 
 * kthSmallest;
 * 
 * function kthSmallest(matrix, k){
 *      
 *      kthSmallest = 0;
 * 
 *      flatMatrix = new Array
 *      
 *      -> Store nums of matrix row by row in flatMatrix Array
 *      for(int[] row : matrix)
 *          for(num : row)
 *              flatMatrix.add(num)
 * 
 *      binarySearch(0, flatMatrix.size, flatMatrix)
 *      
 *      return kthSmallest;
 * }
 * 
 * 
 * function binarySearch(left, right, flatMatrix) {
 *      
 *      while(left < right)
 * 
 *          mid = (left + right) / 2
 *  
 *          if(mid == k-1) -> 
 *              kthSmallest = flatMatrix.get(mid) 
 *              return 
 * 
 *          if(mid < k-1) -> then k is on right side of that mid
 *              left = mid + 1
 *          
 *          if(mid > k-1) -> then k is on left side of that mid
 *              right = mid - 1
 * 
 *      return
 * } 
 *      
 *      
 * 
 * 
 */