package cn.ylw.common.design.responsibilitychain;

import lombok.Data;

/**
 * 领导者
 *
 * @author yanluwei
 * @date 2021/8/23
 */
@Data
public abstract class Leader {
    private Leader next;

    public abstract void handler(int vacationDays);
}
