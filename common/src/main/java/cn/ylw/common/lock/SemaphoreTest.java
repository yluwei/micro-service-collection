package cn.ylw.common.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试
 *
 * @author yanluwei
 * @date 2021/9/17
 */
@Slf4j
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        int i = 0;
        while (i < 1000) {
            executorService.execute(() -> {
                try {
                    execute(semaphore);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            i++;
        }

    }

    private static void execute(Semaphore semaphore) throws InterruptedException {
        String name = Thread.currentThread().getName();
        semaphore.acquire();
        log.info("线程{}获取信号量", name);
        Thread.sleep(1000);
        log.info("***********线程{}释放信号量", name);
        semaphore.release();
    }
}
