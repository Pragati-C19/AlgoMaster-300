class MiddleNode {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        // Move slow by 1 step and fast by 2 steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Slow now points to the middle node
        return slow;
    }

    public static void main(String[] args) {
        MiddleNode solution = new MiddleNode();

        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(solution.middleNode(head1).val);  // ✅ Output: 3

        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
        System.out.println(solution.middleNode(head2).val);  // ✅ Output: 4
    }
}

// ListNode Definition
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


/**
 * 
 * Slow pointer moves 1 step at a time.
Fast pointer moves 2 steps at a time.
When fast reaches the end, slow is at the middle.
 */