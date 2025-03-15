public class Flatten {
    private static class Node {
        int val;
        Node prev;
        Node next;
        Node child;

        Node(int val) {
            this.val = val;
        }
    }

    public Node flatten(Node head) {
        if (head == null)
            return null;

        flattenHelper(head); // Call helper function to recursively flatten the list

        return head;
    }

    public Node flattenHelper(Node head) {

        Node curr = head;
        Node tail = head;   // Track the tail of the current flattened list

        while (curr != null) {
            Node next = curr.next;

            if (curr.child != null) {

                Node childTail = flattenHelper(curr.child);     // Recursively flatten the child list
                System.out.println("childTail after recursion : " + childTail.val);

                curr.next = curr.child;
                curr.child.prev = curr;
                curr.child = null;

                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }

                tail = childTail;
                System.out.println("tail = childTail in if loop : " + tail.val + " = " + childTail.val);

            } else {
                tail = curr;
                System.out.println("if curr.child == null : " + tail.val);
            }
            curr = next;

            System.out.println("End of While loop curr : " + (curr != null ? curr.val : "null"));
        }
        return tail;

    }

    // Helper function to print the list (for debugging)
    public void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Flatten solution = new Flatten();

        // [1,2,3,4,5,6,null]
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;

        head.next.next = new Node(3);
        head.next.next.prev = head.next;

        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;

        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;

        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.prev = head.next.next.next.next;

        // 3's childs - [7,8,9,10,null]
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        head.next.next.child = node7;
        node7.next = node8;
        node8.prev = node7;

        node8.next = node9;
        node9.prev = node8;

        node9.next = node10;
        node10.prev = node9;

        // 8's childs - [11,12,null]
        Node node11 = new Node(11);
        Node node12 = new Node(12);

        node8.child = node11;
        node11.next = node12;
        node12.prev = node11;

        Node flattenList = solution.flatten(head);

        System.out.println("Flattened List: ");
        solution.printList(flattenList);
    }
}

/*
 * 
 * -- Doubly Linked List so add next and prev both
 * 
 * - How the multilevel linked list is represented in test cases:
 * 
 * - We use the multilevel linked list from Example 1 above:
 * 1---2---3---4---5---6--NULL
 * |
 * 7---8---9---10--NULL
 * |
 * 11--12--NULL
 * 
 * - The serialization of each level is as follows:
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * 
 * - To serialize all levels together, we will add nulls in each level to
 * signify
 * no node connects to the upper node of the previous level. The serialization
 * becomes:
 * [1, 2, 3, 4, 5, 6, null]
 * |
 * [null, null, 7, 8, 9, 10, null]
 * |
 * [ null, 11, 12, null]
 * 
 * - Merging the serialization of each level and removing trailing nulls we
 * obtain:
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 
 * 
 * 
 * Intutions :
 * 
 * 1. Dealing with a multilevel doubly linked list
 * - next points to next node
 * - prev points to previous node
 * - child points to sublist
 * 2. The goal is to flatten this structure into a single, linear doubly linked
 * list
 * - Each node's child list becomes part of the main list immediately after that
 * node.
 * - Don't change the order of nodes
 * - child pointers should be nullified after flattening.
 * 
 * 
 * Pattern :
 * 
 * 1. Traversal — traverse the list like normal (curr = curr.next).
 * 2. Check for child — If a node has a child:
 * - Save curr.next (the original next node).
 * - Connect curr.next to the child.
 * - Go to the end of this child list and connect its last node to the saved
 * next node.
 * 3. Continue — Repeat this for every node, flattening each child list when
 * encountered.
 * 
 * 
 * Pseudo Code :
 * 
 * function flatten(head):
 * if head is null:
 * return head
 * 
 * stack = []
 * curr = head
 * 
 * while curr:
 * if curr.child:
 * if curr.next:
 * stack.push(curr.next)
 * 
 * curr.next = curr.child
 * curr.next.prev = curr
 * curr.child = null
 * 
 * if not curr.next and stack is not empty:
 * curr.next = stack.pop()
 * curr.next.prev = curr
 * 
 * curr = curr.next
 * 
 * return head
 * 
 * 
 * 
 */