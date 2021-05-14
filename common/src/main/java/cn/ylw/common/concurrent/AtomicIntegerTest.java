package cn.ylw.common.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试原子Integer
 *
 * @author yanluwei
 * @date 2021/5/14
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger);
        atomicInteger.set(100);
        System.out.println(atomicInteger.get());
        boolean b = atomicInteger.compareAndSet(100, 102);
        System.out.println(b);
        System.out.println(atomicInteger.get());
    }
}
