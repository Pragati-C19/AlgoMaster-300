public class AccountsMerge {
    
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