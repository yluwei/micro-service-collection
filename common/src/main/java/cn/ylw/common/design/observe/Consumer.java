package cn.ylw.common.design.observe;

/**
 * 消费者
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public interface Consumer {

    Integer getId();

    void subscribe(String topic);

    void update(String message);
}
