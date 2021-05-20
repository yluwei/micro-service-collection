package cn.ylw.common.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 服务实现
 *
 * @author yanluwei
 * @date 2021/5/20
 */
public class WhoAreYouImpl extends UnicastRemoteObject implements WhoAreYou {

    protected WhoAreYouImpl() throws RemoteException {
        super();
    }

    @Override
    public void whoAreYou(User user) throws RemoteException {
        System.out.println("i`m " + user.getName() + ", and i`m " + user.getAge() + " years old");
    }
}
