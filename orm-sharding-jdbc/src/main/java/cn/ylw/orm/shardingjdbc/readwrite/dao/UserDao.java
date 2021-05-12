package cn.ylw.orm.shardingjdbc.readwrite.dao;

import cn.ylw.orm.shardingjdbc.readwrite.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 * @author yanluwei
 * @date 2021/5/12
 */
@Repository
public interface UserDao {

    List<User> listUsers();

    int insetUser(User user);
}
