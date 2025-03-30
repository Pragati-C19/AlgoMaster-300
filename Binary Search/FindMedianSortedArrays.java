import java.util.*;

public class FindMedianSortedArrays {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        System.out.println("[findMedianSortedArrays] n1: " + n1 + " , n2: " + n2 );
        
        int[] mergedArr = mergedArrays(nums1, n1, nums2, n2);
        System.out.println("Merged Array: " + Arrays.toString(mergedArr) + "\n");

        int left = 0;
        int right = mergedArr.length - 1;
        System.out.println("Length of Merged Array: " + right);

        // 2.0 is a double, so Java promotes the entire expression to floating-point division.
        int mid = (left + right) / 2;
        System.out.println("Mid: " + mid);

        double median = mergedArr[mid];
        System.out.println("Median of nums: " + median);

        return median;
    }

    private int[] mergedArrays(int[] nums1, int n1, int[] nums2, int n2){
        
        int[] mergedArr = new int[n1 + n2];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                mergedArr[k] = nums1[i];
                k++;
                i++;
        
                System.out.println("[mergedArrays] Merged Array: " + Arrays.toString(mergedArr));
                System.out.println("[mergedArrays | IF] i: " + i + " , j: " + j + " , k: " + k);
            }
            else{
                mergedArr[k] = nums2[j];
                k++;
                j++;
                System.out.println("[mergedArrays] Merged Array: " + Arrays.toString(mergedArr));
                System.out.println("[mergedArrays | ELSE] i: " + i + " , j: " + j + " , k: " + k);
            }
        }

        while (i < n1) {
            mergedArr[k] = nums1[i];
            k++;
            i++;
        }

        while (j < n2) {
            mergedArr[k] = nums2[j];
            k++;
            j++;
        }

        return mergedArr;
    }

    public static void main(String[] args){

        FindMedianSortedArrays solution = new FindMedianSortedArrays();

        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Median1: " + solution.findMedianSortedArrays(nums1, nums2) + "\n");

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Median2: " + solution.findMedianSortedArrays(nums3, nums4) + "\n");

        int[] nums5 = {2, 2, 4, 4};
        int[] nums6 = {2, 2, 2, 4, 4};
        System.out.println("Median3: " + solution.findMedianSortedArrays(nums5, nums6));

    }

}


/*
 * 
 * Intuitions : 
 * 
 * 1. We need to merge those two array
 * 2. Create a whole array then find the middle of it
 * 3. to merging both array we can use divide and conquer
 * 4. to get mid from that merge array we can use below thing
 * - sum of all nums from array / n 
 * - left = 0, right = n-1, mid = (left + right) / 2
 * 
 * 
 * How To merge both array and create a new array?
 * Where to use Binary search in it?
 * 
 * 
 * Pattern : 
 * 
 * 1. let's use normal method first brute force to merge both arrays
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 1. merge two array with brute force
 * 
 * function int[] mergeArr(nums1, nums2, n){
 *      
 *      int[] mergeArr = new int[n];
 *      int i = 0, j = 0, k = 0
 * 
 *      while(i < n1 || j < n2) {
 *          if(nums1[i] < nums2[j]){
 *              mergeArr[k] = nums1[i]
 *              k++;
 *              i++; 
 *          }
 *          else {
 *              mergeArr[k] = nums2[j]
 *              k++;
 *              j++;
 *          }
 *      }
 *      
 *      return mergeArr[]
 * }
 * 
 * function findMedianSoertedArrays(nums1, nums2) {
 *      
 *      int n1 = nums1.length
 *      int n2 = nums2.length
 *      int sum = 0
 *      int n = n1 + n2
 *      
 *      int[] mergedArr = mergeArr(nums1, nums2, n)
 *      
 *      while(int i < n){
 *          sum = sum + mergedArr[i]
 *      }
 *      
 *      double median = sum / n
 * 
 *      return median;
 * }
 * 
 */
