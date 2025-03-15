
public class DeleteDuplicates {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        // if there are only one node
        if (head == null || head.next == null) {
            return head;
        }

        // Dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            // if the current node is same as the next node
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next; // skip duplicate
            }

            // if the current node is not same as the next node
            if (prev.next == curr) {
                prev = prev.next; // move prev to curr
            } else {
                prev.next = curr.next; // skip curr all duplicates
            }

            curr = curr.next; // Move to the next node
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
        DeleteDuplicates solution = new DeleteDuplicates();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        System.out.println("Original List: ");
        solution.printList(head);

        head = solution.deleteDuplicates(head);

        System.out.println("Modified List: ");
        solution.printList(head);
    }
}

/*
 * 
 * Pattern I think of..
 * 
 * 1. sort list
 * 
 * 2. Use two pointers
 * prev points to the last node known to be unique.
 * curr iterates through the list to detect duplicates.
 * 
 * 3. skip duplicate
 * curr.val = curr.next.val
 * 
 * 4. connect unique nodes
 * link prev.next to curr.next
 * 
 * 5. return head
 */