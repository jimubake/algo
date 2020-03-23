package algo.stack08;

public class LinkedStack {

//    private static final int DEFAULT_CAPACITY = 10;

//    private int count;

//    private int capacity;

    private Node node;

    public LinkedStack(Node head) {
//        this.count = 0;
//        this.capacity = DEFAULT_CAPACITY;
        this.node = head;
    }

    public boolean push(String value) {
        Node newNode = new Node(value, null);
        if (node == null) {
            node = newNode;
            return true;
        }

        newNode.next = node;
        return true;
//        Node head = node;
//        int i = 1;
//        while (head != null && i < capacity) {
//            head = head.next;
//            i++;
//        }
//        if (head != null) {
//            return false; // 栈区已满
//        } else {
//            newNode.next = node;
//            ++count;
//            return true;
//        }
    }

    public String pop() {
        if (node == null) return null;
        String value = node.getVal();
        node = node.next;
//        --count;
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
