package algo.linkedlist07;

import java.util.HashMap;
import java.util.Map;

public class LRUBasedArray<T> {

    private static final int DEFAULT_CAPACITY = (1 << 3);
    private int capacity;
    private T[] value;
    private Map<T, Integer> holder;
    private int count;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        this.value = (T[]) new Object[capacity];
        this.holder = new HashMap<>(capacity);
        this.count = 0;
    }


    // 缓存在头部，但是需要先右移
    public void cache(T object, int end) {
        rightShift(end);
        value[0] = object;
        holder.put(object, 0);
        count++;

    }

    // 模拟访问某个值
    public void offer(T object) {
        if(object == null)
            throw new IllegalArgumentException("该缓存容器不支持 null！");
        Integer index = holder.get(object);
        if(index ==null) {
            if(isFull())
                removeAndCache(object);
            else
                cache(object, count);
        } else
            update(index);

    }

    // 若缓存中有指定的值，则更新位置
    public void update(int index) {
        T target = value[index];
        rightShift(index);
        value[0] = target;
        holder.put(target, 0);
    }

    public void removeAndCache(T object) {
        T key = value[--count];
        holder.remove(key);
        cache(object, count);
    }

    // end 左边的数据统一右移一位
    private void rightShift(int end) {
        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }
    }

    public boolean isContain(T object) {
        return holder.containsKey(object);
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public static void main(String[] args) {
        System.out.println(1 << 3);
    }
}
