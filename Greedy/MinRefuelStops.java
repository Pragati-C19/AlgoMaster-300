import java.util.*;

public class MinRefuelStops {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. we have given target, startFuel, and stations at different positions with gas can be filled there
 * 2. mala eka start point pasun target la hit karaychy
 *      - tyastahi mala adhi startFuel dilay.. tya feul me stations[] madhe eka gas station chya position vr reach karu shakte me
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
 *      feulLeft = startFeul - stations[i][1]
 * 3. ata bagh feul jr rahilach nahi to travel next position tr apan target hit kasa karnar?
 *      if(feulLeft < 0)  -> return -1
 * 4. and feul asel car madhe tr we can go to next position and like that we can travel to end
 *    so apan startFeul madhe station ch feul add karu.. why in startFeul? it's used for checking total feul in car
 *      if(feulLeft > 0)
 *          startFeul += stations[i][2]
 *          milesTraval += stations[i][1]
 * 5. ithe ek gosticha vichar nahi kela me.. 
 *      example startFeul mazyakde 10 ahe tr I need to check ki stations[i][1] == 10 ahe ka te?
 *          asel tr apan direct tithech jau 
 *      nasel tr will check for lesser one with high feul
 * 
 * 6. will use maxHeap to store stations with maxFeul at top
 *      
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * function minRefeulStops(target, startFeul, stations){
 * 
 *      -> Declare variable maxFeul to check how much distance we travelled,
 *          Initial it is equal to startFeul bcoz without refeuling we can go till that much miles
 *          - maxTravel = startFeul
 * 
 * 
 *      -> Declare a maxHeap to store stations with mzxFeul at top
 *          If feuls are same on both stations will store min position distace at top
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
 *                  - check if there any stations je feul sampnyachya agore astil so that we can refuel 
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