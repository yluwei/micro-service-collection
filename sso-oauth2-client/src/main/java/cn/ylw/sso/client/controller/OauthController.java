package cn.ylw.sso.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * oauth2获取授权码
 *
 * @author yanluwei
 * @date 2021/4/16
 */
@RestController
@RequestMapping("/login/oauth2")
@Slf4j
public class OauthController {

    @GetMapping("/code/github111")
    public String getCode(@RequestParam("code") String code) {
        log.info("授权码：{}", code);
        return code;
    }
}
