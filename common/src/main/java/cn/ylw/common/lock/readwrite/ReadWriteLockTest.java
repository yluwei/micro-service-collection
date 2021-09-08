package cn.ylw.common.lock.readwrite;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author yanluwei
 * @date 2021/9/8
 */
@Slf4j
public class ReadWriteLockTest {

    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        Thread thread = new Thread(() -> {
            readLock.lock();
            log.info("线程1获取读锁");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程1释放读锁");
            readLock.unlock();
        });

        Thread thread1 = new Thread(() -> {
            readLock.lock();
            log.info("线程2获取读锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程2释放读锁");
            readLock.unlock();
        });

        Thread thread2 = new Thread(() -> {
            writeLock.lock();
            log.info("线程3获取写锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程3释放写锁");
            writeLock.unlock();
        });
        thread.start();
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
