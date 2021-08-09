package cn.ylw.common.design.bridge;

/**
 * 图表
 *
 * @author yanluwei
 * @date 2021/8/9
 */
public abstract class Chart {
    protected DataSource dataSource;

    public Chart(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public abstract String load();
}
