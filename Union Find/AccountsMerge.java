import java.util.*;

public class AccountsMerge {
    
    // Disjoint Set Union Class
    private static class DSU {

        // Declare variables Globally for function in the class
        
        int[] parent;   // tells who is the leader of each element
        int[] rank;     // tells how tall the tree is
        int[] size;     // tells how many elements are there in the group

        // Constructor: initialize DSU with n elements (make each element its own parent)
        public DSU (int n) {

            // we use 1-based indexing
            parent = new int[n + 1]; 
            rank = new int[n + 1];
            size = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                
                parent[i] = i;  // everyone is their own leader at the start
                rank[i] = 0;    // height is 0 at the start
                size[i] = 1;    // there is at least one element at the start ((only itself))
            }

        }

        // Find the ultimate leader of the group for an element
        public int findParent (int currElement) {

            if (currElement == parent[currElement]) {
                return currElement; // if it's its own parent, it's the leader
            }

            // find the leader and update it (path compression)
            parent[currElement] = findParent(parent[currElement]);
            
            return parent[currElement];

        }

        // Union by Rank: attach smaller height tree under taller tree
        public void unionByRank (int firstElement, int secondElement) {

            // Get parent of both elementes
            int parent1 = findParent(firstElement);
            int parent2 = findParent(secondElement);

            if (parent1 == parent2) {
                
                // Already in the same set
                return;
            }

            // Attach lower rank tree under higher rank tree
            if (rank[parent1] < rank[parent2]) {
                
                parent[parent2] = parent1;
            }
            else if (rank[parent1] > rank[parent2]) {
                
                parent[parent1] = parent2;
            }
            else {

                // both same height, anyone can be parent and increase height by 1
                parent[parent2] = parent1;
                rank[parent1]++;
            }

            return;
        }

        // Union by Size: attach set with less memebers under set with more memebers
        public void unionBySize (int firstElement, int secondElement) {

            // Get parent of both elementes
            int parent1 = findParent(firstElement);
            int parent2 = findParent(secondElement);

            if (parent1 == parent2) {
                
                // Already in the same set
                return;
            }

            // Attach smaller size tree under bigger size tree
            if (size[parent1] < size[parent2]) {
                
                parent[parent2] = parent1;
                size[parent1] += size[parent2]; 
            }
            else if (rank[parent1] > rank[parent2]) {
                
                parent[parent1] = parent2;
                size[parent2] += size[parent1]; 
            }
            else {

                // both same size, anyone can be parent and increase size 
                parent[parent2] = parent1;
                size[parent1] += size[parent2]; 
            }

            return;
        }

    }

    // Driver Function :
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        // Decalre Variables 
        int n = accounts.size();
        Map<String, Integer> mailGroupMap = new HashMap<>();
        List<String> combinedMail = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();

        // Declare DSU variable
        DSU dsu = new DSU(n);

        // add key and value in map
        for (int i = 0; i < n; i++) {

            // skipping 0'th index of acct[i]
            for (int j = 1; j < accounts.get(i).size(); j++) {
                
                // get mail
                String currMail = accounts.get(i).get(j);

                if (!mailGroupMap.containsKey(currMail)) {

                    // Debug: New mail, assign it to group i
                    System.out.println("    - Mail \"" + currMail + "\" seen first time, map it to group " + i);

                    mailGroupMap.put(currMail, i);
                }
                else {

                    // Debug: Mail already seen, merging sets
                    System.out.println("    ~ Mail \"" + currMail + "\" already seen, union group " + i + " with group " + mailGroupMap.get(currMail));
                    
                    dsu.unionBySize(i, mailGroupMap.get(currMail));
                }
            }
        }
        System.out.println("MailGroupMap : " + mailGroupMap);


        return result;
    }

    
    public static void main (String[] arg) {

        AccountsMerge solution = new AccountsMerge();

        List<List<String>> accounts1 = new ArrayList<>();

        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts1.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts1.add(Arrays.asList("John", "johnnybravo@mail.com"));

        System.out.println("Result 1 : " + solution.accountsMerge(accounts1) + "\n");


        List<List<String>> accounts2 = new ArrayList<>();

        accounts2.add(Arrays.asList("Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"));
        accounts2.add(Arrays.asList("Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"));
        accounts2.add(Arrays.asList("Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"));
        accounts2.add(Arrays.asList("Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"));
        accounts2.add(Arrays.asList("Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"));

        System.out.println("Result 2 : " + solution.accountsMerge(accounts2) + "\n");

    }

}


