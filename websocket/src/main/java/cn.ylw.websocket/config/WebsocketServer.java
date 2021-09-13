package cn.ylw.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 配置websocket
 *
 * @author yanluwei
 * @date 2021/9/13
 */
@Component
@ServerEndpoint("/websocket")
public class WebsocketServer {

    private static Session session;

    public static Session getSession() {
        return session;
    }

    @OnOpen
    public void open(Session session) {
        WebsocketServer.session = session;
        System.out.println(session);
        System.out.println("连接");
    }

    @OnMessage
    public void message(String message, Session session) throws IOException, EncodeException {
        System.out.println("消息:" + message);
        session.getBasicRemote().sendText("sdflasjdfljslfjl");
    }

    @OnClose
    public void close() {
        System.out.println("关闭");
    }
}
