package cn.ylw.common.design.proxy;

/**
 * 静态代理
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class StaticProxyJobService implements JobService {

    private JobService target;

    public StaticProxyJobService(JobService target) {
        this.target = target;
    }

    @Override
    public void createJob(String name) {
        System.out.println("我来代理，先收中介费");
        this.target.createJob(name);
        System.out.println("代理结束，拜拜");
    }
}
