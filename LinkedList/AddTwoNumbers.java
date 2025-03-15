public class AddTwoNumbers {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);    // Dummy Node for result
        ListNode curr = dummy;
        int carry = 0;

        // Traverse both lists
        while (l1 != null || l2 !=null) {
            int val1 = (l1 !=null) ? l1.val : 0;    // If l1 is shorter, take 0
            int val2 = (l2 !=null) ? l2.val : 0;    // If l2 is shorter, take 0

            int sum = val1 + val2 + carry;
            carry = sum / 10;                       // Carry for next addition

            curr.next = new ListNode(sum % 10);     // Last digit to store
            curr = curr.next;                      // Move to next position

            // move to next nodes
            if(l1 != null)  l1 = l1.next;
            if(l2 != null)  l2 = l2.next;

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
 * - Get values from l1 and l2 (0 if null)
 * - Sum = l1.val + l2.val + carry.val
 * - Create new node with (sum % 10)
 * - Update carry = sum / 10
 * - Move to next nodes in l1, l2, and result
 * 3. If carry > 0, add final node with carry
 * 4. Return dummy.next (head of result list)
 *
 *
 * why that sum % 10 and sum / 10
 * - If sum = 15, that time we need only 5 → 15 % 10 = 5
 * - If sum = 15, that time we need to carry 1 → 15 / 10 = 1
 * 
 * 
 * Do I need to reverse the list or traveserse from back?
 * - Noo.. lists aren't actually traversed from back to front.
 * - we are just adding numbers and all game is of carry...
 * - like in normal addition we do we used add right side number then add that carry to left side one..
 * - same thing here , just we are adding left side numbers then adding that carry to right side one 
 * 
 */