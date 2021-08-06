package cn.ylw.common.design.factory.method;

/**
 * 苹果手机
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class Apple implements Phone {
    @Override
    public void call(String name) {
        System.out.println("用苹果手机给" + name + "打电话");
    }
}
