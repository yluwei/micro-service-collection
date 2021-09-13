package cn.ylw.websocket.config;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 发送
 *
 * @author yanluwei
 * @date 2021/9/13
 */
@RestController
@RequestMapping("/send")
public class WsController {

    @GetMapping
    public void sendPong(String text) throws IOException {
        Session session = WebsocketServer.getSession();
        if (StringUtils.isEmpty(text)) {
            text = "oxoA";
        }
        session.getBasicRemote().sendPong(ByteBuffer.allocate(0));
    }
}
