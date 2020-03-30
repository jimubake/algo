package algo.string_match;

public class BM {

    private static final int SIZE = 256;

    public static void main(String[] args) {
        BM bm = new BM();
        char[] b = {'c', 'a', 'b', 'c', 'a', 'b'};
        int[] suffix = new int[6];
        boolean[] prefix = new boolean[6];
        bm.generateGS(b, 6, suffix, prefix);
        for (int i = 0; i < 6; i++) {
            System.out.println(i + ":" + suffix[i] + " " + prefix[i]);
        }
    }

    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < SIZE; i++) {
            int ascii = (int) b[i];
            bc[ascii] = i;
        }
    }

    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int i = 0; // 主串与模式串对齐的第一个字符

        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; j--)  // 模式串从后往前匹配
                if (a[i + j] != b[j]) break;
            if (j < 0)
                return i; // 表示匹配成功
            i = i + (j - bc[a[i + j]]);
        }
        return -1;
    }

    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0; // 后缀公共子串的长度
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                j--;
                k++;
                suffix[k] = j + 1;

            }
            if (j == -1) prefix[k] = true;
        }

    }

}
