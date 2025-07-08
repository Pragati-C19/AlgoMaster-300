import java.util.*;

public class ValidSquare {
    
    // Driver Funcion
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        
        // call helper function to get distance between all two pair of points
        // and store it in int[] array
        int[] distanceCombination = {
            distanceSquare(p1, p2),
            distanceSquare(p1, p3),
            distanceSquare(p1, p4),
            distanceSquare(p2, p3),
            distanceSquare(p2, p4),
            distanceSquare(p3, p4)
        };

        System.out.println(" distanceCombination : " + Arrays.toString(distanceCombination));
        

        // Declare a freqMap and check freq of distance in distanceCombination
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int dist : distanceCombination) {
            
            // add this dist in map
            // if already added increase the freq of it
            freqMap.put(dist, freqMap.getOrDefault(dist, 0) + 1);
        }

        System.out.println(" FreqMap : " + freqMap);


        // If we found freq as 4 and 2 will return true
        if (freqMap.containsValue(4) && freqMap.containsValue(2)) {
            
            System.out.println("    - It's a valid square.. ");
            return true;
        }

        System.out.println("    - It's NOT a valid square.. ");
        return false;
    }

    // Helper Function : to get distance square for points
    private int distanceSquare(int[] a, int[] b) {

        // Distance formula :   d(AB) = (x1 - x2)^2 + (y1 - y2)^2

        // let's declare variable name for (x1 - x2) and (y1 - y2)
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        // take square of it 
        int distAB = (dx * dx) + (dy * dy);

        System.out.println("    - Distance of point A" + Arrays.toString(a) + " and B" + Arrays.toString(b) + " : " + distAB);
        
        return distAB;
    }


    public static void main(String[] args) {

        ValidSquare solution = new ValidSquare();

        int[] p11 = {0,0};
        int[] p21 = {1,1};
        int[] p31 = {1,0};
        int[] p41 = {0,1};
        System.out.println(" Result1 -> " + solution.validSquare(p11, p21, p31, p41) + "\n");   // true

        int[] p12 = {0, 0};
        int[] p22 = {1, 1};
        int[] p32 = {1, 0};
        int[] p42 = {0, 12};
        System.out.println("Result2 -> " + solution.validSquare(p12, p22, p32, p42) + "\n");    // false

        int[] p13 = {1, 0};
        int[] p23 = {-1, 0};
        int[] p33 = {0, 1};
        int[] p43 = {0, -1};
        System.out.println("Result3 -> " + solution.validSquare(p13, p23, p33, p43) + "\n");    // true

    }

}

/*
 * Intuitions :
 
    1. We have given corodinates of four points 
        p1, p2, p3, p4
    2. if 4 points construct square return true
    3. valid square means
        - must have 4 sides with positive length 
        - 4 equal angles (90 degree)
 
 * Pattern :
 
    1. mala tr simple method vattey ki directly Distance gheun ghe two points madhal
        if it's same will return true
 
    2. ek square kasa mhnau shakto apan with distance?
        - 4 sides cha distance same asel
        - 2 diagonals ch distance same asel
            we can't check for 90-degree directly so checking distance of diagonals is right way

    3. aplyala sure nahiye p1, p2, p3, p4 kuthe stored ahet 
        square madhe points kuthehi astil like below

            p1 ---- p2      p2 ---- p3       
            |       |       |       |        
            |       |       |       |        
            p4 ---- p3      p4 ---- p1     
            

        - tyamul will get distance of all combinations of points
        - and store it in an array called distanceCombination

    4. ata tharvaych kasa ki same distance ahe ka nahi te?.. 
        me pratek veles dist[i] == dist[j] tr nahi karu shakt na nested for loop madhe

        - so let's use map here as always jithe duplicates count karayche astil will use freMap
        - freMap madhe apan pratek distance chi freq store karu
        - jr aplyala freq 4 and 2 bhetli then will return true
            ka? bcoz me already bolle square 4 equal sides and 2 equal digonals ni banto
        - otherwise false

    
 
 * Pseudo Code:
 

    function validSquare (p1, p2, p3, p4) {
    
        -> call distance function for 6 combinations and store it in array itself

            int[] distanceCombination = {
            
                distanceSquare(p1, p2),
                distanceSquare(p1, p3),
                distanceSquare(p1, p4),
                distanceSquare(p2, p3),
                distanceSquare(p2, p4),
                distanceSquare(p3, p4),
            }

        -> Declare a freqMap to store freq of all distances 

            for(dist : distanceCombination) 

                - add that distance in freqMap
                    map.put(dist, map.getOrDefault(dist, 0) + 1)

        
        -> check if freq map contains 4 and 2 as value if yes return true 
            if(map.containsValue(4) && map.containsValue(2))
                return true

        -> else return false           
    
    }

    function distanceSquare (a, b) {
    
        - Distance formula :
            d(AB) = (x1 - x2)^2 + (y1 - y2)^2
            
        -> for better understnading let's declare variable name for (x1 - x2) and (y1 - y2)

            dx = a[0] - b[0]
            dy = a[1] - b[1]

        -> get distance square 
            but using ^2 will be complicated in java need to use pow of method
            so simply we can multiply it with itself 

            d(AB) = (dx * dx) + (dy * dy)
    
        -> at the end return d(AB)

    }


 */