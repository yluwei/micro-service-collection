package cn.ylw.common.design.decorator;

/**
 * 装饰器
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class PictureDecorator implements Picture {
    private Picture picture;

    public PictureDecorator(Picture picture) {
        this.picture = picture;
    }

    @Override
    public void display() {
        this.picture.display();
    }
}
