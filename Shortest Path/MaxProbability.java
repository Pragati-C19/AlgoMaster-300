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

        - will start 0 means adding {0, 1} in maxHeap { currNode, Probability }
            why probability as 1? bcoz we are multiplying 
        
        - we don't need a visited array here will check best probability found so far for that node
            initially add bestProb[start] = 1
        
        - bestProb = [1, 0, 0]

        - start a while loop till heap get's empty

            1. currNode = 0, currProb = 1

                - neighbor 1
                    neighborProb = 1.0 * 0.5 = 0.5
                    0.5 > bestProb[1] = 0.0     -> update and add to heap
                    bestProb = [1, 0.5, 0]
                    maxHeap = { [1, 0.5] }

                - neighbor 2
                    neighborProb = 1.0 * 0.2 = 0.2
                    0.2 > bestProb[1] = 0.0     -> update and add to heap
                    bestProb = [1, 0.5, 0.2]
                    maxHeap = { [1, 0.5], [2, 0.2] }
                
            - currNode = 1, currProb = 0.5
                
                - neighbor 0
                    newProb = 0.5 * 0.5 = 0.25
                    0.25 < bestProb[0] = 1.0    -> skip

                - neighbor 2
                    neighborProb = 0.5 * 0.5 = 0.25
                    0.25 > bestProb[2] = 0.2    -> update and add to heap
                    bestProb = [1, 0.5, 0.25]
                    maxHeap = { [2, 0.25], [2, 0.2] }

            - currNode = 2, currProb = 0.25
                it's our destination return currProb

            
        - else return 0
            




 */