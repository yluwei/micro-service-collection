package cn.ylw.common.zookeeper;

import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Curator操作zk
 *
 * @author yanluwei
 * @date 2021/5/28
 */
public class CuratorClient {
    public static void main(String[] args) throws Exception {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curator = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(20000)
            .connectionTimeoutMs(10000)
            .retryPolicy(retry)
            .build();

        curator.start();

        curator.create()
            .creatingParentsIfNeeded()
            .withMode(CreateMode.PERSISTENT)
            .forPath("/curator/123456789", "curator".getBytes());

        List<String> strings = curator.getChildren().forPath("/test/hello");
        strings.forEach(System.out::println);

        byte[] bytes = curator.getData().forPath("/test/hello");
        System.out.println(new String(bytes));

        CountDownLatch latch = new CountDownLatch(2);

        Watcher w = new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent watchedEvent) {
                String path = watchedEvent.getPath();
                byte[] bytes1 = curator.getData().usingWatcher(this).forPath(path);
                System.out.println("发生变化：" + new String(bytes1));
                latch.countDown();
            }
        };

        byte[] bytes1 = curator.getData().usingWatcher(w).forPath("/test/hello");
        System.out.println("原始数据：" + new String(bytes1));

        latch.await();

        curator.close();
    }
}
