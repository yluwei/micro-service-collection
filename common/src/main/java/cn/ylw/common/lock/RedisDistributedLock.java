package cn.ylw.common.lock;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.UUID;

/**
 * Redis实现分布式锁
 *
 * @author yanluwei
 * @date 2020/10/27
 */
@Slf4j
public class RedisDistributedLock extends AbstractDistributedLock {

    /**
     * 线程安全的唯一锁value标识，防止释放非本线程的锁
     */
    private ThreadLocal<String> lockFlag = new ThreadLocal<>();

    /**
     * 释放锁lua脚本，先比较再删除
     */
    private static final String UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
        "then " +
        "    return redis.call(\"del\",KEYS[1]) " +
        "else " +
        "    return 0 " +
        "end ";

    private StringRedisTemplate stringRedisTemplate;

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 获取锁
     *
     * @param key         锁标识key值
     * @param expire      锁有效期（毫秒）
     * @param retryTimes  重试次数
     * @param sleepMillis 循环线程睡眠时间（毫秒）
     * @return {@link boolean}
     * @author yanluwei
     * @date 2020/10/27
     */
    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        // 获取锁
        boolean result = setLock(key, expire);
        // 失败重试
        while ((!result) && retryTimes-- > 0) {
            try {
                log.info("获取锁失败，重试: 第{}次", retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setLock(key, expire);
        }
        return result;
    }

    /**
     * 释放锁
     *
     * @param key 锁key值
     * @return {@link boolean}
     * @author yanluwei
     * @date 2020/10/27
     */
    @Override
    public boolean unLock(String key) {
        try {
            // 构建执行参数
            List<String> keys = Lists.newArrayList(key);
            List<String> args = Lists.newArrayList(lockFlag.get());

            // 适配集群模式和哨兵模式
            Long result = stringRedisTemplate.execute((RedisCallback<Long>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }

                // 单机模式或哨兵模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            });

            return result != null && result > 0;
        } catch (Exception e) {
            log.error("释放锁失败，原因：{}", e);
        }
        return false;
    }

    private boolean setLock(String key, long expire) {
        try {
            String result = stringRedisTemplate.execute((RedisCallback<String>) connection -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                String uuid = UUID.randomUUID().toString();
                lockFlag.set(uuid);
                SetParams params = new SetParams();
                params.nx();
                params.px(expire);
                return commands.set(key, uuid, params);
            });
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error("加锁时，redis发生异常，原因：{}", e);
        }
        return false;
    }
}
