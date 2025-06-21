import java.util.*;

public class MinSessions {
    
    // Globally Declare variables

    
    // Driver Function 
    public int minSessions(int[] tasks, int sessionTime) {
        
        return 0;
    }

    // Recursion Function : to check if all tasks are done or not
    private int dfs (int mask, int timeLeft, int[] tasks, int sessionTime) {

        return 0;
    }

    public static void main(String[] args){
      
        MinSessions solution = new MinSessions();

        // First Example
        int[] tasks1 = {1,2,3};
        System.out.println("Result1 -> " + solution.minSessions(tasks1, 3) + "\n");   // 2

        // Second Example
        int[] tasks2 = {3,1,3,1,1};
        System.out.println("Result2 -> " + solution.minSessions(tasks2, 8) + "\n");   // 2
        
        // Third Example
        int[] tasks3 = {1,2,3,4,5};
        System.out.println("Result3 -> " + solution.minSessions(tasks3, 15) + "\n");   // 1
        
    }

}

/*
 * Intuitions :
 
    1. there is an n tasks for us
    2. task time is represented as an integer array tasks
        where i'th task takes task[i] hrs to finish
    3. sessionTime will tell max Time allowed in one work session
    4. work session is when yo work for at most sessionTime consecutive hrs and then take take break
    5. conditions :
        if we start a task we must finish it in that work session
        we can start new task after finishing prev one
    6. return minimum number of work session needed to finish all the tasks
 
 
 * Pattern :
 
    1. we need to add task in sessions such that 
        - each session's sum < sessiontime
        - total number of sessions is min
    2. Think of each session like a bin
    3. Will use bitmask such as
        - if bit = 1 then task i is done
    4. apan ekhada task add kela tr aplyala sessionTime madhun tya task cha time substract karava lagel
        jyani samjel kiti remianing time ahe baki te
    5. aplyala vichar karava lagel ki unvisited task add karaychet
        - jr remainingSession Time < sesssiontime asel
            tr will add task in that session
        - if not then apan new session start karu
    
    ^ Dry Run

               task = [1, 2, 3]
                  n = 3
        sessionTime = 3

        - use bitmask to check which tasks are already done
            Each bit position = whether task i is done (1) or not (0).
            means 
                000 - no task done
                001 - only 0'th task done
                010 - only 1'th task done
                111 - all task done

        - start from no task done and no time left in session so apan new task start karu
            dfs(000, 0)

        - dfs(000, 0)
            try task 0 : 
                timeTakes = task[0] = 1  
                timeTaken <= sessionTime  -> 1 <= 3
                mask = 001
                timeleft = 3-1 = 2

            call dfs(001, 2)
                try task 1 as task 0 is done
                    timeTakes = task[1] = 2  
                    timeTaken <= remainingTime  -> 2 <= 2
                    mask = 011
                    timeleft = 2-2 = 0

                call dfs(011, 0)
                    try task 2 but it does not fit in session as timeLeft is 0

                    - so will start new session for task 2 
                        task 2nd time is 3 hrs

                        timeTakes = task[2] = 3  
                        timeTaken <= sessionTime  -> 3 <= 3
                        mask = 111
                        timeleft = 3-3 = 0
 
 * Pseudo Code :
 
    Function minSessions(tasks, sessionTime):
        - n = number of tasks
        - Initialize memoization map
        - Call dfs(mask = 0, timeLeft = 0)
        - Return result

    Function dfs(mask, timeLeft):
        - If all tasks are done (mask has all bits 1): return 0

        - If memo contains (mask, timeLeft): return saved result

        - Try each uncompleted task:
            - If task can fit in current session:
                - result = min(result, dfs(mask | (1 << i), timeLeft - tasks[i]))
            - Else (doesn't fit):
                - result = min(result, 1 + dfs(mask | (1 << i), sessionTime - tasks[i]))

        - Save result in memo and return it


 */