import java.util.*;

public class CloneGraph {
    
    // Node class for graph
    private static class Node {
        
        // Each node has a value and a list of neighbors (connected nodes)
        public int val;
        public List<Node> neighbors;
    
        // Default constructor:
        // Creates a node with value 0 and an empty neighbor list
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
    
        // Constructor that sets the node's value,
        // and initializes an empty neighbor list
        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    
        // Constructor that sets the node's value and its neighbors
        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    // Globally Declare Variables
    Node clonedNode;
    Set<Node> visitedNodeSet;

    // Driver Function
    public Node cloneGraph(Node node) {
        
        // assign value to global variables
        visitedNodeSet = new HashSet<>();

        dfs(node);

        return clonedNode;
    }

    // Recursion Function : to get cloned Nodes and neighbors
    private Node dfs(Node currNode) {

        if (visitedNodeSet.contains(currNode)) {
            System.out.println(" Current Node " + currNode.val + " already cloned. Returning existing clone.");
            return currNode;
        }

        visitedNodeSet.add(currNode);
        
        clonedNode = new Node(currNode.val);
        System.out.println("       -> Cloning Node: " + currNode.val);

        for (Node neighborNode : currNode.neighbors) {
            
            Node clonedNeighborNode = dfs(neighborNode);

            clonedNode.neighbors.add(clonedNeighborNode);
        }

        return currNode;
    }


    // Helper Function : to print node in array list
    private void printGraph(Node node) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (visited.contains(curr)) continue;
            visited.add(curr);

            // Build adjacency list
            adjList.putIfAbsent(curr.val, new ArrayList<>());
            for (Node neighbor : curr.neighbors) {
                adjList.get(curr.val).add(neighbor.val);
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }

        // Prepare list in LeetCode style format
        int size = adjList.size();
        List<List<Integer>> result = new ArrayList<>(Collections.nCopies(size, null));
        for (int i = 1; i <= size; i++) {
            List<Integer> neighbors = adjList.getOrDefault(i, new ArrayList<>());
            result.set(i - 1, neighbors);
        }

        // Print result
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args){

        CloneGraph solution = new CloneGraph();

        // Create original graph manually
        // Graph: 1 -- 2
        //        |    |
        //        4 -- 3

        // Created nodes 
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // add there neighbors
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        // Call your cloneGraph function here (you implement it)
        Node cloned = solution.cloneGraph(node1);

        // Print cloned graph
        System.out.println("-> Cloned Graph : ");
        solution.printGraph(cloned);

    }

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
        - visitedNode  -> Set<Node> bcoz we are adding node not integer
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
