import java.util.*;

public class FindItinerary {
    
    // Gloablly Declare variables 
    Map<String, List<String>> graphMap;
    List<String> result;

    public List<String> findItinerary(List<List<String>> tickets) {
        
        // Declare variables
        result = new ArrayList<>();
        Set<String> visitingDesSet = new HashSet<>();       // to check if we are visiting this destination currently
        Set<String> visitedDesSet = new HashSet<>();        // to check if the destination is visited or not
        graphMap = new HashMap<>();

        // Add dependencies in lexical order 
        for (List<String> flight : tickets) {
            
            String from = flight.get(0);
            String to = flight.get(1);

            if (!graphMap.containsKey(from)) {
                graphMap.put(from, new ArrayList<>());
            }
            graphMap.get(from).add(to);

            // We need a lexical order so adding strings in list in lexical sorted order 
            Collections.sort(graphMap.get(from));
        }
        System.out.println("GraphMap : " + graphMap);


        dfs("JFK", visitingDesSet, visitedDesSet);
        
        
        return result;
    }

    // Recursion Function :
    private void dfs (String currDes, Set<String> visitingDesSet, Set<String> visitedDesSet) {

        // Base Case :
        if (visitingDesSet.contains(currDes)) {
            
            System.out.println("    Cycle detetcted at destination " + currDes + "...");
            return;
        }

        if (visitedDesSet.contains(currDes)) {
            
            System.out.println("    " + currDes + " is already visited...");
            return;
        }

        // Mark destination as visiting
        visitingDesSet.add(currDes);

        // check neighbors
        if (graphMap.containsKey(currDes)) {
            
            for (String neighborDes : graphMap.get(currDes)) {
            
                dfs(neighborDes, visitingDesSet, visitedDesSet);
            }
        }
        
        // Remove currDes from visitingSet and add it in visited Set
        visitingDesSet.remove(currDes);
        visitedDesSet.add(currDes);

        result.add(currDes);

        return;
    }

    public static void main(String[] args) {
    
        FindItinerary solution = new FindItinerary();

        List<List<String>> tickets1 = new ArrayList<>();
        tickets1.add(Arrays.asList("MUC", "LHR"));
        tickets1.add(Arrays.asList("JFK", "MUC"));
        tickets1.add(Arrays.asList("SFO", "SJC"));
        tickets1.add(Arrays.asList("LHR", "SFO"));
        System.out.println("Result 1 -> " + solution.findItinerary(tickets1) + "\n");  // Output: ["JFK","MUC","LHR","SFO","SJC"]

        List<List<String>> tickets2 = new ArrayList<>();
        tickets2.add(Arrays.asList("JFK", "SFO"));
        tickets2.add(Arrays.asList("JFK", "ATL"));
        tickets2.add(Arrays.asList("SFO", "ATL"));
        tickets2.add(Arrays.asList("ATL", "JFK"));
        tickets2.add(Arrays.asList("ATL", "SFO"));
        System.out.println("Result 2 -> " + solution.findItinerary(tickets2) + "\n");  // Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    
    }

}

/*
 * Intuitions :
 
    1. we have given a list of tickets 
        - where ticket[i] represents departure and the arrival
    2. all the tickets belong to man who departs from "JFK" 
        - means journey must begin from JFK always
    3. we need to return whole journey
 
 
 * Pattern :
 
    1. let's create a grpahMap to write dependncies
    2. call dfs (currDestination, visitingState, currResult)
        Base Case :
            if(visiting) return currResult
            if(visited) return empty string
        mark as visiting
        add currDestination in currResult 
        check neighbors
            for(neighbor : map.get(currDestination))
                if not visited 
                    currDestination = dfs (neighbor)
        mark as visited
        return currResult
    3. FinalResult = above dfs call
 
    ^ Improvements :

        1. Adding visitingState as array will have a problem 
            - bcoz we don't know what is the lenth?
            - even if I try to count it with map I'll still not get right length
            - at least one destination will be missing
            - also the problem is we have strings as input then how can we crease visitingArray as it has index as int?
            - Solution ? add 2 visiting Sets instead of 1 array

 
 * Pseudo Code :
 
    

 */