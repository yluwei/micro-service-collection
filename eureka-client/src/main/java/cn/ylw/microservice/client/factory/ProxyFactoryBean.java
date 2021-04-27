package cn.ylw.microservice.client.factory;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author yanluwei
 * @date 2021/4/27
 */
public class ProxyFactoryBean<T> implements FactoryBean<T> {

    private T target;

    public void setTarget(T target) {
        this.target = target;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
            new ProxyService(target));
    }

    @Override
    public Class<?> getObjectType() {
        return target.getClass();
    }
}
