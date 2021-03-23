package cn.ylw.sso.controller;

import cn.ylw.sso.vo.HelloWorldVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author yanluwei
 * @date 2021/3/23
 */
@RestController
public class HelloController {

    @GetMapping("/hello/world")
    public HelloWorldVO hello(String hello, Integer world) {
        HelloWorldVO helloWorldVO = new HelloWorldVO();
        if (StringUtils.isNotBlank(hello)) {
            helloWorldVO.setHello(hello);
        }
        if (world != null) {
            helloWorldVO.setWorld(world);
        }
        return helloWorldVO;
    }
}
