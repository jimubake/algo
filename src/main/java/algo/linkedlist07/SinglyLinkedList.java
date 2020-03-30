package algo.linkedlist07;

public class SinglyLinkedList {
    private Node head;


    public Node findByValue(int value) {
        Node p = head;
        while (p.next != null && p.data == value)
            p = p.next;
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p.next != null && pos == index) {
            p = p.next;
            pos++;
        }
        return p;
    }

    // 无头节点，表头插入
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) head = newNode;
        else {
            newNode.next = head;
            head = newNode;
        }
    }

    // 顺序插入，尾部插入
    public void insertTail(int value) {
        Node newNode = new Node(value, null);
        if (head == null) head = newNode;
        else {
            Node p = head;
            while (p.next != null)
                p = p.next;
            newNode.next = p.next;
            p.next = newNode;
        }

    }

    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) return;
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null) return;
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) q = q.next;

        if (q == null) return;

        newNode.next = p;   //(q.next = p);
        q.next = newNode;
    }

    public void deleteByNode(Node p) {
        if (p == null && head == null) return;

        if (p == head) {
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p)
            q = q.next;

        if (q == null) return;

        q.next = q.next.next;
    }


    public void deleteByValue(int value) {
        if (head == null) return;
        Node q = head;
        Node p = null;
        while (q != null && q.data != value) {
            p = q;
            q = q.next;
        }
        if (q == null) return;

        if (p == null)
            head = head.next; // p = head
        else
            p.next = p.next.next; // p.next = q

        // 删除所有指定 value 的节点

        if (head != null && head.data == value)
            head = head.next;

        Node pNode = head;
        while (pNode != null) {
            if (pNode.next.data != value) {
                pNode.next = pNode.next.next;
                continue;
            }
            pNode = pNode.next;
        }
    }


    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p.getData() + " ");
            p = p.next;
        }
        System.out.println();
    }


    // 判断两个链表的值是否相等
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
            } else {
                return false;
            }
        }
        return l == null && r == null;
    }

    // 判断是否为回文
    public boolean palindrome() {
        if (head == null)
            return false;
        else {
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            if (p.next == null) {
                System.out.println("只有一个元素");
                return true;
            }
            while (q.next != null && q.next.next != null) {
                p = p.next;
                q = q.next.next;
            }
            System.out.println("中间节点：" + p.data);

            System.out.println("开始执行奇数节点的回文判断");
            Node leftLink = null;
            Node rightLink = null;
            if (q.next == null) {
                // 此时，p节点为链表的中间节点
                rightLink = p.next;
                leftLink = inverseLinkedList(p).next;
                System.out.println("左边第一个节点" + leftLink.data);
                System.out.println("右边第一个节点" + rightLink.data);
            } else {
                // 此时，节点数为偶数
                rightLink = p.next;
                leftLink = inverseLinkedList(p);
                System.out.println("左边第一个节点" + leftLink.data);
                System.out.println("右边第一个节点" + rightLink.data);
            }
            return TFResult(leftLink, rightLink);
        }
    }

    // 带头节点的链表翻转
    public Node inverseLinkedList_head(Node p) {
//        Node node = inverseLinkedList(p);
//        Node HH = new Node(999, null);
//        HH.next = node;
//        return HH;
        Node Head = new Node(9999, null);
        // p　为原来整个链表的头结点,现在Head指向　整个链表
        Head.next = p;
        /*
        带头结点的链表翻转等价于
        从第二个元素开始重新头插法建立链表
        */
        System.out.println("ddd" + p.data);
        Node Cur = p.next;
        p.next = null;
        Node next = null;

        while (Cur != null) {
            next = Cur.next;
            Cur.next = Head.next;
            Head.next = Cur;
            System.out.println("first " + Head.data);

            Cur = next;
        }

        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return Head;

    }

    // 无头节点的链表翻转
    public Node inverseLinkedList(Node p) {
        Node pre = null;
        Node r = head;
        Node next = null;
        System.out.println(r.data);

        while (r != p) {
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;
        return r;
    }

    // 递归链表翻转
    public Node reverse(Node p) {
        if (p == null || p.next == null)
            return p;
        Node newNode = reverse(p.next);
        p.next.next = p;
        p.next = null;
        return newNode;
    }

    public Node reverse2(Node p) {
        if (p == null || p.next == null)
            return p;
        Node cur = p.next.next;
        Node next;
        p.next.next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = p.next;
            p.next = cur;
            cur = next;
        }
        return p;
    }

    public static Node createNode(int data) {
        return new Node(data, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {

        SinglyLinkedList link = new SinglyLinkedList();
        int[] data = {1, 2, 5, 3, 8};
        for (int i : data) {
            link.insertTail(i);
        }
        link.printAll();
//        Node node = link.head;
//        while(node.next!=null)
//            node=node.next;

//        Node p = link.inverseLinkedList_head(link.head);

        Node p = link.reverse2(link.head);
        while (p != null) {
            System.out.println("aa" + p.data);
            p = p.next;
        }

    }
}
