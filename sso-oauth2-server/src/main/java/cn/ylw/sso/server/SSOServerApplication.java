package cn.ylw.sso.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author yanluwei
 * @date 2021/4/19
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "cn.ylw.sso.server.dao")
public class SSOServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOServerApplication.class, args);
    }
}
