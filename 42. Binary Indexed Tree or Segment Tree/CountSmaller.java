import java.util.*;

public class CountSmaller {
    
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

  
 
 
 * Pseudo Code :
 




 */