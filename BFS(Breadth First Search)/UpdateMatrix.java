import java.util.*;

public class UpdateMatrix {
    
}

/*
  
 * Intuitions :
 
    1. We have given a binary matrix with size m x n
    2. return the distance of nearest 0 for each cell
    3. 
 
 * Pattern :
 
    1. BFS is pattern like below
        - we need queue
        - add initial value in queue
        - will check if queue.isEmpty
        - then use for loop ( dir : matrixDirection)
        - use base case if 
        - do actual changes and add that cell to queue
        - return the result

    2. let's think what we need to store in queue?
        - will store all zeoros in the queue
        - Initially store all cells with value zero in queue
        - then start while loop till !queue.isEmpaty
        - pop out the top cell
        - check it's directions (BRUL)
            if u get any unvisited cell 
            visit it and add it in queue
            also replace it's num to topcell[i][j] + 1
        - at the end return matrix

    3. why replace it's num to topcell[i][j] + 1 ?
        
        - let's take this as an example :
                0   1   1   1   1
                1   1   1   0   1
                1   0   1   1   1
                1   1   1   1   0

        - so at first our queue will be : [[0,0], [1,3], [2,1], [3,4]]
        - now we pop out [0,0]
            check unvisited cells near that poppedCell 
            add that unvisited cell in queue
            also changed that cell [1,0] from 1 to mat[0,0] (0) + 1 = 1 
        - after checking BRUL of [0,0] queue is : [[1,3], [2,1], [3,4], [1,0], [0,1]]
        - now we pop out [1,3]
            check unvisited cells near that poppedCell
            add that unisted cell in queue
            also changed that cell (BRUL cells) from 1 to mat[1,3] (0) + 1 = 1
        
        - like that matrix will look like
                0   1   2   1   2
                1   1   1   0   1
                1   0   1   1   1
                2   1   2   1   0



 * Pseudo Code :
 

 
 */