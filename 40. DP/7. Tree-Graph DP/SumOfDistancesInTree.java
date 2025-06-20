import java.util.*;

public class SumOfDistancesInTree {
    
    
}

/*
 * Intuitions :
 
    1. there is an undirected connected tree with n nodes labeled from 0 to n-1 
        and there are n-1 edges too
    2. we are given a integer n and array edges 
        where edges[i] = [a, b]
        which indicates that there is a edge between this a and b
    3. we need to return array ans of length n
        where ans[i] = sum of the distance between i'th node in the tree and all other nodes
 
 
 * Pattern :
 
    1. This is a graph problem 
        so we need adjList
    2. Will use dfs function (currNode, parentNode)
        - Base Case : if currNode is null then return 0
        - assign currDist = 1
            yacha meaning currNode itkya distance vr ahe
        - Check all neighbors
            for(neighbor : adjList)

                if(neighbor != parentNode) 
                    ans[i] += dfs(neighbor, currNode)


    ^ Dry Run :
        
        Example :
            n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]

        - Create adj List
            adjList = {
                0:  [1,2]
                1:  [0] 
                2:  [0,3,4,5] 
                3:  [2] 
                4:  [2] 
                5:  [2] 
            }
    
        - As per explaination on que 
            ans[0] = dist[0,1] + dist[0,2] + dist[0,3] + dist[0,4] + dist[0,5]

            so I think it is more on 
            ans[i] = dist[i,neighbor]

 
 * Pseudo Code:
 





 */