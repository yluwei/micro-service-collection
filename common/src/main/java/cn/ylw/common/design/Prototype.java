package cn.ylw.common.design;

/**
 * 原型模式
 *
 * @author yanluwei
 * @date 2021/8/5
 */
public class Prototype implements Cloneable {

    private String a;
    private String b;

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public Prototype(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype = new Prototype("a", "b");
        // 注意深克隆与浅克隆
        Prototype clone = (Prototype) prototype.clone();
        System.out.println(clone.getA());
    }
}
