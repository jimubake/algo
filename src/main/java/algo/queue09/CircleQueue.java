package algo.queue09;

public class CircleQueue {
    private int n = 0;
    private String[] items;
    private int head = 0, tail = 0;

    public CircleQueue(int capacity) {
        this.n = capacity;
        this.items = new String[capacity];
    }

    public boolean enQueue(String data) {
        if ((tail + 1) % n == head) return false; // 队列满了
        items[tail] = data;
        tail = (tail + 1) % n;
        return true;
    }

    public String deQueue() {
        if (head == tail) return null; // 队列空
        String item = items[head];
        head = (head + 1) % n;
        return item;
    }
}