/*

 * Took Help by :
    1. https://www.youtube.com/watch?v=FMwpt_aQOGw&t=11s&ab_channel=takeUforward
    2. I need to learn more abt DSU I'm not able to think it in problems yet


 * Intuitions :
 
    1. We have given a list of accounts
        where account[i][0] is name 
                and rest are emails
    2. we need to combine these accounts 
    3. two accts belongs to same person id there is some common email to both accounts
    4. even if two accounts have the same name, they may belong to different people as people could have the same name. 
    5. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
    6. after combining accounts return the acct in following order
        - name
        - emails in sorted order

 
 * Pattern :
 
    ^ Trace Example :

    1.  accounts = {
            ["John","johnsmith@mail.com","john_newyork@mail.com"],
            ["John","johnsmith@mail.com","john00@mail.com"],
            ["Mary","mary@mail.com"],
            ["John","johnnybravo@mail.com"]
        }
        
        - first and second john are same persons bcoz they have common email id "johnsmith@mail.com"
        - mary and third john are different persons as no other accts have their mail address
        - so my brute force is except acct[i][0] check if any string is equal to any other string in array
            if yes combine them and then add it in ans
            if no add it in ans
         
    ^ Code Writing

    1. we need to write DSU class
        
        - findParent (int currElement)
        - unionByRank (int firstElement , int secondElement)
        - unionBySize (int firstElement , int secondElement)

    2. Driver Function :

        - Declare variables
            n               -> size of acct array
            map             -> to store mail and it's parent node
            combinedMail    -> store combined mail if any like apan jr 2 sets combine kele tr tyache mails ekat merge hotil te ithe lihilel asel
            result          -> get result

        - then start a for loop for acct Array 
            for(i = 0 to n)
        
            - let's check arrays of acct one by one, and will skip the 0'th position (acct[i][0]) karan te names ahet we don't want it
                for(j = 1 to acct[i].length)

                    - get mail 
                        mail = acct[i][j]

                    - jr map does not contains mail we need to add it as a group of i
                        why ? see mazyakde ek acct array ahe tyat 4 arrays ahet so me tya saglyanna ek 4 different group samjte je 0 to n ahet
                            ata jevha me acct[i][j] check kartey tevha to mail mazya i group madhe asel? right? 
                            nasel samjal tr check that video

                        if(!map.contains(mail))
                            map.put(mail, i)

                    - else jr map contains that mail then we need to combine those two groups i
                        
                        else 
                            DSU.unionBySize(i, map.get(mail))   

        - we have disjoint sets now after combining same accts
            now we need to add it in result right?

            for all map entry

                mail = entry.Key                    // get mail from map
                immediateParent = enty.Value        // it's parent we have sotred in map with help of acct array
                
                - We need to find at which set it belongs to, set can have it's parents to right?
                    so we need to find ultimate parent of the mail not the immediate parent which we have stored according to the input in map
                    ultimateParentOfMail = DSU.find(set) 

                - add this ultimate parent in combined mail array
                    combinedMail.get(ultimateParentOfMail).add(mail)
            
        - ata result madhe sagal kahi add kr nums at start and then he sorted list of mails

            for(i = 0 to n)

                - jr combinedMail chya ekhadya index la kontech mails nastil rahile 
                    suppose me set 1 and set 3 la combine kel and sagle mails set 1 madhe takle tr set 3 empty asel na so tyala add arun kay use ahe na

                    if(combinedMail[i].length == 0)
                        continue

                - aplyala que madhe dilay we need a sorted list on mails
                    sort(combinedMail[i])

                - ata jo acct[i][0] hota name to adhi apan result amdhe add karu
                    result.get(i).add(acct[i][0])

                - ata name add kelay we need to add all mails in result followed by name
                    for (j = 0 to combinedMail[i].length)

                        result.get(i).add(combinedMail.get(j))


        - at the end return result



 */