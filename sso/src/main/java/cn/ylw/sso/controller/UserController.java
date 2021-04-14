package cn.ylw.sso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取用户
 *
 * @author yanluwei
 * @date 2021/4/14
 */
@Controller
public class UserController {

    // 登录页
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/user/detail")
    @ResponseBody
    public Authentication getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/admin/test")
    public String test() {
        return "you are permitted";
    }

    @GetMapping("/test")
    public String testAuthority() {
        return "you are permitted test";
    }
}
