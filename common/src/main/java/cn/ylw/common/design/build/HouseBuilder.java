package cn.ylw.common.design.build;

import java.util.Date;

/**
 * 建造器
 *
 * @author yanluwei
 * @date 2021/8/10
 */
public class HouseBuilder {
    private String name;
    private Long square;
    private String position;
    private Date create;

    public static HouseBuilder builder() {
        return new HouseBuilder();
    }

    public HouseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public HouseBuilder square(Long square) {
        this.square = square;
        return this;
    }

    public HouseBuilder position(String position) {
        this.position = position;
        return this;
    }

    public HouseBuilder create() {
        this.create = new Date();
        return this;
    }

    public House build() {
        return new House(this.name, this.square, this.position, this.create);
    }

    public static void main(String[] args) {
        House house = HouseBuilder.builder()
            .name("美丽家园")
            .square(1000L)
            .position("东经北纬")
            .create()
            .build();
        System.out.println(house);
    }
}
