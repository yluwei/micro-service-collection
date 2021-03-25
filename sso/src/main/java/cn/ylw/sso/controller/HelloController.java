package cn.ylw.sso.controller;

import cn.ylw.sso.vo.HelloWorldVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 测试
 *
 * @author yanluwei
 * @date 2021/3/23
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello/world")
    public HelloWorldVO hello(String hello, Integer world, HttpServletRequest request) {
        HelloWorldVO helloWorldVO = new HelloWorldVO();
        if (StringUtils.isNotBlank(hello)) {
            helloWorldVO.setHello(hello);
        }
        if (world != null) {
            helloWorldVO.setWorld(world);
        }
        String ipAddr = getIpAddr(request);
        helloWorldVO.setHello(ipAddr);
        return helloWorldVO;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            log.info("x-forwarded-for:", ipAddress);
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
                log.info("Proxy-Client-IP:", ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
                log.info("WL-Proxy-Client-IP:", ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        log.info("本机Host：", inet);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                    log.info("本机ip：", ipAddress);
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                    log.info("多个代理分隔后ip：", ipAddress);
                }
            }
        } catch (Exception e) {
            ipAddress = "";
            log.info("发生异常，原因：{}", e);
        }
        return ipAddress;
    }
}
