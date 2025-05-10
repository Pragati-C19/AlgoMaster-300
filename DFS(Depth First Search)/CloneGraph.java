import java.util.*;

public class CloneGraph {
    
}

/*
 
 *  Intutions :

    1. We have given a of conneted nodes from the index i
    2. we need to create a clone of that adjList (1-indexed)
    3. each node in graph contains 
        int val, list[node] neighbors
    4. node's val is same as index
    5. first node will always be val = 1 bcoz adjList is a (1-indexed)
    6. few conditions to know :
        - you can't return the same graph -> mhnje jo input ahe tyalach output mhanna
        - If nodes are cloned they should have same connections
        - graph is cloned when nodes are new, and it looks exactly like the given graph


 *  Pattern :

    1. we need to use dfs -> to clone the nodes and it's neighbors
    2. will maintain a visited list/set to avoid going in infinite loop or getting any duplicates
    3. globally declare variables
        - clonedNode
        
    4. function
        - Declare a set to maintain visited Nodes
            visitedSet = new HashSet

    5. call Recursion function : (currNode)
        - If node is already visited will skip to add it again 
            if(visitedNode.contains(currNode))
                return;

        - If node is not visited will add it in set 
            visitedNode.add(currNode)

        - will create a new node of value currNode -> we are cloning the currNode
            clonedNode = new Node(currNode.val)
        
        - to clone neighbors 
          currNode.neighbors -> will give list<integer> of all neighbors of currNode
            for(neighborNode : currNode.neighbors)
        
        - to add neighbor in clonedNode
            - ohk ne can't directly add neighborNode in clonedNode we need to clonedNeighborNode too
                cloneNeighborNode = dfs(neighborNode)
            
            - After taking that clone neighbor will add it in out clonedNode neighbors
                clonedNode.neighbors.add(cloneNeighborNode)

        - Where will we call recursion again -> before adding neighbor in cloneNode.neighbors 
            dfs(neighborNode)

        - return

    6. return cloneNode



 */
