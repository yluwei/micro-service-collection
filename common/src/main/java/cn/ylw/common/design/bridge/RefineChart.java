package cn.ylw.common.design.bridge;

/**
 * 图表
 *
 * @author yanluwei
 * @date 2021/8/9
 */
public abstract class RefineChart extends Chart {
    public RefineChart(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String load() {
        return dataSource.query() + this.getType();
    }

    public abstract String getType();
}
