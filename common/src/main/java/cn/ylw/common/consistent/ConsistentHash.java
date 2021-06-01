package cn.ylw.common.consistent;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.TreeMap;

/**
 * 一致性哈希
 *
 * @author yanluwei
 * @date 2021/6/1
 */
@Slf4j
public class ConsistentHash {

    // 节点hash，节点名称TreeMap
    private TreeMap<Long, String> hashCircle;

    public ConsistentHash(List<String> nodes, int replicas) {
        this.hashCircle = new TreeMap<>();
        for (String node : nodes) {
            for (int i = 1; i <= replicas; i++) {
                long hash = this.hash(node + i);
                this.hashCircle.put(hash, node);
                log.info("节点：{}副本{}hash值为：{}", node, i, hash);
            }
        }
    }

    // 获取缓存节点
    public String getNode(String key) {
        long hash = this.hash(key);
        Long hashKey = this.hashCircle.tailMap(hash).isEmpty() ? this.hashCircle.firstKey() :
            this.hashCircle.tailMap(hash).firstKey();
        return this.hashCircle.get(hashKey);
    }

    public long hash(String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(key.getBytes());
            byte[] digest = md5.digest();
            long result = ((long) (digest[3] & 0xFF) << 24)
                | ((long) (digest[2] & 0xFF) << 16
                | ((long) (digest[1] & 0xFF) << 8) | (long) (digest[0] & 0xFF));
            return result & 0xffffffffL;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        List<String> nodes = Lists.newArrayList("192.168.40.100", "192.168.40.101", "192.168.40.102");
        ConsistentHash consistentHash = new ConsistentHash(nodes, 3);
        List<String> keys = Lists.newArrayList("中发", "测试", "成功", "小明", "乐乐", "admin", "boy");
        for (String key : keys) {
            log.info("{}缓存到节点：{}", key, consistentHash.getNode(key));
        }
    }
}
