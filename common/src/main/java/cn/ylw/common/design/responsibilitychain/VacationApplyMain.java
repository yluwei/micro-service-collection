package cn.ylw.common.design.responsibilitychain;

/**
 * 请假
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class VacationApplyMain {
    public static void main(String[] args) {
        Leader leader1 = new ClassAdviser();
        Leader leader2 = new DepartmentHead();
        Leader leader3 = new Dean();
        leader1.setNext(leader2);
        leader2.setNext(leader3);
        leader1.handler(2);
        leader1.handler(5);
        leader1.handler(9);
        leader1.handler(20);
    }
}
