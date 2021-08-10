package cn.ylw.common.design.decorator;

/**
 * 照片美颜
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class PsPictureDecorator extends PictureDecorator {
    public PsPictureDecorator(Picture picture) {
        super(picture);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("我来美颜一下");
    }

    public static void main(String[] args) {
        PsPictureDecorator psPictureDecorator = new PsPictureDecorator(new XiaoDaiPicture());
        psPictureDecorator.display();
    }
}
