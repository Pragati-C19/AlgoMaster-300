public class RotateRight {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        // Step 1: Find the length of the list
        ListNode curr = head;
        int length = 1; // Let assume count starts at 1

        while (curr.next != null) {
            curr = curr.next;
            length++;
        }

        // Step 2: Optimize rotations
        k = k % length;
        System.out.println("after optimization: [k,length] : " + " [ " + k + " , " + length + " ] ");

        if (k == 0){
            return head;    // No need to rotate if k is 0 after modulo
        }

        // Step 3: Connect the tail to head (circular list)
        System.out.println("Before circular list: " + curr.val);
        curr.next = head;  // Make the list circular

        // Step 4: Find the new tail (length - k - 1)
        int stepsToNewTail = length - k;
        System.out.println("after optimization: [stepsToNewTail,length] : " + " [ " + stepsToNewTail + " , " + length + " ] ");

        curr = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            curr = curr.next;
        }

        // Step 5: Break the circle and get the new head
        ListNode newHead = curr.next;
        curr.next = null;  // Break the cycle

        return newHead;

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
        RotateRight solution = new RotateRight();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List: ");
        solution.printList(head);

        int k = 2;
        head = solution.rotateRight(head, k);

        System.out.println("Modified List: ");
        solution.printList(head);
    }
}

/*
 * 
 * 
 * Pattern :
 * 
 * 1. Find the length of the list.
 * 2. Optimize k: k = k % length (skip unnecessary full rotations).
 * 3. Find the new "break point":
 * The new tail is at length : k - 1.
 * The new head is at length : k.
 * 4. Reconnect the list:
 * Make the old tail point to the old head.
 * Break the connection at the new tail.
 * 5. Return the new head.
 * 
 * 
 */