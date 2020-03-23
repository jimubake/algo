package algo.linkedlist07;

import java.util.Scanner;

/**
 * 基于单链表的 LRU 算法
 */

public class LRUBasedLinked<T> {

    private final static Integer DEFAULT_CAPACITY = 10;

    private SNode<T> headNode;

    private Integer length;

    private Integer capacity;

    public LRUBasedLinked() {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public void add(T data) {
        SNode<T> preNode = findPreNode(data);
        if (preNode != null) {
            deleteElement(preNode);
            insertElementAtBegin(data);
        } else {
            if (length >= this.capacity)
                deleteElementAtEnd();
            insertElementAtBegin(data);
        }
    }

    private void deleteElementAtEnd() {
        SNode<T> ptr = headNode;
        if (ptr.getNext() == null)
            return;
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }
        ptr.setNext(null);
        length--;
    }

    private void deleteElement(SNode<T> preNode) {
        SNode<T> tmp = preNode.getNext();
        preNode.setNext(tmp.getNext());
        tmp = null;
        length--;
    }

    public void insertElementAtBegin(T data) {
        SNode<T> next = headNode.getNext();
        headNode.setNext(new SNode<>(data, next));
        length++;
        next = null;
    }

    // 查找元素的前一个节点
    private SNode<T> findPreNode(T data) {
        SNode<T> node = headNode;
        while (node.next != null) {
            if (data.equals(node.getNext().getElement()))
                return node;
            node = node.getNext();
        }
        return null;
    }

    public LRUBasedLinked(Integer capacity) {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = capacity;
    }

    public void printAll() {
        SNode<T> sNode = headNode.getNext();
        while (sNode != null) {
            System.out.println(sNode.getElement() + ", ");
            sNode = sNode.getNext();
        }
        System.out.println();
    }

    public class SNode<T> {
        private T element;
        private SNode<T> next;

        public T getElement() {
            return element;
        }

        public void setNext(SNode<T> next) {
            this.next = next;
        }

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode<T> next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode<T> getNext() {
            return next;
        }
    }

    public static void main(String[] args) {
        LRUBasedLinked list = new LRUBasedLinked();
        Scanner sc = new Scanner(System.in);
        while(true){
            list.add(sc.next());
            list.printAll();
        }
    }

}
