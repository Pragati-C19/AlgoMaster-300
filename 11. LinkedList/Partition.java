public class Partition {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode partition(ListNode head, int x) {

        if (head == null)
            return null;

        // Two dummy heads fro small and lasrge lists
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        ListNode small = smallDummy;
        ListNode large = largeDummy;

        // Travese the original List
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                small.next = curr;
                small = small.next;
            } else {
                large.next = curr;
                large = large.next;
            }
            curr = curr.next;
        }

        // Merge that two Partitions
        small.next = largeDummy.next;
        large.next = null; // Break cycle here

        return smallDummy.next;
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
        Partition solution = new Partition();

        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        System.out.println("Original List: ");
        solution.printList(head);

        int x = 3;
        head = solution.partition(head, x);

        System.out.println("Modified List: ");
        solution.printList(head);
    }
}

/*
 * 
 * 
 * Pattern
 * 
 * 1. Create two dummy nodes:
 * smallHead → collects nodes smaller than x.
 * largeHead → collects nodes larger or equal to x.
 * 
 * 2. Traverse the list:
 * Add nodes to small list if node.val < x.
 * Otherwise, add them to large list.
 * 
 * 3. Merge the two lists:
 * Connect smallTail.next to largeHead.next.
 * Ensure largeTail.next = null to avoid cycles.
 * 
 * 
 */