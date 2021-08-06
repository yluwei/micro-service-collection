package cn.ylw.common.design.factory.method;

/**
 * 苹果工厂
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class AppleFactory implements PhoneFactory {
    @Override
    public Phone make() {
        return new Apple();
    }
}
