public class SwapPairs {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode swapPairs(ListNode head) {

        // If there is only one node
        if (head == null || head.next == null)
            return head;

        // Dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        // ListNode first = prev.next;
        // ListNode second = prev.next.next;

        while(prev.next != null && prev.next.next != null){
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            //Swap the pair
            first.next = second.next;
            second.next = first;
            prev.next = second;

            //Move prev to the next pair
            prev = first;
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
        SwapPairs solution = new SwapPairs();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println("Original List: ");
        solution.printList(head);

        head = solution.swapPairs(head);

        System.out.println("Modified List: ");
        solution.printList(head);
    }
}

/*
 * 
 * 
 * pattern I saw
 * 
 * 1. Create a dummy node to simplify the edge cases.
 * 2. Three-pointer strategy
 * prev → Keeps track of the node before the pair being swapped.
 * first → The first node of the pair.
 * second → The second node of the pair.
 * 3. After swapping, update prev.next to point to the second node.
 * Update first.next to point to the node after the pair.
 * Move pointers to the next pair.
 */
