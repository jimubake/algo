package algo.hash;

public class HashLearn {

    private int capacity;
    private char[] var;
    private int hash;

    public int hash(Object o) {
        int h = o.hashCode();
        return (h ^ (h >>> 16)) & (capacity - 1);
    }

    @Override
    public int hashCode() {
        int hash1 = this.hash;
        if (hash1 == 0 && this.var.length > 0) {
            char[] var2 = this.var;
            for (int i = 0; i < var2.length; i++) {
                hash1 = 31 * hash1 + var2[i];
            }
            this.hash = hash1;
        }
        return hash1;
    }
}
