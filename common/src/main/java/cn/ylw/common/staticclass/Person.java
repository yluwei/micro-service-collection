package cn.ylw.common.staticclass;

/**
 * 内部类
 *
 * @author yanluwei
 * @date 2021/5/26
 */
public class Person {

    private Integer age = 18;

    class Boy {
        public void dodo() {
            System.out.println("boy");
        }
    }

    static class Girl {
        public void dodo() {
            System.out.println("girl");
        }

        public static void dodo1() {
            System.out.println("static girl");
        }

        public static void main(String[] args) {
            dodo1();
        }
    }

    public void dodo() {
        new Boy().dodo();
    }

    public static void main(String[] args) {
        Girl.dodo1();
        new Girl().dodo();
        new Person().dodo();
        System.out.println(new Person().age);
    }
}
