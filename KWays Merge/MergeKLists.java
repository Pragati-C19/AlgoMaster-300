import java.util.*;

public class MergeKLists {
    

    // Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
 
    // Driver Function
    public ListNode mergeKLists(ListNode[] lists) {
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>();

        // Add only head to minHeap
        for (ListNode listHead : lists) {
            minHeap.add(listHead);
        }

        // print minHeap in console like this
        System.out.println("Initial Heap : " + Arrays.deepToString(minHeap.toArray()));

        while (!minHeap.isEmpty()) {
            
            ListNode smallestNode = minHeap.poll();
            System.out.println("    Smallest Node is " + smallestNode);
            
            curr.next = smallestNode;
            curr = curr.next;

            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }

        }

        return dummy.next;

    }


    // HElper Function : to build a list
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
    
    // Helper Function : to print the list (for debugging)
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args){

        MergeKLists solution = new MergeKLists();

        // Example 1
        int[][] input1 = {
            {1, 4, 5},
            {1, 3, 4},
            {2, 6}
        };

        ListNode[] lists1 = new ListNode[input1.length];
        for (int i = 0; i < input1.length; i++) {
            lists1[i] = buildList(input1[i]);
        }

        ListNode mergedHead1 = solution.mergeKLists(lists1);
        printList(mergedHead1);
    }

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