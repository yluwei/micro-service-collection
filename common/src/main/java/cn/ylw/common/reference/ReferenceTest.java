package cn.ylw.common.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 引用
 *
 * @author yanluwei
 * @date 2021/9/8
 */
public class ReferenceTest {
    public static void strongReference() {
        Object o = new Object();
        System.gc();
        System.out.println(o);
    }

    public static void softReference() {
        SoftReference o = new SoftReference<>(new Object());
        System.gc();
        System.out.println(o);
    }

    public static void weakReference() {
        WeakReference o = new WeakReference<>(new Object());
        System.gc();
        System.out.println(o);
    }

    public static void phantomReference() {
        PhantomReference o = new PhantomReference<>(new Object(), new ReferenceQueue<>());
        System.gc();
        System.out.println(o);
    }

    public static void main(String[] args) {
        Refer refer = new Refer(new Object(), new SoftReference<>(new Object()), new WeakReference<>(new Object()),
            new PhantomReference<>(new Object(), new ReferenceQueue<>()));
        System.gc();
        Object o = refer.get();
        System.out.println(refer);
        System.out.println(o);
    }
}

class Refer {
    private Object strong;
    private SoftReference soft;
    private WeakReference weak;
    private PhantomReference phantom;

    public Refer(Object strong, SoftReference soft, WeakReference weak, PhantomReference phantom) {
        this.strong = strong;
        this.soft = soft;
        this.weak = weak;
        this.phantom = phantom;
    }

    public Object get() {
        return this.phantom.get();
    }
}