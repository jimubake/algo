package algo.Sort11;

public class BucketSort {

    public static void bucketSort(int[] a, int bucketSize) {
        if (a.length < 2) return;

        int minValue = a[0];
        int maxValue = a[1];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < minValue)
                minValue = a[i];
            else if (a[i] > maxValue)
                maxValue = a[i];
        }

        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount]; // 每个桶的 size

        for (int i = 0; i < a.length; i++) {
            int bucketIndex = (a[i] - minValue) / bucketSize; // 分配桶的 index
            if (indexArr[bucketIndex] == buckets[bucketIndex].length)
                ensureCapacity(buckets, bucketIndex);
            buckets[bucketIndex][indexArr[bucketIndex]++] = a[i];
        }

        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].length == 0) // indexArr[i] = buckets[i].length
                return;

            quickSort(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++)
                a[k++] = buckets[i][j];
        }
    }

    public static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] temp = buckets[bucketIndex];
        int[] newArr = new int[temp.length * 2];
        System.arraycopy(temp, 0, newArr, 0, temp.length);
        buckets[bucketIndex] = newArr;
    }

    public static void quickSort(int[] a, int l, int r) {
        if (l <= r) return;
        int partition = partition(a, l, r);
        quickSort(a, l, partition - 1);
        quickSort(a, partition + 1, r);
    }

    public static int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int p = l;
        for (int q = l; q <= r; q++) {
            if (a[q] <= pivot) {
                swap(a, p, q);
                p++;
            }
        }
        swap(a, p, r);
        return p;
    }

    public static void swap(int[] a, int i, int j) {
        if (i == j) return;

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
