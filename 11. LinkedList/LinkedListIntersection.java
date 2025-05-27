class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListIntersection {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Edge case: if either list is empty, no intersection
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        // Traverse both lists
        // When one pointer hits the end, switch to the other list's head
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        // Return the intersection node (or null if no intersection)
        return a;
    }

    // Helper function to create a linked list
    public static ListNode createList(int[] values) {
        if (values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    public static void main(String[] args) {
        LinkedListIntersection solution = new LinkedListIntersection();

        // Create two separate lists
        ListNode listA = createList(new int[]{4, 1});
        ListNode listB = createList(new int[]{5, 6, 1});

        // Create the intersection part
        ListNode intersection = createList(new int[]{8, 4, 5});

        // Attach the intersection to both lists
        ListNode tempA = listA;
        while (tempA.next != null) tempA = tempA.next;
        tempA.next = intersection;

        ListNode tempB = listB;
        while (tempB.next != null) tempB = tempB.next;
        tempB.next = intersection;

        // Find intersection node
        ListNode result = solution.getIntersectionNode(listA, listB);

        // Output result
        if (result != null) {
            System.out.println("Intersected at node with value: " + result.val);
        } else {
            System.out.println("No intersection.");
        }
    }
}




/*
 * 
 * Psuedo code 
 * 
 * 
 * 
 * Logic I think of behind it
 * 
 * Two pointers (a and b) start from headA and headB.
Traverse each list normally.
When a pointer reaches the end of its list, it jumps to the head of the other list.
a moves to headB
b moves to headA
Now they effectively "balance out" the length difference — because each pointer travels exactly m + n steps.
If they intersect, they meet at the intersection node.
If not, both pointers reach null at the same time.
 * 
 */






                               