package cn.ylw.microservice.client.service;

/**
 * 用户
 *
 * @author yanluwei
 * @date 2021/4/27
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getUsername() {
        return "管理员用户";
    }
}
