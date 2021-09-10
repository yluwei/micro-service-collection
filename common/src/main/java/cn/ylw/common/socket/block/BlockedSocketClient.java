package cn.ylw.common.socket.block;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 阻塞
 *
 * @author yanluwei
 * @date 2021/9/10
 */
public class BlockedSocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 19989);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader print = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s = print.readLine()).equals("exit")) {
            writer.write(s + "\n");
            writer.flush();
        }
    }
}
