package cn.ylw.orm.shardingjdbc.shard.controller;

import cn.ylw.orm.shardingjdbc.shard.dao.BlogDao;
import cn.ylw.orm.shardingjdbc.shard.entity.Blog;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 博客
 *
 * @author yanluwei
 * @date 2020/10/22
 */
@RestController
public class ShardController {

    @Autowired
    private BlogDao blogDao;

    @GetMapping("/getBlog")
    public List<Blog> listBlog() {
        return blogDao.listBlog();
    }

    @GetMapping("/addBlog")
    public String addBlog(Integer id) {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setPublishTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        blog.setTitle("分库分表");
        blogDao.insertBlog(blog);
        return "ok";
    }
}
