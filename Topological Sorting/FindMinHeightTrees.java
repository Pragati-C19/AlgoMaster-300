import java.util.*;

public class FindMinHeightTrees {
    
}

/*
 * Intuitions :
 
    1. we have given a tree which is an undirected graph mean two vertices are connected by exactly one path
    2. Any connected grpah without simple cycle is tree
    3. we have given a tree of n nodes (0-indexed)
        where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree
        we can use any node of tree as root
    4. when we choose x node of the tree as root, the result tree has height h
    5. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees 
    6. we want to return list of all minimum height trees's rootNode

 * Pattern :
 
    1. after tracing the problem I think we need to select one node as root and then check it's neighbors
    2. store it's height then check next node as root and get it's height
    3. updated minHeight = min(currHeight, minHeight)
    3. store that [node, height] in a array temporary
    4. then get node whose heights == minHeights

    ^ Trace example :

        n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]

        - we only knows the edge it's not compolusary that [3, 0] mean 3 -> 0 it can be 0 -> depend on what is our root node
        - so let's see edges between nodes
            0 - 3
            1 - 3
            2 - 3
            3 - 0, 1, 2, 4
            4 - 3, 5
            5 - 4

        - we have 6 nodes and will have to select one node as root one by one
        - 0 as rootNode : height = 4

                            0
                            |
                            3
                     _______|_______
                    |       |       |
                    1       2       4
                                    |
                                    5

        - 1 as rootNode : height = 4

                            1
                            |
                            3
                     _______|_______
                    |       |       |
                    0       2       4
                                    |
                                    5

        - 2 as rootNode : height = 4

                            2
                            |
                            3
                     _______|_______
                    |       |       |
                    0       1       4
                                    |
                                    5
        
        - 3 as rootNode : height = 3
                    
                            3
                 ___________|___________
                |       |       |       |
                0       1       2       4
                                        |
                                        5
                                    
        - 4 as rootNode : height = 3
                    
                                4
                         _______|_______
                        |               |
                        3               5
                 _______|_______
                |       |       |       
                0       1       2       
        
        - so minHeight so far is 3 
        - and there are two root node whose height is 3 
            which are [3, 4]
                                    
    Thinking process :
        - I understand that I need to slect one node and trace it till end it doen't have cycle detected thing
        - bcoz we know edges 
        - my problem is I can't use map to store key and value if I did that it will goes in cycle detected loop
        - I think when I say this is root I need to add values in map according to that node?
        - I'm only stuck at here

 
 * Pseudo Code :


 */