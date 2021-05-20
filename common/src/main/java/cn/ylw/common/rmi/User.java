package cn.ylw.common.rmi;

import java.io.Serializable;

/**
 * 用户
 *
 * @author yanluwei
 * @date 2021/5/20
 */
public class User implements Serializable {

    private Integer age;
    private String name;

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
