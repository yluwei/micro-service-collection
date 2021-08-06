package cn.ylw.common.design.factory.method;

/**
 * 三星
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class Sumsung implements Phone {
    @Override
    public void call(String name) {
        System.out.println("用三星手机给" + name + "打电话");
    }
}
