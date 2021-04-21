package cn.ylw.sso.resources.controller;

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

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
