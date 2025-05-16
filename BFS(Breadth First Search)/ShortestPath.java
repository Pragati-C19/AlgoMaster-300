import java.util.*;

public class ShortestPath {
    
}

/*
 ^ took help from video to understand ho to do below thing
    use for loop to check other BRUL direction of that popout cells
            define x, y
            if(Base Case && visited != true)

                todo: How to destroy obstacles and how to manage it I'm not sure
                queue.add(x, y)
                visited[x][y] = true

    https://www.youtube.com/watch?v=zpOhlTndBv0&ab_channel=codestorywithMIK


    
 * Intuitions :

    1. we have given a matrix with size m x n
    2. it has 1's (obstacles) and 0's (empty)
    3. we can move BRUL from any empty cell to any other empty cell by one step
    4. return the minimum number of steps to walk from the upper left (0,0) to lower right(m-1, n-1)
    5. you can remove k obstacles 
    6. if it's not possible to find walk then return -1


 * Pattern :

    1. we are using BFS
    2. create a queue and initially store (0, 0) in it
    3. mark visited that (0, 0) cell 
    4. use while loop till queue is not empty
        - check out queue's size and the create a for loop to check level by level
        - pop out the top cell from queue
        - check if that cell == (m-1, n-1)
            yes : return path
        - use for loop to check other BRUL direction of that popout cells
            define x, y
            if(Base Case && visited != true)

                todo: How to destroy obstacles and how to manage it I'm not sure
                queue.add(x, y)
                visited[x][y] = true
        - path++
    5. return path


 * Pseudo Code :






 */