import java.util.*;

public class CanCompleteCircuit {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
    }

    public static void main(String[] args){

        CanCompleteCircuit solution = new CanCompleteCircuit();

        int[] gas1 = {1,2,3,4,5};
        int[] cost1 = {3,4,5,1,2};
        System.out.println("Result 1 : " + solution.canCompleteCircuit(gas1, cost1));

        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};
        System.out.println("Result 2 : " + solution.canCompleteCircuit(gas2, cost2));

    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. gas[] - > tells how much gas you get at station i
 * 2. cost[] -> tells how much gas u need to go to next station
 * 3. return the point from which u can complete the whole circle  
 * 4. Car tank jr maza 0 zala mhnje I gon't have gas left start from next station
 * 
 * 
 * Pattern :
 * 
 * 1. can only start with the gas station whose gas[i] > cost[i]
 * 2. if tank <= 0 will stop the loop for that i and skip all visited prev Stations
 * 3. like jr me asa mhnle chalo start karu i index pasun tr tyacha end baghaycha kuthparynt hotoy 
 *      - jr to end == start asel -> return that start
 * 
 * 
 * Pseudo Code :
 * 
 *  1. Declare variables
 *      - start = 0         -> It will trace Index from which we can start  
 *      - tank = 0          -> It will check totalTank for traveling
 *      - currTank = 0      -> it will check currIndexTank, it's depend on start point
 * 
 * 2. So out basic idea is will try every i and check it's difference 
 *      - if that diff < 0 -> that means we can't travel to next station so let's start with next station
 *      - else will check next index and store that difference in currIndexTank
 *  
 * 
 */