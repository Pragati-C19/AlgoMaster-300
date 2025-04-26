import java.util.*;

public class FindMaximizedCapital {
    
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        
        int n = profits.length;
        int projectIndex = 0;
        int result = w;

        int[][] updatedProjects = new int[n][3];
        for (int i = 0; i < n; i++) {
            updatedProjects[i][0] = profits[i];     // Projects Profit
            updatedProjects[i][1] = capital[i];     // Capital to start that project
            updatedProjects[i][2] = i;              // Original Index
        }

        // Sort array by capital requirements so used [1]
        Arrays.sort(updatedProjects, (a, b) -> Integer.compare(a[1], b[1]));
        System.out.println("  Sorted Array is : " + Arrays.deepToString(updatedProjects));

        PriorityQueue<Integer> availableProjects = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < k; i++) {
            
            while (projectIndex < n && updatedProjects[projectIndex][1] <= w) {
                availableProjects.add(updatedProjects[projectIndex][0]);
                
                System.out.println("    available projects are : " + availableProjects);
                projectIndex++;
            }

            if(!availableProjects.isEmpty()){
                int currProfit = availableProjects.poll();
                w += currProfit;
                result = w;
            }
        }

        return result;
    }

    public static void main(String[] args){

        FindMaximizedCapital solution = new FindMaximizedCapital();

        int[] profit1 = {1, 2, 3};
        int[] capital1= {0, 1, 1};
        int k1 = 2;
        int w1 = 0;
        System.out.println("-> Result 1 : " + solution.findMaximizedCapital(k1, w1, profit1, capital1) + "\n");

        int[] profit2 = { 1, 2, 3};
        int[] capital2 = {0, 1, 2};
        int k2 = 3;
        int w2 = 0;
        System.out.println("-> Result 2 : " + solution.findMaximizedCapital(k2, w2, profit2, capital2));

    }
}

/*
 * 
 * so this test case was wrong k = 2  w = 1  profits = [1,2,3]  capital = [2,2,2]
 * - if w = 1 and no capital has less or equal to w value then will say no project is available 
 * - so as per rule it should return w = 1 as it is right?
 * - my code was return 0 bcoz of 2 reasons
 *      - I was giving return as result.. and initializing result = 0 instead of = w
 *      - First of all I should just return w instead of creating new variable result
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