package cn.ylw.orm.shardingjdbc.shard.entity;

import lombok.Data;

/**
 * 博客
 *
 * @author yanluwei
 * @date 2020/10/22
 */
@Data
public class Blog {
    private Integer id;
    private String publishTime;
    private String title;
}
