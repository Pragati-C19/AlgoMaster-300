class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // Count the length of the linked list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Setup a dummy node to manage head swapping cleanly
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Reverse nodes in groups of k
        while (length >= k) {
            current = prev.next;
            ListNode next = current.next;

            // Reverse k nodes
            for (int i = 1; i < k; i++) {
                current.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = current.next;
            }

            // Move prev to the end of this reversed group
            prev = current;
            length -= k;
        }

        return dummy.next;
    }

    // Helper function to print the list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }

    // Test Cases
    public static void main(String[] args) {
        ReverseKGroup solution = new ReverseKGroup();

        // Test Case 1
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result1 = solution.reverseKGroup(head1, 2);
        printList(result1); // Output: 2 -> 1 -> 4 -> 3 -> 5

        // Test Case 2
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result2 = solution.reverseKGroup(head2, 3);
        printList(result2); // Output: 3 -> 2 -> 1 -> 4 -> 5

        // Test Case 3
        ListNode head3 = new ListNode(1);
        ListNode result3 = solution.reverseKGroup(head3, 1);
        printList(result3); // Output: 1
    }
}

// ListNode class provided by Leetcode
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


/***
 * 
 * 
 * def reverse_k_group(head, k):
    # Count the length of the list
    length = 0
    current = head
    while current:
        length += 1
        current = current.next
    
    # If the list is shorter than k, return as is
    if length < k:
        return head

    # Reverse k nodes at a time
    dummy = ListNode(0)
    dummy.next = head
    prev = dummy

    while length >= k:
        current = prev.next
        next_node = current.next

        # Reverse the k-group
        for _ in range(k - 1):
            current.next = next_node.next
            next_node.next = prev.next
            prev.next = next_node
            next_node = current.next

        # Move to the next group
        prev = current
        length -= k

    return dummy.next

 */