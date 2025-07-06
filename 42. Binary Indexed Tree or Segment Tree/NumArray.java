import java.util.*;

public class NumArray {
   
    // Globally Declare variable
    int[] numsArray;
    int n;
    int[] segmentTree;

    // Driver Function : to Initialize Object 
    public NumArray(int[] nums) {

        // Assign value here
        numsArray = nums;
        n = nums.length;
        segmentTree = new int[2*n];

        // build segmentTree
        buildSegmentTree(0, 0, n-1);

    }

    // Driver Function : to update value 
    public void update(int index, int val) {

        // call recursion to update values at index
        updatedSegmentTree(0, 0, n-1, index, val);

        return;
    }

    // Driver Function : to get sum for range (left, right)
    public int sumRange(int left, int right) {

        return 0;
    }

    // Recursion Function : to build segmentTree 
    private void buildSegmentTree (int nodeIndex, int start, int end) {

        // Base Case : if we hit leaf node
        if (start == end) {
            
            segmentTree[nodeIndex] = numsArray[start];
            
            System.out.println("    - [buildSegmentTree] We Hit leafNode : " + start);
            return;
        }


        // Find Mid
        int mid = (start + end) / 2;

        // get leftIndex and rightIndex of currNode
        int leftIndex = 2 * nodeIndex + 1;
        int rightIndex = 2 * nodeIndex + 2;

        // Call recursion and get values of right and left subtree
        buildSegmentTree(leftIndex, start, mid);
        buildSegmentTree(rightIndex, mid + 1, end);

        // Add both left and right side values and store it in segmentTree
        segmentTree[nodeIndex] = segmentTree[leftIndex] + segmentTree[rightIndex];

        System.out.println("    - [buildSegmentTree] Segment Tree at node(" + nodeIndex + ") : " + Arrays.toString(segmentTree));
        return;
    }

    // Recursion Function : to updated value in segmentTree 
    private void updatedSegmentTree (int nodeIndex, int start, int end, int index, int val) {

        // Base Case : if we hit leaf node
        if (start == end) {
            
            numsArray[index] = val;
            segmentTree[nodeIndex] = val;
            
            System.out.println("    - [updatedSegmentTree] We Hit leafNode : " + start);
            return;
        }


        // Find Mid
        int mid = (start + end) / 2;

        // get leftIndex and rightIndex of currNode
        int leftIndex = 2 * nodeIndex + 1;
        int rightIndex = 2 * nodeIndex + 2;

        // Check if index is at left side or right side of root
        if (index <= mid) {
            
            // Call recursion and get values of right subtree
            updatedSegmentTree(leftIndex, start, mid, index, val);
        }
        else {

            // Call recursion and get values of right subtree
            updatedSegmentTree(rightIndex, mid + 1, end, index, val);
        }
        

        // Add both left and right side values and store it in segmentTree
        segmentTree[nodeIndex] = segmentTree[leftIndex] + segmentTree[rightIndex];

        System.out.println("    - [updatedSegmentTree] Segment Tree at node(" + nodeIndex + ") : " + Arrays.toString(segmentTree));
        System.out.println("    - [updatedSegmentTree] Nums Array : " + Arrays.toString(numsArray));
        return;
    }

    


    public static void main(String[] args) {
        
        // Initialize the array
        System.out.println(" Initialize Object : ");
        NumArray solution = new NumArray(new int[]{1, 3, 5});
        
        // First query
        System.out.println("\n Iteration 1 : ");
        System.out.println(solution.sumRange(0, 2)); // Output: 9
        
        // Second query
        System.out.println("\n Iteration 2 : ");
        solution.update(1, 2);  // Now the array becomes [1, 2, 5]
        
        // Third query
        System.out.println("\n Iteration 3 : ");
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

    3. querySegmentTree :
        - in que we hvae given a range and need to find sum between those index only
        - so me direct buildSegment function tr nahi use karu shakat
        - karan tyat whole (0, n-1) paryant chya range madhlya nums chi value ahe
        - will maybe optimized it with checking if we already visited that range or not
            if yes then return that 

        but for nw let's write whole new recursion function :
            - Base Case :
                jr curr start and end are out of bound, given range pekshya
                    then will return 0

                jr start and end range chya madhe or equal ahet
                    then will return segTree[nodeIndex]
                    - Why left <= start && end <= right?
                        It means:
                            Your query box ([left, right]) is BIGGER than the segment box ([start, end]).
                        Example:
                            You want [2,5] (query).
                            The segment box is [3,4].
                            Since [3,4] fits entirely inside [2,5], you use its pre-written sum (12).

            - Find Mid
            - Get left and right index for array
			- call recursion function to get leftSubTreeSum and rightSubTreeSum
            - add them 
            - return that sum


        querySegmentTree (index, start, end, left, right) {
        
            if(left > end || right < left) 
                return 0

            if(left <= start && right >= end)
                return segment[nodeIndex]

            mid = (left + right) / 2
		
            leftIndex = 2 * index + 1
            rightIndex = 2 * index + 2
            
		    leftSum = querySegmentTree(leftIndex, start, mid, left, right)
		    rightSum = querySegmentTree(rightIndex, mid + 1, end, left, right)
		
		    currSum = leftSum + rightSum

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