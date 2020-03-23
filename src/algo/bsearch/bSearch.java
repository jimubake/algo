package algo.bsearch;

public class bSearch {

    public static void main(String[] args) {
        int[] a = {5, 5, 6, 1, 2, 3};
        System.out.println(b8(a, a.length, 5));
//        System.out.println(b4(a, a.length, 6));
    }

    public static void bSearch(int[] a, int n, int value) {
        bSearchInternally(a, 0, n - 1, value);
    }

    public static int bSearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;

        int mid = low + ((high - low) >> 1);
        if (a[mid] == value)
            return mid;
        else if (a[mid] < value)
            return bSearchInternally(a, low, mid - 1, value);
        else
            return bSearchInternally(a, mid + 1, high, value);
    }

    public static int b2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        if (low > high) return -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] >= value)
                high = mid - 1;
            else
                low = mid + 1;
        }

        if (low < n && a[low] == value)
            return low;

        else return -1;
    }

    public static int b3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        if (low > high)
            return -1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= value)
                low = mid + 1;
            else high = mid - 1;
        }
        if (high >= 0 && a[high] == value) return high;
        else return -1;
    }

    // 查找第一个等于value 的值
    public static int b4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value)
                low = mid + 1;
            else if (a[mid] > value)
                high = low - 1;
            else if (low == 0 || a[mid - 1] != value) return mid;
            else high = mid - 1;
        }
        return -1;
    }

    // 查找最后一个等于 value 的值
    public static int b5(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value)
                high = mid - 1;
            else if (a[mid] < value)
                low = mid + 1;
            else if (a[high] == n - 1 || a[mid + 1] != value) return mid;
            else low = mid + 1;
        }
        return -1;
    }


    // 查找第一个大于等于 value 的值
    public static int b6(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value)
                if (mid == 0 || a[mid - 1] < value) return mid;
                else high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    // 查找最后一个小于等于value 的值
    public static int b7(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (a[mid] <= value)
                if (mid == n - 1 || a[mid + 1] > value) return mid;
                else low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    // 循环有序数组：求值等于给定值的位置 [5,5,6,1,2,3,4]
    public static int b8(int[] a, int n, int value) {
        int minIndex = 0;
        for (int i = 0; i < n; i++)
            if (a[i] < a[minIndex])
                minIndex = i;
        int low = minIndex;
        int high = minIndex + n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid % n] >= value)
                high = mid - 1;
            else
                low = mid + 1;
        }

        if (low == minIndex || a[low % n] == value) return low % n;
        return -1;
    }
}
