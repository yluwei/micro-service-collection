package cn.ylw.sso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取用户
 *
 * @author yanluwei
 * @date 2021/4/14
 */
@RestController
public class UserController {

    @GetMapping("/user/detail")
    public Authentication getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
}
