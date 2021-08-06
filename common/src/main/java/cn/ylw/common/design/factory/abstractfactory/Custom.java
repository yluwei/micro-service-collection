package cn.ylw.common.design.factory.abstractfactory;

/**
 * 客户
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class Custom {

    public void call(ElectronicProductFactory factory, String name) {
        Phone make = factory.make();
        make.call(name);
    }

    public void play(ElectronicProductFactory factory, String name) {
        Computer computer = factory.makeComputer();
        computer.play(name);
    }

    public static void main(String[] args) {
        Custom custom = new Custom();
        custom.call(new AppleFactory(), "tom");
        custom.play(new SumsungFactory(), "jenny");
    }
}
