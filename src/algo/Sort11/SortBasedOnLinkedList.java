package algo.Sort11;

public class SortBasedOnLinkedList {

    public static void insertionSort(Node head) {
        if (head == null || head.next == null) return;
        Node p = head;
        while (p.next != null) {

        }
    }

    public void printAll(Node head) {
        while (head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }

    public static Node inverse(Node head) {
        if (head == null || head.next == null) return head;

        Node newNode = inverse(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    public static class Node {
        private int val;
        private Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
