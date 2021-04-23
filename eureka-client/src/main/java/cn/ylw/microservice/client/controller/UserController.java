package cn.ylw.microservice.client.controller;

import cn.ylw.microservice.client.service.SsoServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/sso/server/hello")
    public String ssoServer() {
        return ssoServerClient.hello();
    }
}
