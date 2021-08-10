package cn.ylw.common.design.proxy;

/**
 * 管理
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class ManagerJobService implements JobService {
    @Override
    public void createJob(String name) {
        System.out.println("管理员发布了一个新职位，职位名称：" + name);
    }
}
