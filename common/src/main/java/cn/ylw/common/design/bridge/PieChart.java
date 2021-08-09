package cn.ylw.common.design.bridge;

/**
 * 饼图
 *
 * @author yanluwei
 * @date 2021/8/9
 */
public class PieChart extends RefineChart {

    public PieChart(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getType() {
        return "饼状图";
    }

    public static void main(String[] args) {
        PieChart pieChart = new PieChart(new MySQLDataSource());
        System.out.println(pieChart.load());
    }
}
