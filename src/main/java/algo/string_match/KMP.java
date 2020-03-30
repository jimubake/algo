package algo.string_match;

public class KMP {

    public int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNext(b, m);
        int k = -1;
        for (int i = 1; i < n; i++) {
            while (k >= 0 && a[i] != b[k + 1])
                k = next[k] + 1;
            if (a[i] == b[k + 1])
                k++;

            if (k == m)
                return i - k;
        }
        return -1;
    }

    private int[] getNext(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = next[0]; // 表示最大可匹配的前缀子串
        for (int i = 1; i < m; i++) {
            while (k != -1 && b[k + 1] != b[i])
                k = next[k];

            if (b[k + 1] == b[i])
                k++;

            next[i] = k;
        }
        return next;
    }
}
