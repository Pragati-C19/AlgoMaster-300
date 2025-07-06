import java.util.*;

public class NumArray {
   
    // Globally Declare variable
    private int[] numsArray;

    public NumArray(int[] nums) {

        // Assign value here
        numsArray = nums;
    }

    public void update(int index, int val) {

        // update value of index 
        numsArray[index] = val;
    }

    public int sumRange(int left, int right) {

        // initialize sum as 0
        int sum = 0;

        // check all nums in range [left, right]
        for (int i = left; i <= right; i++) {

            // add that num in the sum
            sum += numsArray[i];
        }
        
        return sum;
    }


    public static void main(String[] args) {
        
        // Initialize the array
        NumArray solution = new NumArray(new int[]{1, 3, 5});
        
        // First query
        System.out.println(solution.sumRange(0, 2)); // Output: 9
        
        // Second query
        solution.update(1, 2);  // Now the array becomes [1, 2, 5]
        
        // Third query
        System.out.println(solution.sumRange(0, 2)); // Output: 8
    }

}

/*
 * Intuitions :
 
    1. we have given a array nums and we need to handle few queries like
        - update the value of an element in nums
        - calculate the sum of elements of nums between [left, right]
    2. Functions we want to right
        - NumArray(int[] nums) : Initializes the object with the integer array nums.
        - void update(int index, int val) Updates the value of nums[index] to be val
        - int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 
 
 * Pattern :
 
    1. doing initalize and update is easy 
        - to initialize    
            we need to just declare nums array globally and it's length 
        - to update
            we just need to do nums[ndex] = val
        - I think for sumRange 
            we can use for loop (i = left to right)
            and then add nums[i] in sum

    2. But what I'm thinking is just brute force it will give me TLE.. and I need to change my whole approach
        it will not that easy right?

        let's try to use directly on leetcode
 
    3. I tried to understnad the optimized way of segment tree but I don't undertsnad the logic behind it
        it all feels confusing will check later again


    ^ Let's Check Optimal solution :

    1. buildSegementTree 
        - Base Case :
			Jevha start and end same yeil will stop 
        - Find mid 
		- Get left and right index for array
		- Call recursion and get value for leftsubtree and rightsubtree
		- We are doing range sum problems as example to understand the Segment Tree currently so, 
			we need sum 
			and for that will add value at leftSubTree + rightSubTree
			in terms of segmentTreeArray 
				segTree[i] = segTree[leftIndex] + segTree[rightIndex]
				
		- Then will store it in segmentTree
    
        buildSegmentTree (index, start, end) {
        
            if(start == end) 
                segTree[nodeIndex] = numsArray[start]

            mid = (left + right) / 2
		
            leftIndex = 2 * index + 1
            rightIndex = 2 * index + 2
            
		    buildSegmentTree(leftIndex, start, mid)
		    buildSegmentTree(rightIndex, mid + 1, end)
		
		    segTree[i] = segTree[leftIndex] + segTree[rightIndex]

        }


    2. Update Query :

        - Base Case 
			If start == end 
				- aplyala apla index bhetla asel so we need to update that value in nums
					numsArray[index] = val
					
				- and aplyala segmentTree madhe pn value update karavi lagel
					segTree[nodeIndex] = val
						
		- Find Mid
		- Get left and right index for array
			leftSide = 2* nodeIndex + 1
			rightSide = 2* nodeIndex + 2
			
		- Check if index <= mid
			Yes : call recursion on left side
				 dfs (leftIndex, start, mid, index, val)
			No  : call recursion on right side
				 dfs (rightIndex, mid+1, end, index, val)
				
		- Now update value of root 
			segTree[i] = segTree[leftIndex] + segTree[rightIndex]
				
				
		updatedSegmentTree (nodeIndex, start, end, index, val) {
		    
		    if(start == end) 
			    numsArray[index] = val
			    segTree[nodeIndex] = val

			mid = (start + end) / 2
		
		    leftIndex = 2 * nodeIndex + 1
		    rightIndex = 2 * nodeIndex + 2
		    
			if(index <= mid) 
			    updatedSegmentTree (leftIndex, start, mid, index, val)

			else 
			    updatedSegmentTree (rightIndex, mid + 1, end, index, val)
		
		    segTree[nodeIndex] = segTree[leftIndex] + segTree[rightIndex]
		    
		}


 
 * Pseudo Code :
 
    1. Brute Force :

        - all 15/16 test cases are passed
        - just last 16th one had TLE

    class NumArray {

        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int index, int val) {
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += nums[i];
            }
            return sum;
        }

    }

 */