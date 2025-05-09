import java.util.*;

public class NumOfMinutes {
    
    // Globally Declare Variables
    int maxTime;

    // Driver Function
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        
        maxTime = 0;

        Map<Integer, List<Integer>> teamMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            
            if (!teamMap.containsKey(manager[i])) {
                teamMap.put(manager[i], new ArrayList<>());
            }

            if (manager[i] != -1) {

                // Add employee under it's manager
                teamMap.get(manager[i]).add(i);
            }
        }

        System.out.println("Team Map Looks like : " + teamMap);



        return maxTime;
    }

    // Helper Function : to get maxTime recursively
    private void dfs(int currEmployee, int timeTakesToReceivedInfo, Map<Integer, List<Integer>> teamMap, int[] informTime) {

        return;
    }

    public static void main(String[] args){

        NumOfMinutes solution = new NumOfMinutes();

        int[] manager1 = {-1};
        int[] informTime1 = {0};
        System.out.println("Result1 -> " + solution.numOfMinutes(1, 0, manager1, informTime1) + "\n");      // 0

        int[] manager2 = {2,2,-1,2,2,2};
        int[] informTime2 = {0,0,1,0,0,0};
        System.out.println("Result2 -> " + solution.numOfMinutes(6, 2, manager2, informTime2) + "\n");      // 0
        
        int[] manager3 = {12,7,18,11,13,21,12,-1,6,5,14,13,14,9,20,13,11,1,1,2,3,19};
        int[] informTime3 = {0,540,347,586,0,748,824,486,0,777,0,202,653,713,454,0,0,0,574,735,721,772};
        System.out.println("Result3 -> " + solution.numOfMinutes(22, 7, manager3, informTime3) + "\n");      // 0

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. A company has n employee with unique ID from 0 to n - 1
 * 2. The head of the company has headID
 * 3. each employee has direct manager which is given in an array
 *      where manager[i] is the direct manager of i'th Employee
 * 4. manager[headID] = -1
 * 5. headOf Company wants to send an urget information and he will tell to his direct subordinates and they will to there
 *      - subordinates means teams-mates under an employee
 * 
 * 
 * Pattern :
 * 
 * Trace Example :
 * 
 *  n = 6, headID = 2, manager = [2,2,-1,2,0,0,0,1,3,3,3,7,7], informTime = [3,2,1,4,0,0,0,5,0,0,0,0,0]
 * 
 *      - Me ek map banavte tyat manager and employees add karte
 *          map = {0 = 4, 5, 6 } { 1 = 7 } { 2 = 0, 1, 3 } { 3 = 8, 9, 10 } { 7 = 11, 12 }
 *      
 *      - soo maza headID ahe 2 and tyacha info bhetnyacha time 0 bcoz toch tr information sangnar ahe 
 *          tr me adhi map madhun key 2 ahe ka check karel
 *          and tyatlya saglya employee cha time me 0 + 1 = 1 karel
 *          why +1 informTime madhe dilay so
 *          2 (time = 0) -> 0 (time = 1) , 1 (time = 1), 3 (time = 1)
 * 
 *      - let's check subordinates of 2
 *          0 (time = 1) -> 4 (time = 1 + 3) , 5 (time = 1 + 3), 6 (time = 1 + 3)
 *          1 (time = 1) -> 7 (time = 1 + 2) 
 *              yat kay hoil tyachyakde info ali 1 min nantr and tyala next la pass karayla + 2 time lagla
 *          3 (time = 1) -> 8 (time = 1 + 4) , 9 (time = 1 + 4), 10 (time = 1 + 4)
 * 
 *      - let's check subordinates of 1
 *          7 (time = 3) -> 11 (time = 3 + 5) , 12 (time = 3 + 5)
 *          
 *      - let's check subordinates of 3
 *          8 (time = 5) -> none
 *          9 (time = 5) -> none
 *          10 (time = 5) -> none
 * 
 *      - we need to calculate max time so bagh kontya employee parynt infor kiti time madhe bhetali
 *          
 *            Employee     |    Manager    |    Time           |    MaxTime     
 *         ----------------|---------------|-------------------|----------------
 *               0         |      2        |     1             |      1                   
 *               1         |      2        |     1             |      1       
 *               2         |     -1        |     0   -> head   |      0           
 *               3         |      2        |     1             |      1                       
 *               4         |      0        |     4             |      4                       
 *               5         |      0        |     4             |      4           
 *               6         |      0        |     4             |      4                   
 *               7         |      1        |     3             |      4                   
 *               8         |      3        |     5             |      5                           
 *               9         |      3        |     5             |      5                   
 *               10        |      3        |     5             |      5           
 *               11        |      7        |     8             |      8                           
 *               12        |      7        |     8             |      8                               
 *                                                            
 * 
 * 1. We will use a map to store each manager and their list of direct subordinates.
 * 2. We will maintain a maxTime variable to keep track of the Max Total time needed to inform all employees.
 * 3. For each employee, we calculate the time it takes for them to receive the information from their manager plus the time they need to inform their own subordinates.
 * 4. This sum represents the time required for the information to reach all employees under a specific manager.
 * 5. aplyala count karaychay maxTime soo will take 
 *      max(currTime, maxTime)
 *      - currTime asel subordinates la laglela time 
 *      - aplyala addition nahi karaychiye saglyanna kiti time lagtoy tyachi milun
 *      - aplyala bas kiti max Time lagle as in ekhadyala saglyat jast time lagla konti info bhetayla te count karaychy
 * 
 * 
 * Pseudo Code :
 * 
 * // Declare a varible which tracks how much max Time it took to transfer all info to all employee
 * maxTime = 0;
 * 
 * function numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
 * 
 *      initialTime = 0     -> start time kay asel 0 karan headID la info transfer honyasathi 0 time lagtoy he is providing the info 
 * 
 *      Map<Integer, ArrayList> teamMap = new hashmap
 *      
 *      for(i = 0 to n)
 *          
 *          if(!teamMap.contains(manager[i]))
 *              teamMap.put(manager[i], new Arrays)
 *          
 *          // manager[i] == -1 asel mhnje to kay ahe?.. headID ahe so head cha konta manager tr nasel he is sharing information
 *              so apan tyala sodun bakichyanna taku arrayList madhe
 *          if(manager[i] != -1)
 *              teamMap.get(manager[i]).add(i)
 *      
 *      // call dfs function to traverse from all subordinates
 *      dfs(headId, initialTime, teamMap, informTime)
 * 
 *      return maxTime
 * }
 * 
 * functio dfs (currEmployee, timeTakesToReceivedInfo, teamMap, informTime){
 * 
 *      -> currEmployee -> information is provided to the employee or manager 
 *      -> timeTakesToReceivedInfo -> time it takes for employee or manager to receive info 
 *      
 *      // Find maxTime 
 *      maxTime = max(maxTime, timeTakesToReceivedInfo)
 * 
 *      employeeUnderCurrId = teamMap.get(currId)
 *      timeNeedsToInformSubordinates = informTime[currEmployee]
 * 
 *      for(int employee : employeeUnderCurrId){
 *          dfs(employee, timeTakesToReceivedInfo + timeNeedsToInformSubordinates, teamMap, informTime)
 *      }
 * }
 * 
 * 
 */
