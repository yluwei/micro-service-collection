package cn.ylw.sso.resources.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/user")
    public Object user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

    @GetMapping("/money")
    public String money() {
        return "money";
    }
}
