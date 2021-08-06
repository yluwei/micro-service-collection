package cn.ylw.common.design.factory.method;

/**
 * 三星工厂
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class SumsungFactory implements PhoneFactory {
    @Override
    public Phone make() {
        return new Sumsung();
    }
}
