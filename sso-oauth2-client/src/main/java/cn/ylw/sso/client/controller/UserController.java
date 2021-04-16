package cn.ylw.sso.client.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户
 *
 * @author yanluwei
 * @date 2021/4/16
 */
@Controller
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    public Authentication hello() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }
}
