import java.util.*;

public class FindInMountainArray {

    // Interface for MountainArray
    interface MountainArray {
        public int get(int index);
        public int length();
    }

    /*
    * Class to implements MountainArray
        - Static class so it can be used without needing an instance of FindInMountainArray 
        or write this class outside of FindInMountainArray class it will work 
    */
    static class MountainArrayImpl implements MountainArray {

        private int[] arr;  // Array to store the mountain array values

        // Constructor to initialize the mountain array
        public MountainArrayImpl(int[] arr) {
            this.arr = arr;
        }

        // Returns the element at the given index
        public int get(int index) {
            return arr[index];
        }
        
        // Returns the length of the array
        public int length() {
            return arr.length;
        }
    }
    
    // Function to find the minimum index of target element in the mountain array.
    public int findInMountainArray(int target, MountainArray mountainArr) {
        
        int n = mountainArr.length();   // length of arr
        int start = 0;                  // first index of arr
        int end = n - 1;                // last index of arr
        System.out.println("Start: " + start + " , End: " + end);

        int peak = findPeakElement(mountainArr, end);
        System.out.println("Peak: " + peak);

        int leftSideIndex = searchInHalf(start, peak);
        int rightSideIndex = searchInHalf(peak, end);
        System.out.println("Left Side Index: " + leftSideIndex + " , Right Side Index: " + rightSideIndex);

        int minIndex = Math.min(leftSideIndex, rightSideIndex);
        System.out.println("Min Index: " + minIndex);

        return minIndex;
    }

    // Helper function to find the peak element in the mountain array.
    private int findPeakElement(MountainArray mountainArr, int end){

        return end;
    }

    // Binary search helper function to find the target in one half of the array.
    private int searchInHalf(int left, int right){

        return left;
    }

    public static void main(String[] args){
        FindInMountainArray solution = new FindInMountainArray();

        int[] arr1 = {1,2,3,4,5,3,1};
        int[] arr2 = {0,1,2,4,2,1};

        // Wrap arr into MountainArray 
        MountainArray mountainArr1 = new MountainArrayImpl(arr1);
        MountainArray mountainArr2 = new MountainArrayImpl(arr2);
        
        int target = 3;

        System.out.println("Result1 : " + solution.findInMountainArray(target, mountainArr1) + "\n");
        System.out.println("Result2 : " + solution.findInMountainArray(target, mountainArr2));
        
    }

}

/*
 * 
 * Intuitions :
 * 
 * We have given a mountainArray
 * 1. The values strictly increase up to a peak element.
 * 2. After the peak, values strictly decrease.
 * 3. we have to find the minimum index where arr[i] == target.
 * 4. We have to use mountainArray.get(index) return element at that specific index
 * 5. We have to use mountainArray.length() to get the length of the array.
 * 6. We have to use binary search to find the target in the mountain array.
 * 7. We need to create Interface which is done with the help of gpt a little.. 
 * 
 * Pattern :
 * 
 * 1. array has shape like a mountain 
 * - left side - increasing nums
 * - right side - decreasing nums
 * - peak - highest num
 * 2. So target might be before peak (left side) or after peak (right side) or peak itself
 * 3. We have to find the peak first
 * 4. Will use Binary search 3 times
 * - one for getting peak where low and high is there and mid is compared to mid + 1
 * - two for getting index of min from both half
 * 5. to get min from both half will use recurrsion function searchInHalf(left, right)
 * - for leftSide Index -> 0, peak
 * - for rightSide Index -> peak, end
 * 6. return smallest index from leftSideIndex and rightSideIndex
 * 
 * 
 * Pseudo Code :
 * 
 * function findInMountainArray(target, mountainArray){
 *      
 *      end = arr.length - 1
 *      
 *      peak = findPeakElement(mountainarr, end)
 * 
 *      leftSideIndex = searchInHalf(0, peak)
 *      rightSideIndex = searchInHalf(peak, end)
 *
 *      int minIndex = min(leftSide, rightSide)
 *      
 *      return minIndex
 * }
 * 
 * function findPeakElement(mountainarr, end){
 *      
 *      low = 0
 *      high = end
 * 
 *      while (low < high){
 *      
 *          mid = (low + high) / 2
 *          
 *          if( arr[mid] < arr[mid + 1] ){
 *              low = mid + 1    
 *          }
 *          else {
 *              high = mid
 *          }
 *      }
 *  
 *      return low
 * }
 * 
 * function searchInHalf(left, right){
 *      
 *      while(left < right){
 *          
 *          mid = (left + right) / 2
 *          
 *          if(arr[mid] >= arr[right]){
 *              left = mid + 1
 *          }
 *          else {
 *              right = mid
 *          }
 *          
 *      }
 *      
 *      return left;
 * }
 * 
 * 
 */
