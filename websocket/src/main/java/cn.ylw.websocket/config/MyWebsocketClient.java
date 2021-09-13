package cn.ylw.websocket.config;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * TODO
 *
 * @author yanluwei
 * @date 2021/9/13
 */
public class MyWebsocketClient extends WebSocketClient {

    public MyWebsocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("客户端连接成功");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("客户端收到：" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("关闭socket客户端");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("客户端发生异常");
    }

    @Override
    public void connect() {
        super.connect();
        System.out.println("开始连接");
    }

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        MyWebsocketClient my = new MyWebsocketClient(new URI("ws://localhost:8080/websocket"));
        my.setConnectionLostTimeout(10);
        my.connect();
        Thread.sleep(100000);
    }
}
