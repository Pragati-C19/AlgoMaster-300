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

 Appraoch 1:
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

                ? How to destroy obstacles and how to manage it I'm not sure
                queue.add(x, y)
                visited[x][y] = true
        - path++
    5. return path


 Appraoch 2:
    
    1. we can visit any cell with different k_remain (obstacles remain) value
    2. see in below example we have given a matrix and gave k = 1
        - to visit (1, 2) from (0, 0) we can go with 2 paths 
        - path 1 : (0, 0) -> (0, 1) -> (0, 2) -> (1, 2) : k_remain = 1
        - path 2 : (0, 0) -> (0, 1) -> (1, 1) (obstacle) -> (1, 2) : k_remain = 0
               
                0 -> 0 -> 0                 0 -> 0    0
                          |                      |
                1    1    0                 1    1 -> 0
                0    0    0                 0    0    0
                0    1    1                 0    1    1
                0    0    0                 0    0    0

                   path1                       path2

        - if u see there is one more obstacle at (3, 2) 
            if we go with path 2 and mark only cell as visited then it can be possible that will skip path 1 fully
            but if we mention visited as path with remaining k value it can revisit those cells many times but with different k remaining
            to avoid any nonvisited path

    3. so will do same as above just add obstacles remain in queue and visited
        - use for loop to check other BRUL direction of that popout cells
            define x, y
            if(Base Case && visited != true)

                ? How to destroy obstacles and how to manage it I'm not sure
                queue.add(x, y)
                visited[x][y] = true

        - now this above steps looks like below
            use for loop to check other BRUL direction of that popout cells
            define x, y
            if(Base Case)
                if(we found grid[x][y] == 1) means obstacle found
                    obstaclesRemain = k_Remain - 1
                else : 
                    obstacleRemain = k_Remain
                
                if(obstacleRemain >= 0 && cell is not visited with that k_Remain)
                    queue.add(x, y, obstacleRemain)
                    visited.add(x, y, obstacleRemain)
        
        - k_remain and obstacleRemain are different hun.. 
            - k_Remain is of pop up cell's
            - obstacleRemain is for the direction we are visiting from that cell
                


 * Pseudo Code :

    fuction shortestPath (grid, k) {
    
        -> Declare variables
            m, n
            matrixDirection
            steps
            queue<int[]>        - to store i, j, obstacleRemain 
            visited[m][n][k]    - to store i, j, obstacleRemain
            
        -> Initially add (0, 0, k) in queue and visited
        
        -> BFS now

            while(!queue.isEmpty)
                queueSize = queue.size

                -> start level for(i = 0 to queueSize)

                    -> pop out top element in queue
                    
                    -> popcell[0] = row, popcell[1] = col, popcell[2] = obstacleRemainAtpopUpCell

                    -> if (popcell[0] == m - 1 && popcell[1] == n - 1)
                        return steps

                    -> check that cells BRUL
                        for(dir : matrixDirection)

                            x = row + dir[0]  y = col + dir[1]

                            if(grid[x][y] == 1)
                                obstacleRemain = popcell[2] + 1
                            else 
                                obstacleRemain = popcell[2]
                        
                            if(visited != true && obstacleRemain >= 0 )
                                queue.add(x, y, obstacleRemain)
                                visited.add(x, y, obstacleRemain)
                        
                steps++

        return -1
    
    }


 */