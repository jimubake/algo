package algo.Sort11;

public class Heap {

    private int[] a;
    private int n; // 可以存储的最大数据大小
    private int count; // 数据个数

    public Heap(int capacity) {
        this.a = new int[capacity];
        this.n = capacity;
        this.count = 0;
    }

    public void insert(int data) {
        if (count >= n) return;
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i / 2] < data) {
            swap(a, i / 2, i);
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) return;
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    public void sort() {
        if (a.length <= 1)
            return;

        buildHeap(a, count);

        int i = count;
        while (i > 1) {
            swap(a, i, 1);
            --i;
            heapify(a, i, 1);
        }

    }

    public void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 0; i--)
            heapify(a, n, i);
    }

    /**
     * @param a 堆化数组: 从下标 1开始存储
     * @param n 最后一个数组数据的下标
     * @param i 堆化开始的位置
     */
    public void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[i] < a[i * 2 + 1]) maxPos = 2 * i + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
