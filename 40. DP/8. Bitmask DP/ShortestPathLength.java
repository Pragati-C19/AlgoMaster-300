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
 



 */