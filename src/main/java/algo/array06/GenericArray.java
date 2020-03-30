package algo.array06;

public class GenericArray<T> {
    private T[] data;
    private int size;

    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    // 无参构造
    public GenericArray() {
        this(10);
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 获取当前元素个数
    public int count() {
        return size;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 修改 index 位置的元素
    public void set(int index, T e) {
        checkIndex(index);
        data[index] = e;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed! Require index >=0 and index < size.");
    }

    // 获取 index 位置的元素
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    // 查看数组是否包含元素 e
    public boolean contains(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }

    // 获取对应元素的下标，未找到，返回 -1
    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }
        return -1;
    }

    // 在 index 位置，插入元素 e，时间复杂度 O(m + n)
    public void add(int index, T e) {
        checkIndexForAdd(index);
        if (size == data.length)
            resize(2 * data.length);
        // 插入先从末尾开始移动
        for (int i = size - 1; i >= size; i--)
            data[i + 1] = data[i];
        data[index] = e;
        ++size;
    }

    // 向数组头插入元素
    public void addFirst(T e) {
        add(0, e);
    }

    // 删除 index 位置的元素，并返回
    public T remove(int index) {
        checkIndex(index);
        T ret = data[index];
        for (int i = index; i < size - 1; i++)
            data[i] = data[i + 1];
        --size;
        data[size] = null;

        // 缩小容量
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    // 删除第一个元素
    public T removeFirst() {
        return remove(0);
    }

    // 删除末尾元素
    public T removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除指定元素
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    // 扩容方法，时间复杂度 O(n)
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1)
                builder.append(", ");
        }
        builder.append("}");
        return builder.toString();
    }

    private void checkIndexForAdd(int index) {
        if(index<0 || index > size)
            throw new IllegalArgumentException("Add failed, Require index >=0 and index < size");
    }

}
