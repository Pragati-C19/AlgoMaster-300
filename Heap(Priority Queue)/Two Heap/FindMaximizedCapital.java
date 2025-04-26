import java.util.*;

public class FindMaximizedCapital {
    
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. projects array -> stores profit after completing that index's project
 * 2. capital array -> store capital to start that index's project
 * 3. w = initial capital u already have
 * 4. k = you can check at most this much projects to get maximum profits 
 * 
 * 5. Problem madhe below gosti mahit nahiye
 *      - w full profit asel ki just tya index vr alya nantcha profit asel.. 
 *          like trace k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 *          yat last la w = 3 hoto jr 1 consider kela tr nahi kela tr 2 hoto
 *          Que says profit will be added to my total capital
 *      - mala maximum capital havay means pure profit profit madhe add nahi karaychi garaj nahiye direct w madhe add kr
 * 
 * Pattern :
 * 
 * Brute Force :
 * 
 * 1. for(i = 0 to k)
 *      - check if w == capitals[index] ahe ka jo mala maxProfit deil
 *          if yes then w += profit[index] 
 *      - if w != capitals[index] any index of capital is not equal to w then
 *            check for imiginate lesser one 
 *            and add tat index profit in w
 * 
 * Example :
 * 
 * 1. k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * 
 *  i = 0
 *      
 *      -> check kel 0 <= capital[0] chi index ahe.. 
 *              - w += profit[0] 
 * 
 *  i = 1
 * 
 *      -> check kel 1 == capital[1] and capital[2] pn ahe 
 *             tr me yatla max index vala consider kela, karan tyach index chya against maxProfit asel
 *              -  w += profit[2]
 * 
 * 
 * Pseudo Code :
 * 
 * 1. I think of we need a array which has both capital and profit against same index
 * 2. so create a int[] updatedProjects
 *      [projectsProfit, capital, original Index]
 * 3. sort that updatedProjects array by capital requirements
 * 4. will use one heap to store profits of available projects
 *      - I think we need maximum profit so let's use maxHeap
 * 5. for (i = 0 to k)
 *      -> while(updatedProjects[projectIndex][2] <= w)
 *              - maxHeap.add(updatedProjects[projectIndex][1])
 *      -> if(maxHeap.isEmpty) -> break; -> we can't add any projects bcoz it's capital is high
 *      -> if(!maxHeap.isEmpty) 
 *              - currProfit = maxHeap.poll
 *              - w += currProfit
 * 6. return w;
 * 
 */