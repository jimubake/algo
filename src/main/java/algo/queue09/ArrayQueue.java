package algo.queue09;

public class ArrayQueue {
    private String[] items;
    private int n = 0;
    private int head = 0, tail = 0;

    public ArrayQueue(int capacity) {
        items = new String[capacity];
        this.n = capacity;
    }

    public boolean enQueue(String data) {
        if (n == tail) return false;
        items[tail] = data;
        ++tail;
        return true;
    }

    public String deQueue() {
        if (head == tail) return null;
        String req = items[head];
        head++;
        return req;
    }

    public void printAll() {
        for(String s: items) {
            System.out.print(s+", ");
        }
        System.out.println();
    }
}
