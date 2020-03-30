package algo.tree;

public class PrintPermutations {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
//        printPermutations(a, 4, 4);
        System.out.println(getCellCount(10));
    }


    // 调用方式：
// int[]a = a={1, 2, 3, 4}; printPermutations(a, 4, 4);
// k表示要处理的子数组的数据个数
    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < k; ++i) {
            int tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;

            printPermutations(data, n, k - 1);

            tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;
        }
    }

    public static int getCellCount(int n) {
        if (n < 0) return 1;
        if (n == 1) return 2;
        if (n == 2) return 4;
        if (n == 3) return 7;
        return getCellCount(n - 1) * 2 - getCellCount(n - 4);
    }

}
