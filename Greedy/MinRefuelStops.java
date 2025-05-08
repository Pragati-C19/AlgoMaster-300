import java.util.*;

public class MinRefuelStops {
    
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        
        int n = stations.length;
        int stationsIndex = 0;
        int maxTravel = startFuel;
        int refuelingCount = 0;

        // Declare a maxHeap to store values of station
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });

        while (maxTravel < target) {
            
            // used while bcoz once we have visited any station I don't want to recheck it in next while(maxTravle < target) loop
            while (stationsIndex < n && stations[stationsIndex][0] <= maxTravel) {
                
                // Adding all Stations which are within maxTravel now
                maxHeap.add(stations[stationsIndex]);
                System.out.println("       - maxHeap after adding stations at index " + stationsIndex + " : " + Arrays.deepToString(maxHeap.toArray()));

                stationsIndex++;
            }

            if (maxHeap.isEmpty()) {
                
                System.out.println("    maxHeap is empty, means we can't travel more...");
                return -1;
            }

            int[] top = maxHeap.poll();
            System.out.println("    -> Top Popped Out station is : " + Arrays.toString(top));

            maxTravel += top[1];
            refuelingCount++;
            System.out.println("    Refeuling Count till max Distance we travel " + maxTravel + " : " + refuelingCount);

        }

        return refuelingCount;
    }

    public static void main(String[] args){

        MinRefuelStops solution = new MinRefuelStops();

        int target1 = 1;
        int startFuel1 = 1;
        int[][] stations1 = {};
        System.out.println("Result1 -> " + solution.minRefuelStops(target1, startFuel1, stations1) + "\n");     // 0

        int target2 = 100;
        int startFuel2 = 1;
        int[][] stations2 = {{10, 100}};
        System.out.println("Result2 -> " + solution.minRefuelStops(target2, startFuel2, stations2) + "\n");     // -1

        int target3 = 100;
        int startFuel3 = 10;
        int[][] stations3 = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        System.out.println("Result3 -> " + solution.minRefuelStops(target3, startFuel3, stations3) + "\n");     // 2

    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. we have given target, startFuel, and stations at different positions with gas can be filled there
 * 2. mala eka start point pasun target la hit karaychy
 *      - tyastahi mala adhi startFuel dilay.. tya fuel me stations[] madhe eka gas station chya position vr reach karu shakte me
 *      - tithun fuel add kel ki me next position vr jail and so on till I hit target
 * 3. ata yar kahi conditions ahet itka easily tr trace nahi honar
 *      - max fuel litter madhe ahe and it cost 1 litter to go 1 miles
 *          so startFuel = 1 asel and stations[[10, 100]]
 *          asa asel tr mala 10 miles mhnje fuel = 10 litters lagtil to reach station
 *      - or jr target dil asel 100 tr jasa jasa positions vr jail me tasa maz fuel kami hoil
 * 4. return mala minium time me kiti veles stations vr jaun refueling karel tr sangaychy
 * 
 * 
 * Pattern :
 * 
 * 1. mala jr kiti miles travel kel tr baghaych asel tr it's debepend on station's position
 *      mileTravel += stations[i][1]
 * 2. fuel kami honar ahe stations paynt or target parynat jail paryant so I need
 *      fuelLeft = startFuel - stations[i][1]
 * 3. ata bagh fuel jr rahilach nahi to travel next position tr apan target hit kasa karnar?
 *      if(fuelLeft < 0)  -> return -1
 * 4. and fuel asel car madhe tr we can go to next position and like that we can travel to end
 *    so apan startFuel madhe station ch fuel add karu.. why in startFuel? it's used for checking total fuel in car
 *      if(fuelLeft > 0)
 *          startFuel += stations[i][2]
 *          milesTraval += stations[i][1]
 * 5. ithe ek gosticha vichar nahi kela me.. 
 *      example startFuel mazyakde 10 ahe tr I need to check ki stations[i][1] == 10 ahe ka te?
 *          asel tr apan direct tithech jau 
 *      nasel tr will check for lesser one with high fuel
 * 
 * 6. will use maxHeap to store stations with maxFuel at top
 *      
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * function minRefuelStops(target, startFuel, stations){
 * 
 *      -> Declare variable maxFuel to check how much distance we travelled,
 *          Initial it is equal to startFuel bcoz without refueling we can go till that much miles
 *          - maxTravel = startFuel
 * 
 * 
 *      -> Declare a maxHeap to store stations with mzxFuel at top
 *          If fuels are same on both stations will store min position distace at top
 *          - maxHeap = pq<>((a, b) -> b[1] - a[1], if(a[1] == b[1]) a[0] - b[0])
 * 
 *      -> We need a variable to count how many times we have refueled 
 *          - refuelingCount = 0;
 * 
 *      -> if our maxTravel will be greater or equal to target means we have hit the target so we can return the count
 *          but till then we have to go to next positions and refuel if necessary
 *          - while(maxTravel < target)
 *              
 *              - for(i = 0 to stations.length)
 *                  
 *                  - check if there any stations je fuel sampnyachya agore astil so that we can refuel 
 *                      asel tr te sagle heap madhe add kar apan fact tech station gheu adhi jyanchyakde jast fuel ahe
 *                      
 *                      if(stations[i][0] <= maxTravel)
 *                          pq.add(new int{stations[i][0] , stations[i][1]})
 *                  
 *             - when we don't have anything to store in heap that means we can't travel more now so return 
 *                  if(maxHeap.isEmpty) -> return -1
 * 
 *             - at end we need to check how much distance we have traveled and refueling count
 *                  - if(!maxHeap.isEmpty) 
 *                          top = maxHeap.poll()
 *                          fuelRemain = startFuel - top[0]
 *                          
 *                          if(fuelRemain > 0)
 *                              startFuel = fuelRemain + top[1]
 *                              maxTravel = maxTravel + startFuel
 *                              reFuelingCount++
 *                  
 *      -> return reFuelingCount
 * 
 * }
 * 
 * 
 * Improvements In pseudo Code :
 * 
 * 1. for(i = 0 to stations.length)
 *    why?
 *        - will use while loop here.. 
 *        - bcoz if we use for loop it will always check array from 0'th index
 *        - we don't want it everytime
 *        - we just want to check next index after adding previous once in heap 
 * 
 *                  while (index < stations.length)
 *                      if(stations[index][0] <= maxTravel)
 *                          maxHeap.add(stations[index])
 *                      index++
 * 
 * 2. at end we need to check how much distance we have traveled and refueling count
 *   why?
 *       - You do not subtract fuel for the station distance here.
 *       - Because you already traveled that distance before adding that station’s fuel to the heap.
 *      - You're only deciding when to take that fuel — the cost to get there was paid earlier.
 *      
 *                  top = maxHeap.poll()
 *                  maxTravel = maxTravel + top[1]
 *                  reFuelingCount++
 *                          
 * 
 * 
 * 
 */