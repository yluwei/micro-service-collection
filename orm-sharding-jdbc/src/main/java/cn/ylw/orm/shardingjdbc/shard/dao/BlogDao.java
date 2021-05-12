package cn.ylw.orm.shardingjdbc.shard.dao;

import cn.ylw.orm.shardingjdbc.shard.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客
 *
 * @author yanluwei
 * @date 2020/10/19
 */
@Repository
public interface BlogDao {
    List<Blog> listBlog();

    int insertBlog(Blog blog);
}
