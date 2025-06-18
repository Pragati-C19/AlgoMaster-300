import java.util.*;

public class CountPaths {
    
}

/*
 * Intuitions :
 
    1. We are in city which consistes of n intersections numbered from 0 to n-1
        with bi-directional roads
    2. We have given integer and 2D array roads
        where roads[i] = [u, v, time]
        u = startingPoint
        v = endPoint
        time = time it took to reach till v from u
    3. we want to know, how many ways we can travel from 0 to n-1
        in shortest amount of time
    4. Return number of ways you can arrive at destination in the shortest amount of time    

 
 * Pattern :

    1. Will wrote dp where will store all ways to reach any point 
        how much time it took to reach that point 
    2. Take example 

        n     = 7
        roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
        
        index =   0  1  2  3  4  5  6 
        dp    = [ 0, 0, 0, 0, 0, 0, 0 ]

        - Map to store all connections and time in this map
            0 - [1, 2] [4, 5] [6, 7]
            1 - [0, 2] [2, 3] [3, 3]
            2 - [1, 3] [5, 1]
            3 - [1, 3] [5, 1] 
            4 - [0, 5] [6, 2]
            5 - [2, 1] [3, 1]
            6 - [0, 7] [4, 2]

        - When will check every nums one by one will see how much time it will take to reach currIndex

            point 0 - there is 0 ways and time to reach at point 0
                dp[i] = [ways, time]
                so dp[0] = [1, 0]

            point 1 - check all times it took to reach at this point 
                1 - [0, 2] [2, 3] [3, 3]
                so min(2, 3, 3) = 2

                time = 0 + 2

                so dp[1] = [3,2]

            point 2 - check all times it took to reach at this point 
                2 - [1, 3] [5, 1]
                so min(3, 1) = 1

                time = 2 + 1

                so dp[1] = [2,3]

            point 3 - check all times it took to reach at this point 
                3 - [1, 3] [5, 1]
                so min(3, 1) = 1

                time = 3 + 1

                so dp[1] = [2,4]

            point 4 - check all times it took to reach at this point 
                4 - [0, 5] [6, 2]
                so min(5, 2) = 2

                time = 4 + 2

                so dp[1] = [2,6]

            point 5 - check all times it took to reach at this point 
                5 - [2, 1] [3, 1]
                so min(1, 1) = 1

                time = 6 + 1

                so dp[1] = [2,7]

            point 6 - check all times it took to reach at this point 
                6 - [0, 7] [4, 2]
                so min(7, 2) = 2

                time = 7 + 2

                so dp[1] = [2,9]

 
 * Pseudo Code :
 



 */