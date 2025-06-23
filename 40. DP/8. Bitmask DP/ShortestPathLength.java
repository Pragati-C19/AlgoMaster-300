import java.util.*;

public class ShortestPathLength {
    
}

/*
 * Intuitions :
 
    1. we have an undirected, connected graph of n nodes from 0 to n-1
    2. we have given a graph array 
        where graph[i] is list of all the connected nodes with node i
    3. we need to return length of shortest path that visits every nodes
    4. we can start and stop at any node
    5. we can reuse edges and revist nodes multiple time
 
 
 * Pattern :

    Check this video
        https://www.youtube.com/watch?v=m73DRkEo8Ko&ab_channel=codestorywithMIK

    1. We'll try all possible paths starting from each node
    2. We need to maintain our Parent Node 
        to avoid repetations like 0 -> 1 -> 0 -> 1 onstead of checking 0 -> 1 -> 0 -> 2
        for example 
                         0  
                      /  |  \
                     1   2   3
    3. will use BFS 
        - bcoz BFS nehmi shortest path value det0
        - BFS madhe apan start from each node karu
            as in queue = [ (0,0001), (1,0010), (2,0100), (3,1000) ]
        - ata apan saglya queue empty hot nahi toparynt check karat rahu
        - queue madhun pop kel 
            ki check karu if that visited? asel tr will pop next 
            if not then will increase a pathLength by 1 
                and check it's neighbors and add it's mask in queue
        - jevha sagle nodes visit hotil will return pathLength
        - BFS madhe level by level check hot tyamul ithe apan ekach veli sagle starting point pasun check karat asu
            jevha mask = 1111 pop out hoil kontya pn node cha apan directly return karnar ahe 
        
        - ajun nit samjnyasathi example ghe ki tu race madhe ahe
            4 jan ahet race madhe 1, 2, 3, 4
            saglyannacha speed vegla ahe
            so 2nd person jevha finish line la pohochla tevha 1, 3, 4 few meters mage hote
            tyamul apan sagle persons nhi check karat baslo finish line parynat yayche 
            2nd win zala apan directly finish keli race 

    
    ^ Dry Run :

                         0  
                      /  |  \
                     1   2   3

        - As per que apan kuthun nahi start karu shakto 
            so apan pratek node la starting point dharun check karu path length 
            and minPath la update karu once we check all nodes
            
        - Will use bitMask to see which node is visited
            at start mask = 0000

        - apan 0th node pasun start karu

            starting node 0 :
                mask = 0001
                visited = (0, 0001)
                pathLength = 0
                visitedArray = [ (0, 0001) ]

                neighbor 1 :
                    mask = 0011
                    visited = (1, 0011)
                    pathLength = 0 + 1 = 1
                    visitedArray = [ (1,0011), (0, 0001) ]

                    neighbor 0 : 
                        0 is already visited as there is 1 in mask for 0th node so no change for mask
                        mask = 0011
                        visited = (0, 0011) 
                        pathLength = 1 + 1 = 2
                        visitedArray = [ (0, 0011), (1,0011), (0, 0001) ]
                
                        neighbor 1 : 
                            1 is already visited as there is 1 in mask for 1st node so no change for mask
                            mask = 0011
                            check if (1, 0011) is already visited if yes then will not increase pathLength
                            neither will check it's neighbors now
    
                            visitedArray = [ (0, 0011), (1,0011), (0, 0001) ]
                        
                        neighbor 2 : 
                            mask = 0111
                            visited = (2, 0111) 
                            pathLength = 2 + 1 = 3
                            visitedArray = [ (2, 0111), (0, 0011), (1,0011), (0, 0001) ]

                            neighbor 0 : 
                                0 is already visited as there is 1 in mask for 0th node so no change for mask
                                mask = 0111
                                visited = (0, 0111) 
                                pathLength = 3 + 1 = 4
                                visitedArray = [ (0, 0111), (2, 0111), (0, 0011), (1,0011), (0, 0001) ]

                                neighbor 1 : 
                                    1 is already visited as there is 1 in mask for 1st node so no change for mask
                                    mask = 0111
                                    checked (1, 0011) is already visited if yes then will not increase pathLength
                                    neither will check it's neighbors now
                                    visitedArray = [ (0, 0111), (2, 0111), (0, 0011), (1,0011), (0, 0001) ]

                                    something is wrong here I don't want to check (1,0111) again
                        
                                neighbor 2 : 
                                    2 is already visited as there is 1 in mask for 2nd node so no change for mask
                                    mask = 0111
                                    checked (2, 0111) is already visited if yes then will not increase pathLength
                                    neither will check it's neighbors now
                                    visitedArray = [ (0, 0111), (2, 0111), (0, 0011), (1,0011), (0, 0001) ]

                                neighbor 3 :
                                    mask = 1111
                                    visited = (3, 1111)
                                    pathLength = 4 + 1 = 5
                                    visitedArray = [ (3, 1111), (0, 0111), (2, 0111), (0, 0011), (1,0011), (0, 0001) ]


 
 
 * Pseudo Code :
 

    function shortestPathLength (int[][] graph) {
    
        -> Declare variables
            n = length of graph         - it will give nodes value
            visited[node][mask]         - it will tell if that node and mask is visited or not if it's true means visited
            queue                       - it will store (currNode, currMask) 
            pathlength = 0              - it gives pathLength 

        -> will not create adjList map this time bcoz we already has a graph array 

        -> initially we need to add all nodes and it's mask 
            for(i = 0 to n)

                - get bitmask with only ith node set 
                    as in if i=1 -> mask = 0010
                             i=2 -> mask = 0100
                             i=3 -> mask = 1000 and so on

                    mask = 1 << i

                - add that node i and mask in queue
                - also mark them as visited

        
        -> Now start the loop till queue is not empty
            while(!queue.isEmpty)

                - get size of queue
                - start for loop till that size so will pop all values at curr level
                    for(i = 0 to queueSize)

                        - pop top of que
                        - declare variables as currNode and currMask 
                        
                        - if all nodes are visited means mask = 1111 
                            then return path directly
                            1 << n means pow(2, n) - bit madhe nehmi 2^n chya basis vr value aste tymul he pow(2,n) aivaji 1 << n lihitat

                            if(mask == (1 << n) - 1)

                        - else will check all neighbors
                            for(neighbor : graph[neighbor]) 

                                - next mask kay asel ? 
                                    currMask cha OR kr neighbor mask sobt 
                                    as in if currMask = 0001 and i=1 (neighborMask = 0010)
                                          then nextMask = 0001 | 0010 = 0011                                                              

                                    nextMask  = currMask | (1 << neighbor)

                                - check if neighbor and nextMask is visited or not
                                    if yes then continue
                                    if not then mark it as visited and add it in queue
                
                - Now do pathLength++

        -> at the end return -1 as we didin't reach to an end

    }

 */