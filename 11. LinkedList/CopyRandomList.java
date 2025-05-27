public class CopyRandomList {
    private static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        // If there is only one node
        if (head == null)
            return head;

        // Step 1: Create copy nodes and interweave with original nodes
        Node curr = head;
        while(curr != null){
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }


        // Step 2: Assign random pointers to the copy nodes
        curr = head;
        while(curr != null && curr.next != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;    //For this check comments down below
            }
            curr = curr.next.next;      // Jumps from A to B (skips A')
        }


        // Step 3: Separate the original and cloned list
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        curr = head;

        while (curr!=null) {
            copyCurr.next = curr.next;
            curr.next = curr.next.next;
            
            copyCurr = copyCurr.next;
            curr = curr.next;
        }

        return dummy.next;
    }

    // Helper function to print the list (for debugging)
    public void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print("[" + temp.val + ", " +
                    (temp.random != null ? temp.random.val : "null") + "] -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        CopyRandomList solution = new CopyRandomList();

        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        System.out.println("Original List: ");
        solution.printList(head);

        head = solution.copyRandomList(head);

        System.out.println("Modified List: ");
        solution.printList(head);
    }
}

/*
 * - Pattern
 * 
 * Create new nodes and insert them after original nodes.
 * Adjust the random pointers of new nodes.
 * Restore the original list and extract the copied list.
 * 
 * 
 * - understanding of curr.next.random = curr.random.next;
 * 
 *  curr = 13
 *  curr.next → points to 13' (copy node)
 *  curr.random → points to 7 (original node) 
 * 
 *  Now, the key part:
        curr.next.random = curr.random.next;
        13'.random = 7.next;  
        7.next → points to 7' (the copy of 7)
 *  Result:
        13'.random = 7'
 * 
 * - Psudo code
 * 
 * function copyList(head):
 * if head is null:
 * return null
 * 
 * # Step 1: Interleave the original and copied nodes
 * current = head
 * while current is not null:
 * new_node = new Node(current.val)
 * new_node.next = current.next
 * current.next = new_node
 * current = new_node.next
 * 
 * # Step 2: Set the random pointers of the new nodes
 * current = head
 * while current is not null:
 * if current.random is not null:
 * current.next.random = current.random.next
 * current = current.next.next
 * 
 * # Step 3: Separate the original and copied list
 * original = head
 * copy_head = head.next
 * copy_current = copy_head
 * 
 * while original is not null:
 * original.next = original.next.next
 * if copy_current.next is not null:
 * copy_current.next = copy_current.next.next
 * copy_current = copy_current.next
 * original = original.next
 * 
 * return copy_head
 * 
 */
