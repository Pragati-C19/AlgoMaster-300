public class MaxProbability {
    
}

/*
 * Intuitions :
 
    1. we have given a undirected weighted graph of n nodes (0-indexed)
    2. edge array which tells abt the edges between two nodes
    3. with probability to travel that edge given in succProb
    4. we have given a start and end point
        we need to find maximum probability of sucess to go from start to end
    5. return succProbability
 
    Understand the que
        - basically kahi nodes ahet jya ekmekkana connected ahet with edges
        - pratek edge chi cost mhnu shakto apan te ahe succProb
            edges[1] -> succProb[1]
        - aplyala starting and ending point dilay
        - path find karaychay tyanchyatla
        - path jr bhetla tr check karaychy ki ti probability max ahe ki nahiye? 
 
 * Pattern :
 
    ^ Trace Example :
        n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2

                    0.5
               n0 -------- n1
                 \        /
                  \      /
             0.2   \    /   0.5
                    \  / 
                     \/
                     n2
 
        - we need to store edges with succProb in map
            edges[i][0] -> { edges[i][1], succProb[i] }

            0 -> [1, 0.5], [2, 0.2]
            1 -> [0, 0.5], [2, 0.5]
            2 -> [0, 0.2], [1, 0.5]

        - will start 0 means adding {0, 0} in maxHeap { currNode, Probability }
        - will need a visited array to store if we have visited that currNode or not
        - start a while loop till heap get's empty
        - pop Out {0, 0}
            totalProbability = 0

            check neighbor 1
                it's not viisted add in maxHeap
            check neighbor 2
                it's not visited add in maxHeap

            maxHeap = { [1, 0.5], [2, 0.2] }
            
        - pop out {1, 0.5}
            totalProbability = 0 + 0.5

            check neighbor 0
                visited
            check neighbor 2
                it's not visited add in maxHeap

            maxHeap = { [2, 0.5], [2, 0.2] }    -> max prob at top

        - pop out {2, 0.5}
            totalProbabitlity = 0.5 + 0.5

            check neighbor 0
                visited
            check neighbor 1 
                visited
        
        - ans = totalProbability
            




 */