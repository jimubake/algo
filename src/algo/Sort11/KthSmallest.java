package algo.Sort11;

public class KthSmallest {

    public static void main(String[] args) {
        int[] a = {1, 1, 2};
        int kth = findKth(a, 2);
        System.out.println(kth);
    }

    public static int findKth(int[] a, int k) {
        if (a == null || a.length < k)
            return -1;
        int partition = partition(a, 0, a.length - 1);
        while (partition + 1 != k) {
            if (partition + 1 < k)
                partition = partition(a, partition + 1, a.length - 1);
            else
                partition = partition(a, 0, partition - 1);
        }
        return a[partition];
    }

    public static int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, r);
        return i;
    }


    public static void swap(int[] a, int i, int j) {
        if (i == j) return;

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
