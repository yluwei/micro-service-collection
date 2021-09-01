package cn.ylw.common.concurrent;

/**
 * volatile学习
 *
 * @author yanluwei
 * @date 2021/9/1
 */
public class VolatileTest {
    public static void main(String[] a) throws Exception {
        Task task = new Task();
        new Thread(task).start();

        Thread.sleep(1000);
        long stoppedOn = System.nanoTime();

        task.stop();

        System.out.println("主线程停止: " + stoppedOn);
    }
}

class Task implements Runnable {
    private volatile boolean state = true;
    private int i = 0;

    void stop() {
        state = false;
    }

    @Override
    public void run() {
        while (state) {
            i++;
        }
        System.out.println(i + "Task线程停止" + System.nanoTime());
    }
}
