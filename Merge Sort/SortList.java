import java.util.*;

public class SortList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode sortList(ListNode head) {
        // If list is empty or has one node, it's already sorted
        if (head == null || head.next == null) return head;

        // Divide : Split the list into two halves
        ListNode midIndex = findMiddle(head);
        ListNode rightHalf = midIndex.next;
        midIndex.next = null;

        // Recursion : sort both halves
        ListNode leftSorted = sortList(head);
        ListNode rightSorted = sortList(rightHalf);

        ListNode result = mergeTwoLists(leftSorted, rightSorted);

        return result;
    }

    public ListNode findMiddle(ListNode head){
        // Find the middle of the list
        ListNode slow = head;
        ListNode fast = head;

        // Move fast twice as fast as slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);    // Dummy Node for result
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            
            if (list1.val < list2.val ) {
                curr.next = list1;
                list1 = list1.next;
            }
            else {
                curr.next = list2;
                list2 = list2.next;
            }

            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        }

        if (list2 != null) {
            curr.next = list2;
        }

        return dummy.next;
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
        SortList solution = new SortList();

        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(-1);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(4);
        l2.next.next.next.next = new ListNode(0);

        ListNode sortList1 = solution.sortList(l1);
        ListNode sortList2 = solution.sortList(l2);

        System.out.println("Sort Result for list1: ");
        solution.printList(sortList1);

        System.out.println("Sort Result for list2: ");
        solution.printList(sortList2);
    }
}

/*
 * 
 * intuitions :
 * 
 * 1. will use merge sort in linked list
 * 2. bcoz it will have time complexity as O(n log n)
 * 3. In merge sort we use Divide and conquer technique, it is best for
 * linkedlist
 * 
 * Pattern :
 * 1. Divide:
 * - Find the middle of the list using fast and slow pointers (slow moves 1
 * step,
 * fast moves 2).
 * - Split the list into two halves.
 * 
 * 2️. Conquer:
 * - Recursively sort the left and right halves.
 * 
 * 3️. Merge:
 * - Merge the two sorted halves using a helper function (like merging two
 * sorted
 * arrays).
 * 
 * Pseudo Code :
 * 
 * function sortLinkedList(head):
 * # Base case: If list is empty or has one node, it's already sorted
 * if head is null or head.next is null:
 * return head
 * 
 * # Step 1: Split the list into two halves
 * mid = findMiddle(head)
 * rightHalf = mid.next
 * mid.next = null
 * 
 * # Step 2: Recursively sort both halves
 * leftSorted = sortLinkedList(head)
 * rightSorted = sortLinkedList(rightHalf)
 * 
 * # Step 3: Merge the two sorted halves
 * return mergeTwoLists(leftSorted, rightSorted)
 * 
 * --------------------------------------------------
 * 
 * function findMiddle(head):
 * slow = head
 * fast = head
 * 
 * # Move fast twice as fast as slow
 * while fast is not null and fast.next is not null:
 * slow = slow.next
 * fast = fast.next.next
 * 
 * return slow
 * 
 * --------------------------------------------------
 * 
 * function mergeTwoLists(list1, list2):
 * Create a dummy node to act as the start
 * Set current = dummy
 * 
 * # Compare elements from both lists
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
 * return dummy.next # Return the merged list (skip the dummy node)
 * 
 * 
 * 
 */
