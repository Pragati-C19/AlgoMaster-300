import java.util.*;

public class FindInMountainArray {

    // Interface for MountainArray
    interface MountainArray {
        public int get(int index);
        public int length();
    }


    // Class to implements MountainArray
    //? Static class so it can be used without needing an instance of FindInMountainArray or right this class outside of FindInMountainArray class it will work 
    
    static class MountainArrayImpl implements MountainArray {

        private int[] arr;

        public MountainArrayImpl(int[] arr) {
            this.arr = arr;
        }

        // Given in Leetcode already
        public int get(int index) {
            return arr[index];
        }
        
        public int length() {
            return arr.length;
        }
    }
    
    public int findInMountainArray(int target, MountainArray mountainArr) {
        
        return target;
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
 *      peak = low;
 *      
 *      leftSideIndex = searchInHalf(0, peak)
 *      rightSideIndex = searchInHalf(peak, end)
 *
 *      int minIndex = min(leftSide, rightSide)
 *      
 *      return minIndex
 * }
 * 
 * 
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
