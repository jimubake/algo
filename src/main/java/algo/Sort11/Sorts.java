package algo.Sort11;

public class Sorts {

    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        shellSort3(arr);
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
        "dd".hashCode();
    }

    // 冒泡排序 n 是数组a 的大小
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; i++) {

            // 数据交换标志，存在交换，为 true
            boolean flag = false;

            for (int j = 1; j < n - i; j++) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                    // 此次存在数据交换
                    flag = true;
                }
            }
            // 如果整个 for 循环都没有数据交换，说明都是已经排好序了，提前退出
            if (!flag) break;
        }
    }

    /**
     * 冒泡排序改进：在每次排序后，记录最后一次交换元素的位置，作为下次排序的边界
     * 对于边界外的元素在下一次排序的时候，无需比较
     */
    public static void bubbleSort_Advance(int[] a, int n) {
        if (n <= 1) return;
        int lastChange = 0;
        int sorBorder = n - 1;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < sorBorder; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    flag = true;
                    lastChange = j;
                }
            }
            sorBorder = lastChange;
            if (!flag) break;
        }
    }

    // 插入排序：从无序数组中依次查找元素插入到有序数组：稳定排序
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (value > a[j])
                    break;
                else
                    a[j + 1] = a[j]; // 和有序数组最右边一个元素开始比较，小于就交换
            }
            a[j + 1] = value;
        }
    }

    // 选择排序：每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾：O(n^2)、O(1)、不稳定排序
    public static void selectionSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                if (a[j] < a[minIndex])
                    minIndex = j;
            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }

    // 希尔排序
    public static int[] shellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    public static void shellSort2(int[] a) {
        int n = a.length;
        for (int gap = n / 2; gap > 0; gap /= 2)
            for (int i = gap; i < n; i++)
                insertI(a, gap, i);
    }

    public static void insertI(int[] a, int gap, int i) {
        int insertValue = a[i];
        int j;
        for (j = i - gap; j >= 0 && insertValue < a[j]; j -= gap)
            a[j + gap] = a[j];
        a[j + gap] = insertValue;
    }

    public static void shellSort3(int[] arr) {
        int n = arr.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int insertValue = arr[i];
                int pre = i - gap;
                while (pre >= 0 && arr[pre] > insertValue) {
                    arr[pre + gap] = arr[pre];
                    pre -= gap;
                }
                arr[pre + gap] = insertValue;
            }
            gap /= 2;
        }
    }
}
