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

        return head;
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
 * - To serialize all levels together, we will add nulls in each level to signify
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
 * 
 * 
 */