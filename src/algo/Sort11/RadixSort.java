package algo.Sort11;

public class RadixSort {

    public static void main(String[] args) {
        int[] a = new int[6];
        a[0] = 1;
        a[1] = 1;
        a[2] = 236;
        a[4] = 0;
        a[3] = 0;
        a[5] = 45;
        radixSort(a);
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }

    public static void radixSort(int[] a) {
        if (a == null || a.length < 2) return;

        int max = a[0];
        for (int val : a)
            if (val > max)
                max = val;
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(a, exp);
        }

    }

    /**
     * @param a   数组
     * @param exp 对指定位数进行排序：如对十位数，exp=10, 对百位数，exp=100
     */
    public static void countingSort(int[] a, int exp) {

        if (a == null || a.length < 2) return;
        int[] c = new int[10];

        for (int value : a) c[(value / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            c[i] = c[i] + c[i - 1];

        int[] temp = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            int p = (a[i] / exp) % 10;
            int index = c[p] - 1;
            temp[index] = a[i];
            c[p]--;
        }

        System.arraycopy(temp, 0, a, 0, temp.length);

    }
}
