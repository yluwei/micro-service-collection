package cn.ylw.common.constant;

/**
 * 测试
 *
 * @author yanluwei
 * @date 2021/9/9
 */
public class Legal {
    public static String a = "hello";
    public String b = "hellob";

    public static void main(String[] args) {
        boolean b = Body.a == Legal.a;
        boolean b1 = new Body().b == new Legal().b;
        System.out.println(b);
        System.out.println(b1);
    }
}
