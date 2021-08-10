package cn.ylw.common.design.build;

import lombok.Getter;

import java.util.Date;

/**
 * 房屋
 *
 * @author yanluwei
 * @date 2021/8/10
 */
@Getter
public class House {
    private String name;
    private Long square;
    private String position;
    private Date create;

    public House(String name, Long square, String position, Date create) {
        this.name = name;
        this.square = square;
        this.position = position;
        this.create = create;
    }

    @Override
    public String toString() {
        return "House{" +
            "name='" + name + '\'' +
            ", square=" + square +
            ", position='" + position + '\'' +
            ", create=" + create +
            '}';
    }
}
