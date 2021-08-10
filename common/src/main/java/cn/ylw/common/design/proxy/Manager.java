package cn.ylw.common.design.proxy;

/**
 * 管理员
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class Manager {
    public static void main(String[] args) {
        new StaticProxyJobService(new ManagerJobService()).createJob("项目经理");

        JobService jobService = (JobService) new DynamicProxy(new ManagerJobService()).getProxy();
        jobService.createJob("架构师");
    }
}
