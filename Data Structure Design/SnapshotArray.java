import java.util.*;

public class SnapshotArray {

    // Globally Declare Variables

    
    public SnapshotArray(int length) {
        
    }
    
    public void set(int index, int val) {
        
    }
    
    public int snap() {
        
    }
    
    public int get(int index, int snap_id) {
        
    }


    public static void main(String[] args){

        SnapshotArray solution = new SnapshotArray(3);

        System.out.println("Final Result : ");
        
        solution.set(0, 5);
        System.out.println("  1st Iteration \n"); 
        
        System.out.println("  2nd Iteration : " + solution.snap() + "\n");    // 0

        solution.set(0, 6);
        System.out.println("  3rd Iteration \n");

        System.out.println("  4th Iteration : " + solution.get(0, 0) + "\n");    // 5

    }

}

/*
 * 
 * Intuition :
 * 
 * 1. SnapshotArray(int length) 
 *      here initialize array like data structure whose length is given length and initial value as 0
 * 2. void set(index, val)
 *      set element at the given index to be val
 *      - need clarity on where I need to set it?.. 
 *        the array we are declaring at that index or in array storing it like [0, 5]
 * 3. int snap()
 *      take snapshot of array means maybe we have to create temp array to store index, val, snapId
 *      return snapId : the total numbe of times we called snap() - 1
 * 4. int get(index, snap_id)
 *      return value at given index at the time we took snapshot
 *      - mhnje apan jevha snapshot kadhlay to array madhun value ghaychi na ki jya array madhe set kartoy apan
 * 
 * 
 * Pattern :
 * 
 *  ~ My Thinking
 * 
 *      - SnapShot increase honar ahe like 0 to max
 *      - jr me Hashmap use kela to store <SnapId, Array>
 *      - mala array lagelach kahihi zal tri.. me fact kay karte to array jasa chya tasa hashmap madhe add karte
 *      - jevha mala check karaych asel ki snapID la Array madhe ya index la kay hot I can do it right?
 *      - snapId key nasel tr me return 0 karte
 * 
 * Now think abt how will u write that code
 * 
 * 1. Globally declare variable
 *      - map<Integer, int[]> snapshotMap : will use int[] or List<Integer> depends on what's smooth for me
 *      - int[] setArray
 *      - int snapId
 * 
 * 2. SnapshotArray(int length)
 *      - snapshotMap = new Hashmap
 *      - snapId = -1;
 *      - setArray = new int[length]
 *      - for int[] we don't need to manually add 0 at start, but in List<Integer> we have to spacify it 
 * 
 * 3. void set(index, val)
 *      - setArray[index] = val
 * 
 * 4. int snap()
 *      - snapId++
 *      - snapshotMap.put(snapId, setArray)
 *      - return snapId
 * 
 * 5. int get(index, snapId)
 *      - if(!map.contains(snapId)) return 0
 *      - int value = map.get(snapId)[index]
 *      - return value
 * 
 * 
 * 
 * 
 */