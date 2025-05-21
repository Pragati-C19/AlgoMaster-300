import java.util.*;

public class FindCircleNum {
    
    // Driver Function
    public int findCircleNum(int[][] isConnected) {
        
        // Declare variables
        int n = isConnected.length;
        boolean[] visitedCity = new boolean[n];
        int provincesGroup = 0;

        for (int city = 0; city < n; city++) {
            
            // will only check row here as col are neighbors so will check it in dfs

            if (!visitedCity[city]) {
            
                dfs(city, visitedCity, n);

                // each new DFS call means a new group/province
                provincesGroup++;
            }
           
        }

        return provincesGroup;
    }

    // Recursion Function : to get connected cities
    private void dfs (int currCity, boolean[] visitedCity, int n) {

        visitedCity[currCity] = true;

        // check it's neighbors
        for (int neighborCity = 0; neighborCity < n; neighborCity++) {
            
            dfs(neighborCity, visitedCity, n);
        }

        return;
    }

    public static void main (String[] args) {

        FindCircleNum solution = new FindCircleNum();

        int[][] isConnected1 = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Result 1 -> " + solution.findCircleNum(isConnected1) + "\n");

        int[][] isConnected2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Result 2 -> " + solution.findCircleNum(isConnected2) + "\n");

    }
}

/*
 * Intuition :
 
    1. there are n cities
    2. some of thm are connected others are not
    3. if city(a) -> city(b) and city(b) -> city(c)   
       then city(a) -> city(c)
    4. province is a group of directly or indirectly connected cities (connected components)
    5. we have given n x n matrix 
        where 1 means that row and col city is connected
              0 means that row and col city is notConnected
        example :
                        |
                    0   |    1   1   0
                    1   |    1   1   0
                    2   |    0   0   1
                   row  |                   
                --------|----------------------
                   col  |    0   1   2
           
            so now row(0) -> col(0)
                   row(0) -> col(1)
                   row(1) -> col(0)
                   row(1) -> col(1)
                   row(2) -> col(2)

    6. return total number of provinces
            what is provinces group means ?
                understand it by example
                    city(0) -> city(0), city(1)
                    city(1) -> city(0), city(1)
                    city(2) -> city(2)

                jr baghitl tr kalel ki ithe 2 group bantay
                    g1 = [0, 1]
                    g2 = [2]
                

 * Pattern :
 
    1. so as per given data mala kahi gosti run kartay mind madhe
        - connections ahe mhnje graph ahe 
        - graph ahe tr aplyala dependecyMap lagel
        - city ch city la connectd ahe so row will be node and col will be neighbor 
            ka? bagh na example trace kel tyat tr tasach distay
        - use dfs to trace naighbors of that city
        - now think how will I get group?
            ithe me thodi help ghetli.. see aplyala havet kiti connected componets ahet te bagahyche
            like me je g1 = [0, 1] and g2 = [2] mhnle te tasa nahiye
            mala ek group bhetla jyat 0 - 1 he connected ahet 
            and dusra group bhetla jyat 2 ahe.. it is self connected
        - tr ata focuss shift kr thoda to find connected cities in matrix

    2. let's think with find connected cities in matrix
        - me adhi matrix traversal karte
        - and tyat dfs call karte
            - visited true karel
                visited = true 

            - then neighbors check karel but ata matrix is  n x n 
                and me mhnle hote col mhnje neighbors ahet tyamul will traverse for loop till n
                for(neighbor : n)

                - call dfs for naighbor

    3. what does below code means
        
        for each city {
            if city is not visited {
                dfs(city);          // visit all cities in the current province
                provincesGroup++;   // one new group found
            }
        }

        Every time you enter this if (!visited[i]) block, it means:
            - City i has not been visited yet.
            - So you're starting a new DFS from city i, to find all cities connected directly or indirectly to it.
            - Once DFS completes, you’ve found one full group (also called a connected component or province).
            - So now you increase the group count by one → provincesGroup++.


 
 * Pseudo Code :
 
    - chal lihaychi garaj nahiye simple ahe code let's directly code
 
 */