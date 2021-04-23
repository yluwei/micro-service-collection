package cn.ylw.microservice.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign调用
 *
 * @author yanluwei
 * @date 2021/4/23
 */
@FeignClient(name = "sso-server")
public interface SsoServerClient {

    @GetMapping("/hello")
    String hello();
}
