package cn.ylw.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 服务接口
 *
 * @author yanluwei
 * @date 2021/5/20
 */
public interface WhoAreYou extends Remote {
    void whoAreYou(User user) throws RemoteException;
}
