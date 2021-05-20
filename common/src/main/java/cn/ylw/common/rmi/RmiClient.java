package cn.ylw.common.rmi;

import java.rmi.Naming;

/**
 * 调用客户端
 *
 * @author yanluwei
 * @date 2021/5/20
 */
public class RmiClient {
    public static void main(String[] args) {
        try {
            WhoAreYou lookup = (WhoAreYou) Naming.lookup("//localhost:8080/who");
            lookup.whoAreYou(new User(18, "小明"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
