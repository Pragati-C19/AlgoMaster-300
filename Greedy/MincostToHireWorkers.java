import java.util.*;

public class MincostToHireWorkers {
    
}

/*
   
   
 !    todo: Solve this que later.. keep this in pending for now
 ?    Took Help from below video
        https://www.youtube.com/watch?v=kxR52OB_I8k&ab_channel=codestorywithMIK
   
   
 *  Intuitions :
   
   1. We need to form paid worker group of k workers
   2. To hire people we need to follow below conditions :
        - Every worker must be paid at least there minimun wage or more
        - If we form a group of worker then each worker's pay must be directly proportional to their quality.
        - This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker. 
   3. return the least amount of money needed to form a paid group
   
   
 *  Pattern :
   
 ^  Fresh Thinking:
   Optimal Approach :
   
    - suppose aplyakde Ratio array ahe { [r0, q0], [r1, q1], [r2, q2], [r3, q3], [r4, q4] } and k = 3
        q can be shuffled ithe just example ghetey me
    - ith manager sathi -> ithe i kay asel?.. k - 1 right? adhi apan kelel he solve ka mhnun or u can check video
        managerRatio ghetla
        and tyala apan minimun ration vale workers chya quality sobt multiply karat hoto
        te zal ki then tyala add karat hoto total payment sathi
        (mr * q0) + (mr * q1) + (mr * q2)
        mr * (q0 + q1 + q2)     -> mr common kadhla
    - i+1th manager sathi kay kel asat?
        managerRatio ghetla
        (mr' * q0) + (mr' * q1) + (mr' * q2) + (mr' * q3)
        mr' * (q0 + q1 + q2 + q3)     -> mr' common kadhla
    - so ithe bagh me second madhe fact mr and q3 add hotoy baki sagal tr same ahe, old sum tr samech ahe
    - ata me heap madhe kay karnar quality add karnar fact
        jr maxHeap chi size jr > k zali tr top element me kadhun takel
        ka quality ch add kartey me heap madhe?.. workersPay nahi like before?
        karan me ata full sum quality chi ghetey and mr tya sum madhe end la add kartey tyamul
    
        
    Pseudo Code :

    function (quality, wage, k) {
    
        n = quality.length
        qualitySum = 0

        -> Create a maxHeap to store qualities max quality on top
            maxHeap = pq<>((a, b) -> Integer(b - a))
    
        -> Create a Array to store quality and ratio of wage/quality of all workers
            Wage store karaychi garaj nahiye itki so nahi memory allocate karat ahe tyasathi ajun

            workerRatio = int[n][2]   
   
            for(i = 0 to n)
                ratio = wage[i] + quality[i]
            
                workerRatio[i][0] = quality[i]
                workerRatio[i][1] = ratio

        -> Sort an array in ascending so that it can fullfill managersRatio >= workersRatio
            Arrays.sort(worker, (a, b) -> Integer.compare(a[1] - b[1])) 
        
        -> let's take i = k - 1 jyani < k vale sagle workers manager nahi bannar karan group form honarch nahi tyancha

            for(manager = k-1 to n)

                managerRatio = workersRatio[manager][1]

                -> quality add keli heap madhe
                maxHeap.add(workersRatio[manager][0])

                -> sum madhe me workers chya sum add karat gele
                qualitySum += workersRatio[manager][0]

                -> jr mazi heap size k pekshya vadhli tr me pop karel top quality and and sum madhun remove karel ti
                    if(maxHeap.size > k)
                        top = maxHeap.poll
                        qualitySum -= top
                
                -> ata me currWorkPay kadhun ghete
                currWorkPay = managerRatio * qualitySum

                result = min (result, currWorkPay)

        return result
    }

   

   
 ^  Old Thinking :
   
   1. Trace Example : Doing it with help of discussion prompt under the que
        
       1.  quality = [10,20,5], wage = [70,50,30], k = 2
   
        - Workers 0 and 2 were selected
        - They should be paid at the same rate (70 or 30). 
        - Worker 0 has twice the quality (10) compared to worker 2 (5), so he should be paid double the price of worker 2.
        - If we choose 30 as the base rate, then we pay 30 to worker 2, and 30 * 2 = 60 to worker 0, which is lower than his minimum pay, and We cant pay any worker below his minumun wage.
        - So we have to choose 70 as the base rate. Then, we have 70 for worker 0, and 70/2 = 35 for worker 2.
  
    
 ^  Fresh Thinking :
   Appraoch 1 : Brute Force 
   
   1. Trace Example :
        quality = [10,20,5], wage = [70,50,30], k = 2
   
        - aplyala mahitiye wage/quality cha ratio equal karaychay group madhlya workers cha so can I write it like that?
            
                       wage[i]       wage[i+1]
                    ------------ = --------------
                     quality[i]     quality[i+1]
        
        - In example me jr worker 0 la 70 detey rs karan tyala titke min dene imp ahe, tr me ek base payment set karte 70 mhnun
        - ata mala bakichyanch payment kadhaych asel tr me kay karel
                  
                 Worker 1                    Worker 2 
   
                70       x                  70       x
              ------ = ------             ------ = -----
                10       20                 10       5
            
                   x = 140                     x = 35
   
        - That means apan jr worker 0 sathi min wage select kel tr worker 1 sathi 140 dyave lagtil and worker 2 sathi 35
        - jr pahil tr samjtay ki saglya workers che min wage tr cross hotay or equal payment bhettay tyanna
        - ata fact check karyachy ahe ki kontya worker sobt Worker 0 cha group form karnar jyanchya payment chi sum minimum yeil
        - that's why it is 70 + 35 = 105
   
   2. After tracing que I understand that we need to set base payment accordingly Worker with Highest wage
        - jyach wage high ahe tyala apan manager consider karun tyachya payment chya according other team members la pay karu apan ata
   
   3. Apan jo ration lihila hota we can write it like below too
   
        - To ratio kasa asel in term of manager and team-member 
           
                      wage[team-member]         wage[manager]
                   ----------------------- = --------------------
                     quality[team-member]      quality[manager]         
            
   
        - wage eka side la kele and qualitye eka side la, consider apan jo base Rate set karan mhntoy to apla manager ahe
          
                    wage[team-member]        quality[team-member]        
                  --------------------- = --------------------------
                      wage[manager]            quality[manager]            
    
        
        - ata mala unknow kay asnar ahe?.. team memeber la payment kiti karaych te right? tr me tyala one side thevte
            
                                         wage[manager]
                 wage[team-member] = --------------------- x quality[team-member]
                                        quality[manager]
   
        - doesn't it looks like -> now u know why ratio x quality
            payment = ratio[manager] * wage[team-memeber]
                
   4. tr ya approach madhe kay karnar apan?
   
        - first apan ek ratio ghenar and tyala remaining workers sobt multiple karnar
        - jr ans tya workers chya wage pekshya kami asel tr we can't take that worker in our group
        - and ans jr max or equal to worker's wage asel tr we can consider that worker
        - check karaych apan je paid kartoy workers la tyachi sum minimum ahe ka te that other?.. asel tr return only that 
   
   5. Pseudo Code :
   
   function (quality, wage, k){
        
        n = quality.length
        group = new ArrayList   -> To store all workersPay for that manager
        result = 0              -> it will store min value
   
        maxHeap = pq<>()        -> To get min payment values at top
   
        -> first ek manager set karu
        for(manager = 0 to n)
            ratio = wage[i] / quality[i]
   
            for(worker = 0 to n)
                
                workersPay = managerRatio * quality[worker]
                
                if(workersPay >= wage[worker]) 
                    group.add(workersPay)
   
           -> jr group madhe kahich add nasel tr kay karnar? continue karu for next manager
            if(group.size < k)  -> continue
             
           -> add this values of group in Heap to get k minimun payments to add
            maxHeap.addAll(group)
   
           -> ata maxHeap madhun k values pop out karun sum madhe add karat ja
                for(i = 0 to k)
                    top = maxHeap.poll()
                    sum = sum + top
                
           -> result = min(result, sum)
   
        return result
   }
   
   
   6. Improvements in above code
   
   ~ ithe me workersPay calculate kartey mg tyala wage she compare kartey tyasathi again ek for loop lavala lagtoy 
            for(worker = 0 to n)      
                workersPay = managerRatio * quality[worker]
                if(workersPay >= wage[worker]) 
                      group.add(workersPay)
   
       - yala apan optimize karu shakto na, bagh me ratio ghetanna start la kay mhnle hote manager and team-memeber cha?
            it looks like ratio[team-member] = ratio [manager]
   
                      wage[team-member]         wage[manager]
                   ----------------------- = --------------------
                     quality[team-member]      quality[manager]   
   
       - tr me ya for loop la change karu shakte? by changing it too
            me fact tya if loop madhe workersPay madhun quality[worker] la = chya palikde nel
             
                if(managerRatio >= workersRatio)
                    
   
   7. Pseudo Code : More optimal way
   
   function (quality, wage, k){
        
        n = quality.length
        group = new ArrayList   -> To store all workersPay for that manager
        result = 0              -> it will store min value
   
        maxHeap = pq<>()        -> To get min payment values at top
   
        -> Creating a Ratio array to store wage/quality ratio of all workers
        workerRatio = int[n][3]   
   
        for(i = 0 to n)
            ratio = wage[i] + quality[i]
        
            workerRatio[i][0] = wage[i]
            workerRatio[i][1] = quality[i]
            workerRatio[i][2] = ratio
   
        -> Sort this workerRatio array, to easily get min or max ratios according to our requirement
        Arrays.sort(worker, (a, b) -> Integer.compare(a[2] - b[2]))
   
        -> array sort asel by ascending order mhnje small values at start
            managerRatio mala max havay or equal havay other workersRatio pekshya
            and mala k number of workers havet group madhe suppose k = 4 asel tr u think me i = 0, 1, 2 ghetla manager tr work karel?
            so to optimized for loop and not taking so much values apan i = k - 1 la start karu shakto?
            k is 1th-indexed 
   
        for(manager = k - 1 to n)
            
            managerRatio = workerRatio[manager][2]
    
            for(worker = 0 to <= manager)      -> karan aplyala small ratio havay so apan tyatun fact check kartoy so smallest asel will add it in heap
                
                -> yeah mala vatal hote he lihaychi garaj nahiye but lihav lagel bcoz yeah aplyala wage of worker according to magaers pay nahi mahitye
                    workersPay = managerRatio * workersRatio[worker][1]
   
                -> ata ithe nahiye garaj check karaychi if loop ki ratio jast ahe ka kami te.. karan apan already manager k-1 vapsun ghetoy
                    group.add(workersPay)
            
           -> ithe te group.size < k val pn lavaychi garaj nahiye karan apan manager already k - 1 pasun ghetoy so titke tr astilch
   
           -> add this values of group in Heap to get k minimun payments to add
            maxHeap.addAll(group)
   
           -> ata maxHeap madhun k values pop out karun sum madhe add karat ja
                for(i = 0 to k)
                    top = maxHeap.poll()
                    sum = sum + top
                
           -> result = min(result, sum)
   
        return result
   }
    
   
   
           
     
 */
