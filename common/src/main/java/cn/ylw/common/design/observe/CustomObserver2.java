package cn.ylw.common.design.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class CustomObserver2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Subject o1 = (Subject) o;
        System.out.println("观察者2接收到消息:" + o1.getMessage());
    }
}
