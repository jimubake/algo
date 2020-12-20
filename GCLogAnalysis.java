package gcLogAnalysis;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class GCLogAnalysis {
    private static Random random = new Random();

    public static void main(String[] args) {
        long startMillis = System.currentTimeMillis();
        long timeoutMills = TimeUnit.SECONDS.toMillis(1);
        long endMillis = startMillis + timeoutMills;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");
        int cacheSize = 2000;
        Object[] cacheGarbage = new Object[cacheSize];
        while (System.currentTimeMillis() < endMillis) {
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if(randomIndex < cacheSize)
                cacheGarbage[randomIndex] = garbage;
        }
        System.out.println("执行结束！共生成对象次数: " + counter.longValue());
    }

    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result;
        switch (type) {
            case 0:
                result= new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder sb = new StringBuilder();
                String randomString = "randomString-Anything";
                while (sb.length() < randomSize){
                    sb.append(randomString);
                    sb.append(max);
                    sb.append(randomSize);
                }
                result = sb.toString();
        }
        return result;
    }
}
