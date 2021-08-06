package cn.ylw.common.design.factory.abstractfactory;

/**
 * 手机工厂
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public interface ElectronicProductFactory {
    Phone make();

    Computer makeComputer();
}
