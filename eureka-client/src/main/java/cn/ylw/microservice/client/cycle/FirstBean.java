package cn.ylw.microservice.client.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试循环依赖
 *
 * @author yanluwei
 * @date 2021/4/28
 */
@Component
public class FirstBean {

    @Autowired
    private SecondBean secondBean;

    public void callDodo() {
        secondBean.callDodo();
    }

    public void dodo() {
        System.out.println("dodo");
    }
}
