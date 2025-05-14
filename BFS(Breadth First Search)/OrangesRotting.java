import java.util.*;

public class OrangesRotting {
    
    public int orangesRotting(int[][] grid) {
        
        return -1;
    }

    public static void main(String[] args) {

        OrangesRotting solution = new OrangesRotting();

        int[][] grid1 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("Result 1 : " + solution.orangesRotting(grid1) + "\n");

        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("Result 2 : " + solution.orangesRotting(grid2) + "\n");

        int[][] grid3 = {
            {0, 2}
        };
        System.out.println("Result 3 : " + solution.orangesRotting(grid3) + "\n");

    }
}

/*

 ^ Information :

    1. DFS :
        - apan suppose 3 x 3 cha matrix ghetla and we need to check 1 and if we see 0 we are skipping it
        - jr mala [0,0] vr 1 bhetla tr me lagech tyachya B[1,0] la check karte tithe bhetla 1 again tr me tyachya B[2,0] la check karel
        - ata me [2,0] chya tithe 1 bhelyavr tyacha BRUL check keal but tithe kahich nahi bhetla 
        - mg me jail [1,0] chya R la check karayla tithe bhetle tr tya cell cha B baghel nahi bhetla tr [1,0] cha U and L asa check karel
        - same [1,0] ch complete zal ki me [0,0] chya urlelya side unvisited astil tr baghun gheil

    2. BFS
        - apan suppose 3 x 3 cha matrix ghetla and we need to check 1 and if we see 0 we are skipping it
        - jr mala [0,0] vr 1 bhetla tr me adhi tyala queue madhe takel..
        - ani then me tyachya side che BRUL check karel.. 
        - jr mala 1 bhetla tithe(BRUL) tr me ti cell la lagech check karayla nahi janar me tya cell la queue madhe add karel
        - suppose me [0,0] cha B[1,0] check kela mala tithe 1 bhetla me tya cell la queue madhe takte 
        - nantr queue madhun pop out karel and tyache as pass che check karel


 * Intuitions :
 
    1. We have given a matrix m x n
    2. where each cell have values as
        - 0 : an empty cell
        - 1 : cell with fresh orange in it
        - 2 : cell with rotten orange in it
    3. every minute, any fresh orange around the rotten orrange is getting rotten
    4. return minimum number of minutes that must elapse till no cell have Fresh Orange


 * Pattern :
 
    1. we don't need to check deeply.. we only need to check 4 directions BRUL around rotten orange
    2. Use BFS here : mean we need queue to store rotten orange cell
    3. there is one more thing.. que says if any fresh orange is present in matrix we have to return -1
        - so not to check how many fresh orange remain first we need to set the fresh orange count in matrix
        - and then when we replace it with rotten will do count--
    4. abt time thing.. one rotten orange can change all it's neighbors in 1 min 
        - soo will just do time++ 



 * Pseudo Code :
 
    function orangesRotting(grid) {
    
        -> Declare variables :
                matrixDirection         - to get BRUL directions
                queue                   - to store cell which has rotten orange (2)
                timeElapsed = 0         - time it takes to rotten the fresh oranges
                freshOrangeCount = 0    - it maintains the count of fresh oranges we have (initially we don't know how many oranges are in matrix)

        -> Initial Setup : check fresh orange count and add rotten oranges in queue

            for(i = 0 to m)
                for(j = 0 to n)

                    -> add all rotten oranges we knows in queue before we try to change fresh with rotten
                        if(grid == 2)
                            queue.push(int[] {i, j})
                    
                    -> Also count all fresh oranges we have so far (now we know how many oranges are in matrix)
                        if(grid == 1)
                            freshOrangeCount++

        -> Main Part : now change fresh oranges with rotten

            while(!queue.isEmpty)

                -> pop out rotten orange cell which have i and j in it
                    rottenOrangeCell = queue.pop
                
                -> check fresh oranges near(BRUL) of that rottenOrangeCell
                    for(dir : matrixDirection)
                        x = i + dir[0]
                        y = j + dir[1]
                    
                        -> if u get cell with 1 we need to change it and add that updated rotten orange in queue 
                            and increased the time by 1 and decreased the orange count by 1

                            if(base cases && grid[x][y] == 1)
                                grid[x][y] = 2
                                queue.push(int[] {x, y})
                                time++
                                freshOrangeCount--

        -> return time if freshOrangeCount = 0 else -1
    
    }
    


 */