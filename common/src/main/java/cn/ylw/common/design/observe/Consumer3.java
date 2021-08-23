package cn.ylw.common.design.observe;

/**
 * 消费者1
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class Consumer3 implements Consumer {
    @Override
    public Integer getId() {
        return 2;
    }

    @Override
    public void subscribe(String topic) {
        Broker.addConsumer(topic, this);
    }

    @Override
    public void update(String message) {
        System.out.println("消费者3收到消息：" + message);
    }
}
