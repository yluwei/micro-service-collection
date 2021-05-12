package cn.ylw.orm.shardingjdbc.readwriter.dao;

import cn.ylw.orm.shardingjdbc.readwriter.entity.User;
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
