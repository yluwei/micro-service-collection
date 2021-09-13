package cn.ylw.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 配置
 *
 * @author yanluwei
 * @date 2021/9/13
 */
@Configuration
public class ClientConfig {
    @Bean
    public MyWebsocketClient myWebsocketClient() throws URISyntaxException {
        MyWebsocketClient my = new MyWebsocketClient(new URI("ws://localhost:8080/websocket"));
        my.setConnectionLostTimeout(10000);
        my.connect();
        return my;
    }
}
