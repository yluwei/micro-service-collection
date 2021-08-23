package cn.ylw.common.design.observe;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息中心
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class Broker {
    private static Map<String, List<Consumer>> consumerMap = new HashMap<>();

    public static synchronized void addConsumer(String topic, Consumer consumer) {
        List<Consumer> consumers = consumerMap.get(topic);
        if (consumers == null) {
            consumers = new ArrayList<>();
            consumers.add(consumer);
            consumerMap.put(topic, consumers);
            return;
        }
        if (!CollectionUtils.isEmpty(consumers)) {
            for (Consumer c : consumers) {
                if (c.getId().equals(consumer.getId())) {
                    return;
                }
            }
        }
        consumers.add(consumer);
    }

    public static void publish(String topic, String message) {
        List<Consumer> consumers = consumerMap.get(topic);
        if (!CollectionUtils.isEmpty(consumers)) {
            for (Consumer c : consumers) {
                c.update(message);
            }
        }
    }
}
