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
        int minIndex;                   // to get Min Index
        System.out.println("Start: " + start + " , End: " + end + "\n");

        int peak = findPeakElement(mountainArr, end);
        System.out.println("Peak: " + peak + "\n");

        int leftSideIndex = searchInHalf(mountainArr, target, start, peak);
        System.out.println("Left Index: " + leftSideIndex + "\n");

        int rightSideIndex = searchInHalf(mountainArr, target, peak + 1, end);
        System.out.println("Right Index: " + rightSideIndex + "\n");

        if (leftSideIndex == -1 && rightSideIndex == -1) {
            System.out.println("Target is not present in array...");
            return -1; 
        }
        else if(leftSideIndex != -1 && rightSideIndex == -1) {
            System.out.println("Target is found on Left Side...");
            return leftSideIndex;
        }
        else if(leftSideIndex == -1 && rightSideIndex != -1){
            System.out.println("Target is found on Right Side...");
            return rightSideIndex;
        }
        else{
            minIndex = Math.min(leftSideIndex, rightSideIndex);
            System.out.println("Min Index: " + minIndex);
        }

        return minIndex;
    }

    // Helper function to find the peak element in the mountain array.
    private int findPeakElement(MountainArray mountainArr, int end){

        int low = 0;
        int high = end;
        System.out.println("[findPeakElement] Initial Values | low: " + low + " , high: " + high);

        while (low < high) {
            
            int mid = (low + high) / 2;
            System.out.println("[findPeakElement] mid: " + mid);

            if(mountainArr.get(mid) < mountainArr.get(mid + 1)){
                low = mid + 1;
                System.out.println("[findPeakElement] [IF] low: " + low);
            }
            else{
                high = mid;
                System.out.println("[findPeakElement] [ELSE] high: " + high);
            }
        }

        System.out.println("[findPeakElement] End Values | low: " + low + " , high: " + high);
        return low;
    }

    // Binary search helper function to find the target in one half of the array.
    private int searchInHalf(MountainArray mountainArr, int target, int left, int right){

        System.out.println("[searchInHalf] Initial Values | target : " + target + " , left: " + left + " , right: " + right);

        while (left <= right) {
            
            int mid = (left + right) / 2;
            System.out.println("[searchInHalf] mid: " + mid);

            // If target found return the index
            if (mountainArr.get(mid) == target) {
                System.out.println("[searchInHalf] [IF] right: " + right);
                return mid;
            }
            else if( mountainArr.get(mid) > target){
                right = mid - 1;
                System.out.println("[searchInHalf] [IF] right: " + right);
            }
            else{
                left = mid + 1;
                System.out.println("[searchInHalf] [ELSE] left: " + left);
            }
        }

        System.out.println("[searchInHalf] End Values | left: " + left + " , right: " + right);
        return -1;
    }

    public static void main(String[] args){
        FindInMountainArray solution = new FindInMountainArray();

        int[] arr1 = {1,2,3,4,5,3,1};
        int[] arr2 = {0,1,2,1};
        int[] arr3 = {1,5,2};

        // Wrap arr into MountainArray 
        MountainArray mountainArr1 = new MountainArrayImpl(arr1);
        MountainArray mountainArr2 = new MountainArrayImpl(arr2);
        MountainArray mountainArr3 = new MountainArrayImpl(arr3);
        
        int target = 2;

        System.out.println("Result1 : " + solution.findInMountainArray(target, mountainArr1) + "\n \n");
        System.out.println("Result2 : " + solution.findInMountainArray(target, mountainArr2) + "\n \n");
        System.out.println("Result3 : " + solution.findInMountainArray(target, mountainArr3));
        
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
