package algo.Sort11;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        quickSort2(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r])
                temp[i++] = arr[l++];
            else
                temp[i++] = arr[r++];
        }

        while (l <= mid)
            temp[i++] = arr[l++];
        while (r <= right)
            temp[i++] = arr[r++];
        for (int j = 0; j <= right - left; j++)
            arr[j + left] = temp[j];
    }

    // 哨兵
    public static void mergeBySentry(int[] a, int left, int mid, int right) {
        int[] p = new int[mid - left + 2];
        int[] q = new int[right - mid + 1];
        for (int i = left; i <= mid; i++)
            p[i - left] = a[i];
        p[mid - left + 1] = Integer.MAX_VALUE;
        for (int i = mid + 1; i <= right; i++)
            q[i - mid - 1] = a[i];
        q[right - left] = Integer.MAX_VALUE;


        int i = 0, j = 0;
        int k = left;

        while (k <= right) {
            if (p[i] < q[j])
                a[k++] = p[i++];
            else
                a[k++] = q[j++];

        }
    }


    public static void quickSort(int[] a, int left, int right) {
        if (left >= right) return;
        int index = partition(a, left, right);
        quickSort(a, left, index - 1);
        quickSort(a, index + 1, right);
    }

    public static int partition(int[] a, int left, int right) {
        int pivot = a[right];  // pivot = a[(right-left)>>1]
        int i = left;
        for (int j = left; j < right; j++) {
            if (a[j] < pivot) {
                if (i == j)
                    i++;
                else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }
        int tmp = a[i];
        a[i] = a[right];
        a[right] = tmp;
        return i;
    }

    // 三向切分快速排序: while 循环排序后，l -> k=r 之间的数据有序，k=r -> right && left -> l 之间未排序

    public static void quickSort2(int[] a, int left, int right) {
        if (left >= right)
            return;
        int l = left;
        int r = right;
        int k = left + 1;
        int pivot = a[l];
        while (k <= r) {
            if (a[k] == pivot)
                k++;
            else if (a[k] < pivot)
                swap(a, k++, l++);
            else if (a[r] > pivot)
                r--;
            else if (a[r] == pivot)
                swap(a, k++, r--);
            else {
                swap(a, l, r);
                swap(a, k, r);
                l++;
                k++;
                r--;
            }
        }
        quickSort2(a, left, l - 1);
        quickSort2(a, r + 1, right);
    }


    public static void swap(int[] a, int i, int j) {
        if (i == j) return;

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
