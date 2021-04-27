package cn.ylw.microservice.client.factory;

import cn.ylw.microservice.client.service.UserService;
import cn.ylw.microservice.client.service.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author yanluwei
 * @date 2021/4/27
 */
public class ProxyService implements InvocationHandler {

    // 被代理对象
    private Object target;

    public ProxyService(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理前");
        Object invoke = method.invoke(target, args);
        System.out.println("动态代理后");
        return invoke;
    }

    public static void main(String[] args) {
        UserService user = new UserServiceImpl();
        UserService o = (UserService) Proxy.newProxyInstance(user.getClass().getClassLoader(),
            user.getClass().getInterfaces(),
            new ProxyService(user));
        String username = o.getUsername();
        System.out.println(username);
    }
}