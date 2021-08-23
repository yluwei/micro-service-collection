package cn.ylw.common.design.responsibilitychain;

/**
 * 院长
 *
 * @author yanluwei
 * @date 2021/8/23
 */
public class Dean extends Leader {
    @Override
    public void handler(int vacationDays) {
        if (vacationDays <= 15) {
            System.out.println("院长同意请假");
        } else {
            Leader next = this.getNext();
            if (next != null) {
                next.handler(vacationDays);
            } else {
                System.out.println("院长后无人审批，请假被拒绝");
            }
        }
    }
}
