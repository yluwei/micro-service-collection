package cn.ylw.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 网关
 * 注意，由于网关用的是webflux，不能引入spring-boot-starter-web，因为用的是spring MVC模式
 *
 * @author yanluwei
 * @date 2021/4/23
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
