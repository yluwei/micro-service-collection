package cn.ylw.common.design.observe;

/**
 * 测试
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class MessageMain {
    public static void main(String[] args) throws InterruptedException {
        Consumer consumer1 = new Consumer1();
        consumer1.subscribe("hello");
        Consumer consumer2 = new Consumer2();
        consumer2.subscribe("food");
        Consumer consumer3 = new Consumer3();
        consumer3.subscribe("bed");

        while (true) {
            long time = System.currentTimeMillis();
            Broker.publish("hello", "瓜娃子" + time);
            Broker.publish("food", "你猜" + time);
            Broker.publish("bed", "试试" + time);
            Thread.sleep(1000);
        }
    }
}
