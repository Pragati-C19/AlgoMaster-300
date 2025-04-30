import java.util.*;

public class MergeKLists {
    
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. we have given a linkedList list of array which contains multiple list's with different sizes
 * 2. we need to merge them into one
 * 3 also wee need to add them in sorted order hun
 * 
 * 
 * Pattern :
 * 
 * 1st Approach : 
 * 
 * 1. will use a dummy LinkNode to store merge nums
 * 2. will trace curr on dummy
 * 3. first will merge 2 list then will add third list in it 
 * 4. this thing seems a bit off..
 * 
 * 
 * 2nd Approach :
 * 
 * 1. will create a minHeap 
 * 2. add only head of linkedLists
 * 3. at the end create a dummy node one by one and current to trace that dummy node's next
 * 4. till heap get's empty
 *      - pop head from minHeap
 *      - add it to curr.next
 *      - move curr to curr.next
 *      - if u see head's value is not null add it in heap 
 * 5. return dummy.next
 * 
 * Pseudo code :
 * 
 * 
 * function mergeKList(ListNode[] lists){
 * 
 *      -> Declare a minHeap
 *      -> Declare dummy
 *      -> Declare curr = dummy
 * 
 *      -> add only head of each list to the minHeap
 *      for(ListNode head : lists)
 *          minHeap.add(head)
 *      
 *      -> till minHeap is empty 
 *      while(!minHeap.isEmpty())
 *          
 *          smallestNode = minHeap.poll()
 *          curr.next = smallestNode
 *          curr = curr.next
 * 
 *          if(smallestNode.next != null)
 *              minHeap.add(smallestNode.next)
 *      
 *      return dummy.next
 * }
 * 
 * 
 * 
 */