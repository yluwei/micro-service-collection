package cn.ylw.common.design.adapter;

/**
 * 海尔适配器
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class HaierTelevisionAdapter extends PowerSource implements Television {
    @Override
    public void watch() {
        // 插电源
        String supply = supply();
        System.out.println(supply + "来了");
        System.out.println("看电视");
    }
}
