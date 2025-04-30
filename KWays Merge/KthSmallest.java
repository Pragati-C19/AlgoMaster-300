import java.util.*;

public class KthSmallest {
    
    //Globally Declare variable
    int kthSmallest;

    // Driver Function 
    public int kthSmallest(int[][] matrix, int k) {

        List<Integer> flatMatrix = new ArrayList<>();

        for (int[] row : matrix) {
            for (int num : row) {
                flatMatrix.add(num);
            }
        }
        System.out.println("flatMatrix : " + flatMatrix);

        Collections.sort(flatMatrix);

        int n = flatMatrix.size();

        // sending k - 1 here bcoz array is 0-th index 
        binarySearch(0, n, flatMatrix, k-1);

        return kthSmallest;

    }

    // Helper Function
    private void binarySearch(int left, int right, List<Integer> flatMatrix, int k){

        while (left <= right) {
            
            int midIndex = (left + right) / 2;
            System.out.println("  mid = " + midIndex);
            
            if (midIndex == k) {

                kthSmallest = flatMatrix.get(midIndex);
                System.out.println("  kth Smallest is found : " + kthSmallest);
                return;
            }
            else if (midIndex < k) {
                
                left = midIndex + 1;
                System.out.println("    -> k is at right side of that mid...");
            }
            else {

                right = midIndex - 1;
                System.out.println("    -> k is at left side of that mid...");
            }
        }

        return;
    }

    public static void main(String[] args){

        KthSmallest solution = new KthSmallest();

        // int[][] matrix1 = {
        //     {1,5,9},
        //     {10,11,13},
        //     {12,13,15}
        // };
        // int k1 = 8;
        // System.out.println("Result1 -> " + solution.kthSmallest(matrix1, k1) + "\n");

        // int[][] matrix2 = {
        //     {-5},
        // };
        // int k2 = 1;
        // System.out.println("Result2 -> " + solution.kthSmallest(matrix2, k2) + "\n");

        int[][] matrix3 = {
            {1,2},
            {1,3}
        };
        int k3 = 2;
        System.out.println("Result3 -> " + solution.kthSmallest(matrix3, k3) + "\n");

    }

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
 * 3. using for loop
 *      - I didn't do anything
 *      - I mean I just flatten the matrix to 1D
 *      - store values in List<Integer>
 *      - and sorted that array
 *      - and return the k-1 index's value..
 *      - why k-1? bcoz the array was 0-th indexed and k was 1-th indexed
 * 
 * 
 * Pseudo code :
 * 
 * 1. Binary Search 
 * 
 * // Globally Declare 
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
 * 2. only For Loop
 * 
 * public int kthSmallest(int[][] matrix, int k) {
 * 
 *         List<Integer> flatMatrix = new ArrayList<>();
 * 
 *         for (int[] row : matrix) {
 *             for (int num : row) {
 *                 flatMatrix.add(num);
 *             }
 *         }
 *         
 *         int n = flatMatrix.size();
 * 
 *         // to sort List<Intger> we use this
 *         Collections.sort(flatMatrix);
 * 
 *         return flatMatrix.get(k - 1); 
 * 
 * }
 * 
 */