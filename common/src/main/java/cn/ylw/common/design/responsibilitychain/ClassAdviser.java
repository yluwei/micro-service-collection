package cn.ylw.common.design.responsibilitychain;

/**
 * 班主任
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class ClassAdviser extends Leader {
    @Override
    public void handler(int vacationDays) {
        if (vacationDays <= 2) {
            System.out.println("班主任同意请假");
        } else {
            Leader next = this.getNext();
            if (next == null) {
                System.out.println("班主任后无人审批，请假被拒绝");
            } else {
                next.handler(vacationDays);
            }
        }
    }
}
