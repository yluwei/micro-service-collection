package cn.ylw.microservice.gateway.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断回调
 *
 * @author yanluwei
 * @date 2021/4/23
 */
@RestController
public class CallbackController {

    @GetMapping("/fail")
    public String fail() {
        return "request timeout";
    }
}
