public class MyLinkedList {
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    // Initializes an empty linked list
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {

        if (index < 0 || index >= size) {
            return -1;
        }

        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.data;
    }

    public void addAtHead(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            size++;
        } else {
            newNode.next = head;
            head = newNode;
            size++;
        }
    }

    public void addAtTail(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            size++;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
            size++;
        }
    }

    public void addAtIndex(int index, int data) {
        Node newNode = new Node(data);
        Node curr = head;

        if (index < 0 || index > size)
            return;

        if (index == 0) {
            addAtHead(data);
            return;
        }
        
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;

        if (index == 0) {
            head = head.next;
        } else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
        }

        size--;
    }

    // Helper function to print the list (for debugging)
    public void printList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();

        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2); // Linked list becomes 1->2->3
        System.out.println(myLinkedList.get(1)); // Output: 2
        myLinkedList.deleteAtIndex(1); // Linked list becomes 1->3
        System.out.println(myLinkedList.get(1)); // Output: 3

        // Optional: Print the final list
        myLinkedList.printList(); // Output: 1 -> 3 -> null
    }

}

/*
 * 
 * Steps to Write it...
 * 
 * 1. Initialize an empty list :- output - null
 * 2. add 1 as the first node :- output - null, list- 1
 * 3. add 3 to the end :- output - null, list - 1 -> 3
 * 4. insert 2 before index 1 :- output - null, list :- 1 -> 2 -> 3
 * 5. Retrieve value at index 1 — should return 2 (node at index 1 is 2).
 * 
 */