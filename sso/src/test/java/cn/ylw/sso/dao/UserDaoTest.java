package cn.ylw.sso.dao;

import cn.ylw.sso.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试
 *
 * @author yanluwei
 * @date 2021/4/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findUser() {
        User admin = userDao.findByUsername("admin");
        Assert.assertEquals(new Integer(1), admin.getId());
    }

}
