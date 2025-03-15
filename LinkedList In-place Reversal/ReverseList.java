class ReverseList {
    // Iterative approach
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }

    // Recursive approach
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode reversedHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return reversedHead;
    }

    // Test Cases
    public static void main(String[] args) {
        ReverseList solution = new ReverseList();

        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        
        // Test Iterative
        ListNode reversed = solution.reverseList(head);
        printList(reversed); // Output: 5 -> 4 -> 3 -> 2 -> 1

        // Test Recursive
        ListNode head2 = new ListNode(1, new ListNode(2));
        ListNode reversedRecursive = solution.reverseListRecursive(head2);
        printList(reversedRecursive); // Output: 2 -> 1
    }

    // Helper to print linked list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }
}

// ListNode class provided by Leetcode
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


/**
 * 
 * def reverse_list(head):
    prev = None
    while head:
        next_node = head.next
        head.next = prev
        prev = head
        head = next_node
    return prev


    def reverse_list_recursive(head):
    if not head or not head.next:
        return head
    reversed_head = reverse_list_recursive(head.next)
    head.next.next = head
    head.next = None
    return reversed_head

 */
