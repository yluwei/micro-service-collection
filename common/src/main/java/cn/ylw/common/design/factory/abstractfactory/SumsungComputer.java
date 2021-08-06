package cn.ylw.common.design.factory.abstractfactory;

/**
 * 三星电脑
 *
 * @author yanluwei
 * @date 2021/8/6
 */
public class SumsungComputer implements Computer {
    @Override
    public void play(String name) {
        System.out.println("三星电脑玩" + name + "游戏");
    }
}
