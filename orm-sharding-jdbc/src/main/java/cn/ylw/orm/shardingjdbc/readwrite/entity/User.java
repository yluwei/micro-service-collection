package cn.ylw.orm.shardingjdbc.readwrite.entity;

import lombok.Data;

/**
 * 用户
 *
 * @author yanluwei
 * @date 2021/5/12
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String role;
}
