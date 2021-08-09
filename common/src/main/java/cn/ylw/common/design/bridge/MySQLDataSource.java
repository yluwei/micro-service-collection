package cn.ylw.common.design.bridge;

/**
 * mysql查询
 *
 * @author yanluwei
 * @date 2021/8/9
 */
public class MySQLDataSource implements DataSource {
    @Override
    public String query() {
        return "从MySQL去查询";
    }
}

