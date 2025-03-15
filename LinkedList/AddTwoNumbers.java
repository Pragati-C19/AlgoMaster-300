public class AddTwoNumbers {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return l1;
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
        AddTwoNumbers solution = new AddTwoNumbers();

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println("First number:");
        solution.printList(l1);

        System.out.println("Second number:");
        solution.printList(l2);

        ListNode sum = solution.addTwoNumbers(l1, l2);

        System.out.println("Result: ");
        solution.printList(sum);
    }
}

/*
 * 
 * Intutions :
 * 
 * l1 = [2,4,3] represents 342
 * l2 = [5,6,4] represents 465
 * 
 * 342 + 465 = 807 ➜ Output: [7,0,8]
 * 
 * 1. we need to trace from Head to tail bcoz number are reverse
 * 2. Maintain a carry
 * 3. when one list is short then treat missing digit as zero
 * 4. If the final carry is non-zero, create a new node.
 * 
 * Pattern behind this :
 * 
 * 1. Initialize pointers for list and carry which should be set to zero
 * 2. Traverse the both list
 * l1.val + l2.val + carry.val
 * new digit = sum % 10, carry = sum / 10
 * store that sum in a node
 * 
 * Psuedo Code :
 * 
 * 1. Initialize dummy node and carry = 0
 * 2. While l1 or l2 exists:
 *      - Get values from l1 and l2 (0 if null)
 *      - Sum = l1.val + l2.val + carry.val
 *      - Create new node with (sum % 10)
 *      - Update carry = sum / 10
 *      - Move to next nodes in l1, l2, and result
 * 3. If carry > 0, add final node with carry
 * 4. Return dummy.next (head of result list)
 *
 *
 * why that sum % 10 and sum / 10
 *      -  If sum = 15, that time we need only 5 → 15 % 10 = 5
 *      -  If sum = 15, that time we need to carry 1 → 15 / 10 = 1
 * 
 * 
 * 
 */