class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect cycle using slow and fast pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If slow meets fast, a cycle exists
            if (slow == fast) {
                // Step 2: Find start of cycle
                ListNode start = head;
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                return start;  // This is the start of the cycle
            }
        }

        return null;  // No cycle
    }

    public static void main(String[] args) {
        // Creating a test case with a cycle
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;  // Creates a cycle

        DetectCycle detector = new DetectCycle();
        ListNode cycleNode = detector.detectCycle(head);

        if (cycleNode != null) {
            System.out.println("Cycle starts at node with value: " + cycleNode.val);
        } else {
            System.out.println("No cycle detected.");
        }
    }
}
