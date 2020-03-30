package algo.stack08;

public class ArrayStack {
    private int n;
    private int count;
    private String[] items;

    public ArrayStack(int n) {
        items = new String[n];
    }

    public boolean push(String value) {
        if (count == n) {
            resize(3 * n / 2);
        }
        items[count] = value;
        ++count;
        return true;
    }

    public String pop() {
        if (count == 0) return null;
        return items[--count];
    }

    public void resize(int capacity) {
        String[] newItems = new String[capacity];
        if (count >= 0) System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
    }
}
