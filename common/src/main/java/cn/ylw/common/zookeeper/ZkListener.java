package cn.ylw.common.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 监听
 *
 * @author yanluwei
 * @date 2021/5/28
 */
public class ZkListener {

    public void watch(ZooKeeper connect) throws Exception {
        Stat stat = new Stat();
        byte[] data = connect.getData("/test", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                Watcher.Event.EventType type = watchedEvent.getType();
                System.out.println("节点类型: " + type);
                try {
                    byte[] data1 = connect.getData("/test", this, null);
                    System.out.println("变化后:" + new String(data1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println("变化前:" + new String(data));
        System.out.println("节点状态: " + stat);
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper connect = ZkClient.connect();
        new ZkListener().watch(connect);
        Thread.sleep(Integer.MAX_VALUE);
        connect.close();
    }
}
