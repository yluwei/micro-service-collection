package cn.ylw.common.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * 服务端
 *
 * @author yanluwei
 * @date 2021/5/20
 */
public class RmiServer {
    public static void main(String[] args) {
        try {
            WhoAreYou whoAreYou = new WhoAreYouImpl();
            LocateRegistry.createRegistry(8080);
            Naming.bind("//localhost:8080/who", whoAreYou);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
