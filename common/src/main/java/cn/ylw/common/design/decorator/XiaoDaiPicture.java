package cn.ylw.common.design.decorator;

/**
 * 小呆
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class XiaoDaiPicture implements Picture {
    @Override
    public void display() {
        System.out.println("这是小呆在河边玩水的照片");
    }
}
