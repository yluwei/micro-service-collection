package cn.ylw.common.socket.block;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 阻塞
 *
 * @author yanluwei
 * @date 2021/9/10
 */
@Slf4j
public class BlockedSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress socketAddress = new InetSocketAddress("0.0.0.0", 19989);
        serverSocket.bind(socketAddress);
        Socket accept = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        String s;
        while (!(s = reader.readLine()).equals("bye")) {
            log.info("收到{}消息：{}", accept.getInetAddress().getHostAddress(), s);
            writer.write("我收到了：" + s + "\n");
            writer.flush();
        }
    }
}
