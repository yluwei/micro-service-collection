package cn.ylw.common.design;

/**
 * 单例模式
 *
 * @author yanluwei
 * @date 2021/8/5
 */
public class SingleTon {

    // 私有构造器
    private SingleTon() {

    }

    // 饿汉模式
    private static final SingleTon instance = new SingleTon();

    public static SingleTon getInstance() {
        return instance;
    }

    // 懒汉模式
    private static volatile SingleTon lazyInstance;

    // 有并发问题
    public static synchronized SingleTon getLazyInstance() {
        if (lazyInstance == null) {
            lazyInstance = new SingleTon();
        }
        return lazyInstance;
    }

    // 双重检查模式
    // 此模式需要使用volatile，保证SingleTon实例化遵守相关语义，以及内存可见性，避免线程拿到为实例化完成的对象
    public static SingleTon getLazyInstance1() {
        if (lazyInstance == null) {
            synchronized (SingleTon.class) {
                if (lazyInstance == null) {
                    lazyInstance = new SingleTon();
                }
            }
        }
        return lazyInstance;
    }

}
