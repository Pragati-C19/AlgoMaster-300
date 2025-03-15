class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Find the middle (slow/fast pointer)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        ListNode secondHalf = reverseList(slow);

        //  Compare both halves
        ListNode firstHalf = head;
        ListNode tempSecond = secondHalf;
        while (tempSecond != null) {
            if (firstHalf.val != tempSecond.val) return false;
            firstHalf = firstHalf.next;
            tempSecond = tempSecond.next;
        }

        // Optional: Restore the original list
        reverseList(secondHalf);

        return true;
    }

    // Reverse linked list helper
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }

    // Test Cases
    public static void main(String[] args) {
        IsPalindrome solution = new IsPalindrome();
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println(solution.isPalindrome(head1));  // ✅ Output: true

        ListNode head2 = new ListNode(1, new ListNode(2));
        System.out.println(solution.isPalindrome(head2));  // ✅ Output: false
    }
}

// ListNode Definition
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
