import java.util.*;

public class MergeTwoLists {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
    }

    // Helper function to print the list (for debugging)
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        MergeTwoLists solution = new MergeTwoLists();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        System.out.println("First list:");
        solution.printList(l1);

        System.out.println("Second List:");
        solution.printList(l2);

        ListNode mergeList = solution.mergeTwoLists(l1, l2);

        System.out.println("Merge Result: ");
        solution.printList(mergeList);
    }
}

/**
 * 
 * Intuitions :
 * 
 * 1. Two List has given need to merge them into one sorted list
 * 2. Since both lists are already sorted, we can compare nodes one by one and
 * attach the smaller node to the result.
 * 
 * Pattern :
 * 
 * 1. Dummy Node to track head
 * 2. use a pointer to build the new list
 * 3. compare curr from lists
 * - take smaller node and link it to curr
 * - move the pointer curr forward
 * 4. repeat step 3 until one of the lists is empty
 * 5. return dummy.next
 * 
 * 
 * Psuedo Code :
 * 
 * function mergeTwoLists(list1, list2):
 * Create a dummy node to act as the start
 * Set current = dummy
 * 
 * while list1 is not null and list2 is not null:
 * if list1.val < list2.val:
 * current.next = list1
 * list1 = list1.next
 * else:
 * current.next = list2
 * list2 = list2.next
 * 
 * Move current forward
 * 
 * # Attach the remaining nodes
 * if list1 is not null:
 * current.next = list1
 * if list2 is not null:
 * current.next = list2
 * 
 * Return dummy.next (skipping dummy node)
 * 
 * 
 */
