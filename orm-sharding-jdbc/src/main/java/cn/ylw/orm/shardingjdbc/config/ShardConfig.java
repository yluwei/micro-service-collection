package cn.ylw.orm.shardingjdbc.config;

import com.google.common.collect.Lists;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 多数据源配置
 *
 * @author yanluwei
 * @date 2021/5/12
 */
@Configuration
public class ShardConfig {

    @Bean
    public DataSource shardDataSource() throws SQLException {
        // 配置数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        HikariDataSource shard1 = new HikariDataSource();
        shard1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        shard1.setJdbcUrl("jdbc:mysql://localhost:3306/shard1?useUnicode=true&characterEncoding=utf8&serverTimezone" +
            "=GMT%2B8");
        shard1.setPassword("123456");
        shard1.setUsername("root");
        HikariDataSource shard2 = new HikariDataSource();
        shard2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        shard2.setJdbcUrl("jdbc:mysql://localhost:3306/shard2?useUnicode=true&characterEncoding=utf8&serverTimezone" +
            "=GMT%2B8");
        shard2.setUsername("root");
        shard2.setPassword("123456");
        dataSourceMap.put("shard1", shard1);
        dataSourceMap.put("shard2", shard2);

        // 配置分库分表规则
        ShardingRuleConfiguration config = new ShardingRuleConfiguration();
        // 分库规则
        ShardingStrategyConfiguration db = new InlineShardingStrategyConfiguration("id", "shard$->{id % 2 +1}");
        // 分表规则
        ShardingStrategyConfiguration table = new InlineShardingStrategyConfiguration("id", "blog$->{id % 3}");
        TableRuleConfiguration tableRule = new TableRuleConfiguration("blog", "shard$->{1..2}.blog$->{0..2}");
        tableRule.setDatabaseShardingStrategyConfig(db);
        tableRule.setTableShardingStrategyConfig(table);
        config.setTableRuleConfigs(Lists.newArrayList(tableRule));

        // 相关配置
        Properties props = new Properties();
        props.put("sql.show", true);

        return ShardingDataSourceFactory.createDataSource(dataSourceMap, config, props);
    }

    @Bean
    public DataSourceTransactionManager shardDataSourceTransactionManager(DataSource shardDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(shardDataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public SqlSessionFactoryBean shardSqlSessionFactoryBean(DataSource shardDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(shardDataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer shardMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("shardSqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("cn.ylw.orm.shardingjdbc.shard.dao");
        return mapperScannerConfigurer;
    }
}
