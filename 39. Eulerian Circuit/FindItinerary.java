import java.util.*;

public class FindItinerary {
    
    // Gloablly Declare variables 
    Map<String, List<String>> graphMap;
    List<String> result;

    public List<String> findItinerary(List<List<String>> tickets) {
        
        // Declare variables
        graphMap = new HashMap<>();
        result = new ArrayList<>();

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


        // we will always start from JFK
        dfs("JFK");
        
        return result.reversed();
    }

    // Recursion Function :
    private void dfs (String currDes) {

        // check neighbors
        if (graphMap.containsKey(currDes)) {
            
            List<String> neighborsList = graphMap.get(currDes);

            // While neighbors still exist
            while (!neighborsList.isEmpty()) {
                
                // Remove the first (smallest lex) destination
                String neighbor = neighborsList.remove(0);

                System.out.println("    - Updated GraphMap : " + graphMap);

                dfs(neighbor);
            }
        }
        
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
        2. Removed visitingState fully
            - seems like mala navhti garaj tyachi
            - why ? I'm currently unsure fully.. I just know it bcoz gpt say so
            - need to chake abt this more
        3. Instead of List<String> in map we can use minHeap 
            - We don't need to sort List<String> always when we add destination in map
            - and minHepa will automatically set destination in lexical order
            - also we want to remove that neighbor once we have visited
                why? bcoz que says we can visit one edge only one time
                so once it's visited we are removing that neighbor from parent Destination 
                example if I have graphMap like
                    JFK -> ATL, SFO
                    SFO -> ATL
                    ATL -> JFK, SFO
                    
                    - ata bagh me JFK madhe ATL check karayla geli.. tr ATL madhe first ch JFO ahe
                        so there is a chance that te parat JFK -> ATL path vr jail even if we have visited it before
                    - tyamul ekda path visit kela ki apan remove karun taktoy to neighbor
 
 * Pseudo Code :
 
    

 */