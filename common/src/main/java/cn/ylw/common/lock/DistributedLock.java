package cn.ylw.common.lock;

/**
 * 分布式锁
 *
 * @author yanluwei
 * @date 2020/10/27
 */
public interface DistributedLock {
    /**
     * 锁有效时间（毫秒）
     */
    long TIMEOUT_MILLIS = 30000;

    /**
     * 重试次数
     */
    int RETRY_TIMES = Integer.MAX_VALUE;

    /**
     * 循环线程睡眠时间（毫秒）
     */
    long SLEEP_MILLIS = 500;

    boolean lock(String key);

    boolean lock(String key, int retryTimes);

    boolean lock(String key, int retryTimes, long sleepMillis);

    boolean lock(String key, long expire);

    boolean lock(String key, long expire, int retryTimes);

    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    boolean unLock(String key);
}
