import java.util.*;

public class AllPathsSourceTarget {
    
}

/*
  
 * Intuitions :
  
  1. We have given Directed Acyclic Graph (DAG) of n nodes
  2. Find all possible paths from node 0 to node n - 1
  3. return them in any order
  4. graph array looks like : 
       graph[i] is list of all nodes you can visit from node i
       graph = [[1,2],[3],[3],[]]
       - from node 0 we can visit node [1, 2]
       - from node 1 we can visit node [3]
       - from node 2 we can visit node [3]
       - from node 3 we can't visit any node as the list is empty
  
  
 * Pattern :
  
  1. Will use map to store index as key and List<nodes u can visit from that index>
  2. will need variables
        - currPath Array -> it will store path till he finds n - 1
        - result Array -> After u find n - 1 with that path u should add that array in result Array
  3. Recursion call : dfs(index = 0, currPath, resultPath, graph )
        - apan saglyat adhi 0 add karu path madhe bcoz to asnarch ahe then after each recursion call it will add one more node connected to it
            currPath.add(index)    
        - Will check here only that visitingNode == n-1 will add curr to resultPath
            if(node == n - 1)
                resultPath.add(new currPath)
        - will check if map.contains(index)
        - visitNodeList -> jr asel tr apan tyachi visiting Node chi list gheu
        - will start an for(node : visitNodeList)
            dfs(node, currPath, resultPath, graph)
  4. return resultPath

  
 * Pseudo Code :
  
  function allPathsSourceTarget (graph){
  
        n = graph.length
        currPath = new Array
        resultPath = new Array

        Map<Integer, List<Integer>> map = new Hashmap

        -> I'm not sure how to add int[] in map 
            problem is : graph[i] chi fix size nahiye, so I can't use for loop unless I use graph[i].length
        
        for(i = 0 to n)
            
            if(!map.contains(i))
                map.put(i, new Array)
            
            for(j = 0 to graph[i].length)
                map.get(i).add(graph[i][j])


        dfs( 0, currPath, resultPath, graph, n)

        return resultPath
  
  }

  function dfs(currVisitingNode, currPath, resultPath, graph, n){
  
        currPath.add(currVisitingNode)

        if(currVisitingNode == n - 1)
            resultPath.add(new Array(currPath))

        if(map.contains(currVisitingNode))

            visitingNodesList = map.get(currVisitingNode)

            for(node : visitingNodesList)
                dfs(node, currPath, resultPath, graph, n)

        return;
  }
  
 */