package algo.queue09;

public class QueueBasedOnLinkedList {

    private Node head, tail;

    public QueueBasedOnLinkedList() {
        head = null;
        tail = null;
    }

    public boolean enQueue(String data) {
        Node newNode = new Node(data, null);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        }
        tail.next = newNode;
        tail = tail.next;
        return true;
    }

    public String deQueue() {
        if (head == null) return null;
        String value = head.val;
        head = head.next;
//        if (head == null) tail = null;
        return value;
    }

    public static class Node {
        private String val;
        private Node next;

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }

        public String getVal() {
            return val;
        }
    }
}
