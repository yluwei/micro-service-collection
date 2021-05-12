package cn.ylw.orm.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author yanluwei
 * @date 2021/5/12
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.ylw.orm.shardingjdbc.**.dao"})
public class ShardingJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
}
