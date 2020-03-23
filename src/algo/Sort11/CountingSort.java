package algo.Sort11;

public class CountingSort {

    public static void countingSorts(int[] a) {
        int max = a[0];
        int n = a.length;
        for (int i = 1; i < n; i++)
            if (a[i] > max)
                max = a[i];
        int[] c = new int[max + 1];
        // 在数组 c 中，c 下标为 0-max，c[i] 为数组a 中每个值的数量
        for (int value : a) c[value]++;

        // 将数组 c 中的值依次累加，表示在数据a 中小于a[i] 的数有多少个
        for (int i = 1; i <= max; i++)
            c[i] = c[i] + c[i - 1];

        int[] r = new int[n]; // 临时数组存储从后往前扫描a 中的数值

        // 从后往前扫描
        for (int i = n - 1; i >= 0; i--) {
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--;
        }

        System.arraycopy(r, 0, a, 0, n);
    }
}
