class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        // Step 1: Set up a dummy node to handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Move to the node before 'left'
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // Step 2: Reverse the sublist between 'left' and 'right'
        ListNode current = prev.next;
        ListNode next = null;

        for (int i = 0; i < right - left; i++) {
            next = current.next;
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }

    // Helper to print the list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }

  
    public static void main(String[] args) {
        ReverseBetween solution = new ReverseBetween();

        // Test Case 1
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result1 = solution.reverseBetween(head1, 2, 4);
        printList(result1); // Output: 1 -> 4 -> 3 -> 2 -> 5

        // Test Case 2
        ListNode head2 = new ListNode(5);
        ListNode result2 = solution.reverseBetween(head2, 1, 1);
        printList(result2); // Output: 5
    }
}

// ✅ ListNode class provided by Leetcode
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}



/****
 * 
 * 
 * 
 * def reverse_between(head, left, right):
    # Edge case: single node or no need to reverse
    if not head or left == right:
        return head

    # Step 1: Move to the 'left-1' node
    dummy = ListNode(0)
    dummy.next = head
    prev = dummy
    for _ in range(left - 1):
        prev = prev.next

    # Step 2: Reverse the sublist between 'left' and 'right'
    curr = prev.next
    for _ in range(right - left):
        temp = curr.next
        curr.next = temp.next
        temp.next = prev.next
        prev.next = temp

    return dummy.next

 */