package algo.skipList;

import java.util.Random;

public class SkipList {

    private final float p = 0.5f;
    private final int MAX_LEVEL = 16;
    private int levelCount = 1;

    // 头节点
    private Node head = new Node(MAX_LEVEL);

    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.insert(2);
        list.insert(5);
        list.insert(4);
        list.insert(3);
        list.insert(1);
    }

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value)
                p = p.forwards[i];
        }
        if (p.forwards[0] != null && p.forwards[0].data == value)
            return p.forwards[0];
        else
            return null;
    }

    public void delete(int value) {
        Node p = head;

        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null)
                if (p.forwards[i].data == value) {
                    p.forwards[i] = p.forwards[i].forwards[i];
                    break;
                } else if (p.forwards[i].data < value)
                    p = p.forwards[i];
        }
    }

    public void insert(int value) {
//        int level = head.forwards[0] == null ? 1 : randomLevel();
//        if (level > levelCount)
//            level = ++levelCount;
        int level = randomLevel();

        Node newNode = new Node(level);
        newNode.data = value;

        // 记录每一层中需要插入值的前一个节点
        Node[] preNode = new Node[level];
        for (int i = 0; i < level; i++)
            preNode[i] = head;

        Node p = head;
        for (int i = level - 1; i >= 0; i++) {
            while (p.forwards[i] != null && p.forwards[i].data < value)
                p = p.forwards[i];
            preNode[i] = p;

//            newNode.forwards[i] = p.forwards[i];
//            p.forwards[i] = newNode;
        }

        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = preNode[i].forwards[i];
            preNode[i].forwards[i] = newNode;
        }

        if (level > levelCount) levelCount = level;
    }

    public int randomLevel() {
        int level = 1;
        while (Math.random() < p && level < MAX_LEVEL)
            level += 1;
        return level;
    }

    public int randomLevel2() {
        int level = 1;
        Random r = new Random();
        for (int i = 0; i < MAX_LEVEL; i++) {
            if (r.nextInt() % 2 == 1)
                level++;
        }
        return level;
    }

    public class Node {
        private int data = -1;
        private Node[] forwards;
        private int maxLevel = 0;

        public Node(int level) {
            this.forwards = new Node[level];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{ data: ");
            sb.append(data);
            sb.append("; level: ");
            sb.append(maxLevel);
            sb.append("}");
            return sb.toString();
        }
    }
}
