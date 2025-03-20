import java.util.*;

public class SortList {

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
