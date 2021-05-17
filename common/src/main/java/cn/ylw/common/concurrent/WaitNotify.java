package cn.ylw.common.concurrent;

/**
 * wait notify
 *
 * @author yanluwei
 * @date 2021/5/17
 */
public class WaitNotify {
    private final Object lock = new Object();

    private boolean have = true;

    private void acquire() throws InterruptedException {
        synchronized (lock) {
            Thread thread = Thread.currentThread();
//            thread.interrupt();
            System.out.println(thread.getName() + "是否中断" + thread.isInterrupted());
            // 设置中断标志位，就会抛出InterruptedException
            Thread.sleep(2000);
            System.out.println(thread.getName() + "获取锁成功");
            if (have) {
                System.out.println(thread.getName() + "wait");
                have = false;
                // 设置中断标志位，就会抛出InterruptedException
                lock.wait();
                System.out.println(thread.getName() + "notify");
                lock.notify();
                System.out.println(thread.getName() + "执行结束");
            } else {
                System.out.println(thread.getName() + "notify");
                lock.notify();
                System.out.println(thread.getName() + "wait");
                lock.wait();
                System.out.println(thread.getName() + "执行结束");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotify waitNotify = new WaitNotify();
        Thread thead = new Thread(() -> {
            try {
                waitNotify.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thead2 = new Thread(() -> {
            try {
                waitNotify.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thead.start();
        Thread.sleep(5000);
        thead2.start();
    }
}
