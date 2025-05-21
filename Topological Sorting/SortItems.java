import java.util.*;

public class SortItems {

}

/*
 * Intuitions :
 
    1.  we have given below things
        n = 8 -> this much items we have
        m = 2 -> groups (group 0 and group 1) means it's belongs to that group
                -1 means item doesn't belong to any group
        beforeItems[]: dependencies, where beforeItems[i] means item i depends on those items before it.
                    mhnje in noraml time pan mhnto hoto index che neighbors ahet te...
                    ata mhntoy yacha neighbor ahe index
                    example : [[],[6],[5],[6],[3,6],[],[],[]]
                        means 6 -> 1, 3, 4
                              5 -> 2
                              3 -> 4
    2. we need to return sorted list but it has some conditions :
        - The items that belong to the same group are next to each other in the sorted list.
 
 
 * Pattern :
 
    1. ohk this is a bit complecated problems.. lets understand it with example
    
    ^ Trace Example 
        Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
        Output: [6,3,4,1,5,2,0,7]

        - there are 8 items : 0, 1, 2, 3, 4, 5, 6, 7
        - there are 2 groups : 0, 1 
        - group -1 means that index doesn't belong to any group that means it is of it's own group
            so now groups are = m + (sum of items with value -1) = 2 + 3 = 5    
            groups are : g0, g1, g2, g3, g4

        - groups will look like below.. u can visiualize it like there are 5 different circles and it has some numbers
            rememeber we used to solve set problems intersection, union in school it looks just like that

            g0 = [3, 4, 6]      g1 = [2, 5]      g2 = [0]      g3 = [1]      g4 = [7]                                                                   
                _____              _____          _____         _____          _____
               |     |            |     |        |     |       |     |        |     |
               |  3  |            |  2  |        |     |       |     |        |     |
               |  4  |            |  5  |        |  0  |       |  1  |        |  7  |
               |  6  |            |     |        |     |       |     |        |     |
               |_____|            |_____|        |_____|       |_____|        |_____|

        - ata let's check dependencies of items from beforeItems
            it says index is a neighbor of nums in array
            mhnje like below
            6 -> 1, 3, 4
            5 -> 2
            3 -> 4
            before items dilele aplyala tya specific items chya adhi yetil mhnun
            tr me te check karun ghetal ki ohk konte items che neighbors ahet mazi index 

        - he bagh he samjan thod complicated ahe part but bagh adhi kadhi pn mala edge or kontahi dependencies cha array dilelela asel tr me mhnt hote ki 
            index che neighbors edge[index] ahe mhnje : index -> edge[index]
            but ata ulat ahe ithe edge[index] cha neighbor index ahe mhnje : edge[index] -> index

        - ohk he zal fix?.. me 2 map banavle 
            1. groupMap -> jyat konta item kontya group cha ahe te ahe
            2. itemDependancyMap -> jyat items chi dependency ahe

        - see que madhe ek condition ahe ki The items that belong to the same group are next to each other in the sorted list.
            samja apan items la sort karaycha start kel without considering it's groups kasa hoil te?
            stack (top -> bottom) : [7, 6, 5, 3, 4, 2, 1, 0]     
            
            but ata ithe bagh kahi gosti full fill nahi hotye
                - groups madhle items javal havet.. 5, 2 javal ahe?.. nahi.. 3, 4, 6 javal ahe? nahi

        - tr ata apan kay karu adhi group by group sorting karu but yat ek issue ahe apan group madhle elements sort karyala laglo tr khup issue hoil at same time
            adhi apan je dependecy hoti tyat check karu group pn same ahet ki vegle ahet te ani tayvrun group sathi dependencies shodhu
            - g0 madhe 6, 3, 4 already ahet tyamul 3, 4 la seperate karaychi garaj nahiye
                6 -> 1      he vegal asel fact 
                g0 -> gr3

            - g1 madhe 5, 2 aleady ahet tyamul tyanna seperate karaychi garaj nahiye
            - g0 madhe 3, 4 aleady ahet tyamul tyanna seperate karaychi garaj nahiye

        - now ata adhi sorting kr groups chi
            sortedGroups = [g4, g2, g1, g0, g3]
        
        - now groups are sorted wrote items from it with it's order like let's take
            
            add values from g4 in topological sorting order
                sortedItems = [7]

            add values from g2 in topological sorting order
                sortedItems = [7, 0]

            add values from g1 in topological sorting order
                g1 has 2, 5
                we know as per our itemDependancyMap 5 -> 2
                sortedItems = [7, 0, 5, 2]

            add values from g0 in topological sorting order
                g0 has 3, 4, 6
                we know as per our itemDependancyMap 6 -> 3, 4  and 3 -> 4
                sortedItems = [7, 0, 5, 2, 6, 3, 4]

            add values from g3 in topological sorting order
                sortedItems = [7, 0, 5, 2, 6, 3, 4, 1]

        - Now u'll say he tr ans nahiyeee ans tr [6,3,4,1,5,2,0,7] asa ahee it's wrong now
            NOPE... it's not wrong
            I learn it hard way too topological sorting means dependecies valid ahe ka te matter karat.. exactly order tich ahe ka te nahi
            like ithe dependecies hoti 6 should be before 1, 3, 4 does it?  Yes it is 
                                       5 before 2 and 3 -> 4? Yes it is
        


     * Pseudo Code :
 
 
 
 
 *