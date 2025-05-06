import java.util.*;

public class LeastInterval {
    
}


/*
 * 
 * //? Check out this videos
 *      https://youtu.be/rYh-Kkbzsnw?si=tnFLLg-6Q8DgBrqb
 * 
 * 
 * Intuition :
 * 
 * 1. Tasks[] and n is given
 * 2. u need to return minimum number of CPU intervals required to complete all tasks.
 * 3. Task can be completed in any order
 * 4. there has to be a gap of at least n intervals between two tasks with the same label.
 *      - mhnje jr suppose task A chalu kela tr at least n time lagel to start A task again
 * 
 * 
 * Pattern :
 * 
 * 1. Trace Example :
 * 
 *   # tasks = ["A","A","A","B","B","B"], n = 2
 *      
 *      - bagh ithe kay mhnl ahe apan ek task complete zala ki 2 interval lagtil madhe
 *      - me first A task chalu kela ata parat next A chalu karnyasathi mala time 2 hoilapynt thambav lagel
 *              A _ _ A _ _ A
 * 
 *      - ata yat 2 free space ahe tevha CPU asach basel tr apan dusra kahi task asel tr to tithe add karu
 *      - we have one more diff task B apan to tya time madhe add karu
 *              A B _ A B _ A B
 * 
 *      - bagh B pn 2 interval nantr chalu honar ahe.. tyamul to _ A nantr yetoy bagh
 *      - ata AB sodun ajun kota task nahiye so remaining spaces are in idle state
 *      - So ans is 8
 * 
 *   # tasks = ["A","C","A","B","D","B"], n = 1 
 * 
 *      - chalo ithe pn same apan adhi sagle A task set karun gheu ekach interval nantr apan same task gheu shakto
 *              A _ A 
 * 
 *      - Ata C add karte
 *              A C A  
 *      
 *      - Ata B add karu -> B 2 hote mhnun madhe el idle state add keli konta task asle tr tithe add karel
 *              A C A B _ B
 *      
 *      - ata D add Karte
 *              A C A B D B
 * 
 *      - So ans is 6
 * 
 * 
 * Approach 1 :
 * 
 * 1. We need to know konta task kiti veles alay.. mhnje we need freq of each task
 * 2. Apan jyachi saglyat jast freq ahe tila apan first ghenar
 * 3. Apan kay karu sagle je task astil te apan MaxHeap madhe taku.. Highest freq at top 
 * 4. while (!maxHeap.isEmpty) -> joparynt empty hot nahi apan pop out karu tasks
 * 5. for(i = 0 to n + 1) 
 *      - why n + 1 ? -> karan mala ek task excute kelayve n time thambav lagtay 
 *      - example jr me A task complete kartey tr mala n time thamban ahe.. but me A pop out kelay na heap madhun
 *      - so to A + n that means 1 + n veles heap madhun pop out hoil
 *      - I think n ch lagel me already i = 0 kartey so
 * 6. jo task pop out kelay tyachi freq ek ni kami karaychi and store it in an array till apala for loop complete hot nahi
 * 7. intervalCount++ pn kr karan aplyala time find karaychay kiti veles kelay te
 * 8. ithe ajun ek idle state of cpu chya veles kay karnar?
 *      - apan jevha for loop lavnar ahe tevha task asel maxHeap madhe tr add karu nasel tr kay? apan tri time tr increse karnar hoto
 *      - so tyasathi ek Base Case lihu in for loop 
 *      - if(!maxHeap.isEmpty) -> intervalCount++
 *      - else adhi je lihile points te kr
 * 8. for loop end zala ki je pn task execute karun freq kami keliye add it in maxHeap again
 * 9. maxHeap tyala rearrange karun gheil and top la parat maxFreq vala yeil task
 * 10. soo like that while loop again check karell maxHeap empty nahiye so poll() till n 
 * 11. after ending of while loop will return the intervalCount
 * 
 * 
 * Pseudo Code :
 * 
 * function leastIntervals(tasks, n) {
 * 
 *      -> Store freq in map
 *          map = new HashMap
 * 
 *      -> Will add map's key and value in heap with Map.Entry<Integer, Integer>[], it will store entry 
 *      maxHeap = pq<>((a, b) -> Integer.(b.getValue() - a.getValue()))
 * 
 *      -> Add value in map and then heap
 * 
 *      for(i = 0 to tasks.length)
 *          - map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1)
 *          
 *      for(Map.Entry<Integer, Integer> entry : map.entrySet())
 *          - maxHeap.add(new Map.Entry<Integer, Integer>{entry.getKey(), entry.getValue()})
 *          - I don't know if this symbol will work will try it out first
 *          - but core is we need to add those values from map to maxHeap
 * 
 * 
 *      while(!maxHeap.isEmpty)
 *          
 *          for(i = 0 to n)
 *              
 *              if(maxHeap.isEmpty)     -> idle state
 *                  intervalCount++
 *              
 *              else
 *                  maxHeapTop = maxHeap.poll()
 *                  
 *                  currMap.put(maxHeapTop.getKey(), maxHeapTop.getValue() - 1)
 * 
 *                  if(maxHeapTop.getValue() == 0)
 *                      currMap.remove(maxHeapTop.getKey())
 * 
 *                  intervalCount++
 * 
 * 
 *          -> after for loop end add those values in currMap into the heap again
 * 
 *              for(Map.Entry<Integer, Integer> entry : currMap.entrySet())
 *                  - maxHeap.add(new Map.Entry<Integer, Integer>{entry.getKey(), entry.getValue()})
 * 
 * 
 *       -> after while loop ends
 *          return intervalCount
 * 
 * }
 * 
 * 
 * 
 */