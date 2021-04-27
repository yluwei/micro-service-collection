package cn.ylw.microservice.client.controller;

import cn.ylw.microservice.client.factory.ProxyFactoryBean;
import cn.ylw.microservice.client.service.SsoServerClient;
import cn.ylw.microservice.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author yanluwei
 * @date 2021/4/20
 */
@RestController
public class UserController {

    @Autowired
    private SsoServerClient ssoServerClient;

    @Autowired
    private ProxyFactoryBean<UserService> proxyFactoryBean;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/sso/server/hello")
    public String ssoServer() {
        return ssoServerClient.hello();
    }

    @GetMapping("/user")
    public String user(@RequestHeader("user-id") Integer userId,
                       @RequestHeader("user-name") String userName) {
        return userName + ":" + userId;
    }

    @GetMapping("/factory/bean")
    public String factoryBean() throws Exception {
        UserService object = proxyFactoryBean.getObject();
        return object.getUsername();
    }
}
