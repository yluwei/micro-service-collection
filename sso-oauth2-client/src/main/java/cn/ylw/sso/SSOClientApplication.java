package cn.ylw.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * oauth2客户端启动类
 *
 * @author yanluwei
 * @date 2021/4/16
 */
@SpringBootApplication
public class SSOClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOClientApplication.class, args);
    }
}
