package cn.ylw.sso.dao;

import cn.ylw.sso.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author yanluwei
 * @date 2021/4/9
 */
@Repository
public interface UserDao {

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
}
