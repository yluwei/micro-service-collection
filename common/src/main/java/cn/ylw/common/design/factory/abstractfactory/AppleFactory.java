package cn.ylw.common.design.factory.abstractfactory;

/**
 * 苹果工厂
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class AppleFactory implements ElectronicProductFactory {
    @Override
    public Phone make() {
        return new Apple();
    }

    @Override
    public Computer makeComputer() {
        return new AppleComputer();
    }
}
