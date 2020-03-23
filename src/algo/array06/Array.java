package algo.array06;

/**
 * 1) 数组的查找、删除，按下标进行随机访问操作
 * 2）数据类型为 int
 */
public class Array {
    // 定义 int 数组保存数据
    public int[] data;
    // 数组的长度
    public int n;
    // 数组中实际的个数
    public int count;

    // 构造方法 初始化数组大小
    public Array(int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;
    }

    public int find(int index) {
        if (index < 0 || index >= count) return -1;
        return data[index];
    }

    public boolean insert(int index, int value) {
        if (count == n) {
            System.out.println("没有可插入的位置");
            return false;
        }

        // 位置不合法，如果大于 count，位置不连续
        if (index < 0 || index > count) {
            System.out.println("位置不合法");
            return false;
        }

        for (int i = count; i > index; i--)
            data[i] = data[i - 1];

        data[index] = value;
        ++count;
        return true;

    }

    public boolean delete(int index) {
        if (index < 0 || index >= count) return false;
        for (int i = index + 1; i < count; i++)
            data[i - 1] = data[i];
        --count;
        return true;
    }

    public void printAll() {
        for(int i=0; i<count; i++)
            System.out.println(data[i]+" ");
        System.out.println();
    }

}
