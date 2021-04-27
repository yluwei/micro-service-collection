package cn.ylw.microservice.client.factory;

import cn.ylw.microservice.client.service.UserService;
import cn.ylw.microservice.client.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 *
 * @author yanluwei
 * @date 2021/4/27
 */
@Configuration
public class BeanConfig {

    @Bean
    public ProxyFactoryBean<UserService> proxyFactoryBean() {
        ProxyFactoryBean<UserService> proxyFactoryBean = new ProxyFactoryBean<>();
        proxyFactoryBean.setTarget(new UserServiceImpl());
        return proxyFactoryBean;
    }
}
