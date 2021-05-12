package cn.ylw.orm.shardingjdbc.readwriter.controller;

import cn.ylw.orm.shardingjdbc.readwriter.dao.UserDao;
import cn.ylw.orm.shardingjdbc.readwriter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 读写分离
 *
 * @author yanluwei
 * @date 2021/5/12
 */
@RestController
public class ReadWriterController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/read")
    public List<User> users() {
        return userDao.listUsers();
    }

    @GetMapping("/write")
    public String writerUser() {
        User user = new User();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        String format = dateTimeFormatter.format(LocalDateTime.now());
        user.setName("张三" + format);
        user.setRole("角色" + format);
        userDao.insetUser(user);
        return "ok";
    }
}
