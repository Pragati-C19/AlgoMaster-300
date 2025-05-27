public class RemoveNthFromEnd {
    private static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
        }
    }

    public ListNode removeNthFromEnd (ListNode head, int n){
        ListNode fast = head;
        ListNode slow = head;

        // move fast pointer n steps
        for(int i=0; i < n; i++){
            fast = fast.next;
        }

        // If fast is null after moving n steps, it means we need to remove the head
        if(fast == null) return head.next;

        // move both pointers till end
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        // removed that n'th ListNode
        slow.next = slow.next.next;

        return head;

    }

    // Helper function to print the list (for debugging)
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        RemoveNthFromEnd solution = new RemoveNthFromEnd();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List: ");
        solution.printList(head);
        
        int n = 2;
        head = solution.removeNthFromEnd(head, n);

        System.out.println("List after removing " + n + "th ListNode from end:");
        solution.printList(head);
    }
}


/*
 * 
    Pattern used here:
    1. Create 2 pointers 
        fast moves n step ahead
        slow starts from head
    
    2. Move both pointers together
        when fast pointer hits to null then slow pointer will be at the data which we want to remove
    
    3. slow.next = slow.next.next

 */
