package cn.ylw.microservice.client.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 初始化测试
 *
 * @author yanluwei
 * @date 2021/11/5
 */
//@Component
public class InitObject implements InitializingBean, BeanPostProcessor, DisposableBean, CommandLineRunner,
    ApplicationListener<ApplicationContextEvent>, ApplicationContextAware, ApplicationRunner {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessBeforeInitialization");
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessBeforeInitialization");
//        return bean;
//    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        System.out.println("onApplicationEvent");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner");
    }
}
