package cn.ylw.common.design.factory.abstractfactory;

/**
 * 苹果电脑
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class AppleComputer implements Computer {
    @Override
    public void play(String name) {
        System.out.println("苹果电脑玩" + name + "游戏");
    }
}
