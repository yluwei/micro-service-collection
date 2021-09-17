package cn.ylw.common.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 测试
 *
 * @author yanluwei
 * @date 2021/9/17
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(3);
        int i = 0;
        while (i < 3) {
            i++;
            countDown(count);
        }
        count.await();
        System.out.println("主线程结束");
    }

    private static void countDown(CountDownLatch count) {
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + name + "执行完成");
            count.countDown();
        }).start();
    }
}
