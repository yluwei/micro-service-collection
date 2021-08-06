package cn.ylw.common.design.factory.abstractfactory;

/**
 * 三星工厂
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class SumsungFactory implements ElectronicProductFactory {
    @Override
    public Phone make() {
        return new Sumsung();
    }

    @Override
    public Computer makeComputer() {
        return new SumsungComputer();
    }
}
