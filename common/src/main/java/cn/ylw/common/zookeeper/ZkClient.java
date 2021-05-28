package cn.ylw.common.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * zookeeper操作
 *
 * @author yanluwei
 * @date 2021/5/28
 */
public class ZkClient {

    public static ZooKeeper connect() throws IOException {
        return new ZooKeeper("localhost:2181", 10000, null);
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper connect = connect();
//        String test = connect.create("/test/hello", "test123hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//            CreateMode.PERSISTENT);
//        System.out.println(test);
//        String test = connect.create("/test/hello/app", "test123hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//            CreateMode.PERSISTENT_SEQUENTIAL);
        connect.setData("/test/hello", "newtest7".getBytes(), -1);
    }
}
