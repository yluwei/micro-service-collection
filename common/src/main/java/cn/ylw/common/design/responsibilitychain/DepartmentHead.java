package cn.ylw.common.design.responsibilitychain;

/**
 * 系主任
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class DepartmentHead extends Leader {
    @Override
    public void handler(int vacationDays) {
        if (vacationDays <= 7) {
            System.out.println("系主任同意请假");
        } else {
            Leader next = this.getNext();
            if (next != null) {
                next.handler(vacationDays);
            } else {
                System.out.println("系主任后无人审批，请假被拒绝");
            }
        }
    }
}
