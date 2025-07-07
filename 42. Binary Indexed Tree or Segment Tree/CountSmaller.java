import java.util.*;

public class CountSmaller {
    
    // Driver Function 
    public List<Integer> countSmaller(int[] nums) {
        
        // Declare variables
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        // Loop through each element in the array
        for (int i = 0; i < n; i++) {
            int count = 0;

            // Check all elements to the right of i
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }

            // Add count to result
            result.add(count);
        }

        return result;
    }


    public static void main(String[] args) {
    
        CountSmaller solution = new CountSmaller();

        int[] nums1 = {5,2,6,1};
        System.out.println(" Result 1 -> " + solution.countSmaller(nums1) + "\n");  // [2,1,1,0]

        int[] nums2 = {-1};
        System.out.println(" Result 2 -> " + solution.countSmaller(nums2) + "\n");  // [0]

        int[] nums3 = {-1, -1};
        System.out.println(" Result 3 -> " + solution.countSmaller(nums3) + "\n");  // [0,0]

    }

}

/*
 * Intuitions :
 
    1. we have given an array nums
    2. we need to return an integer array counts
        where count[i] is the number of smaller elements to the right of nums[i]

 
 * Pattern :
 
    1. Brute force :
        - use for loop (i = 0 to n) and (j = i to n)
        - check if nums[i] > nums[j]
            then increase count 
        - after end of j loop when we are going to start next i 
            will add that count in count[i]

    2. Problem with above approach is I need to check all number each time
        - mala asa kahitri havay jyani mala fact ekdach check karava lagel num
        - and will wrote there konapekshya chota ahe te

    3. I did try to submit brute force approach
        but 62/66 test cases are passed..
        63rd gives me TLE
 


    ^ Improvement 

    1. What I think 
        - apan attaparynt buildSegmentTree madhe mid find karun then left right check karun value add karat hoto segTree[curr] amdhe
        - this que says mala curr chya right side la jitke nums ahet tyatun min count havay
            so ranges will be :
                [0, n]
                [1, n]
                [2, n]
                till [n, n]
                
        - maza segmentTree madhe ata range hi asel and tyat me count store karel 
        - buildSegementTreeMin :
            - Base Case :
                if start == end asel tr apan numsArray chya currElement vr ahe 
                aplyala tevha 0 return karava lagel karan sadhya mincount tr 0 ch ahe na 

            - declare a count variable 
            - check karu ki currIndex vrcha 

~ I tried to work on this problem but not able to solve nor I understood the method 

 
 * Pseudo Code :
 
    1. Brute Force :

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        -> Loop through each element in the array
            for (int i = 0; i < n; i++) {
                int count = 0;

            -> Check all elements to the right of i
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] < nums[i]) {
                        count++;
                }
            }

            -> Add count to result
                result.add(count);
        }   

        return result
    
    }



 */