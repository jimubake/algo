package algo.linkedlist07;

public class LinkedListAlgo {
    // 单链表翻转
    public static Node reverse(Node node) {
        if (node == null && node.next == null)
            return node;
        Node newNode = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return newNode;
    }

    // 单链表翻转2
    public static Node reverse2(Node node) {
        Node pre = null, next;
        Node cur = node;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 检测环
    public static boolean checkCircle(Node node) {
        Node fast = node.next;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // 合并有序链表
    public Node mergeSortedList(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node p = a;
        Node q = b;
        Node head;
        if (p.data <= q.data) {
            head = p;
            p = p.next;
        } else {
            head = q;
            q = q.next;
        }
        Node tmp = head;
        while (p != null && q != null) {
            if (p.data < q.data) {
                tmp.next = p;
                p = p.next;
            } else {
                tmp.next = q;
                q = q.next;
            }
            tmp = tmp.next;
        }
        if (p != null) {
            tmp.next = p;
        } else
            tmp.next = q;
        return head;
    }

    // Leetcode 21 有序链表合并：利用哨兵简化代码
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode soldier = new ListNode(0, null);
        ListNode p = soldier;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return soldier;
    }


    // 删除倒数第k个节点
    public static Node deleteLastKth(Node node, int k) {
        Node fast = node;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        if (fast == null) return node; // k 大于链表长度

        Node slow = node;
        Node pre = null;
        while (fast.next != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre == null)
            pre = slow.next;
        else
            pre.next = slow.next;
        return pre;
    }


    // 求中间节点
    public Node findMiddleNode(Node node) {
        if (node == null)
            return null;
        Node slow = node;
        Node fast = node;
        if (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next == null) return slow;
        else return null;
    }

    public void printAll() {

    }

    public static class Node {
        private Node next;
        private int data;

        public Node(Node next, int data) {
            this.next = next;
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    public static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }
    }
}
