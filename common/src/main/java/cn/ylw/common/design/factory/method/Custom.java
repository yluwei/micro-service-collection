package cn.ylw.common.design.factory.method;

/**
 * 客户
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class Custom {

    public void call(PhoneFactory factory, String name) {
        Phone make = factory.make();
        make.call(name);
    }

    public static void main(String[] args) {
        Custom custom = new Custom();
        custom.call(new AppleFactory(), "tom");
    }
}
