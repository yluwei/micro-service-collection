package cn.ylw.orm.shardingjdbc.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 多数据源配置
 *
 * @author yanluwei
 * @date 2021/5/12
 */
@Configuration
public class ReadWriteConfig {

    @Bean
    public DataSourceTransactionManager readWriteDataSourceTransactionManager(DataSource masterSlaveDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(masterSlaveDataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public SqlSessionFactoryBean readWriteSqlSessionFactoryBean(DataSource masterSlaveDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterSlaveDataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer readWriteMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("readWriteSqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("cn.ylw.orm.shardingjdbc.readwrite.dao");
        return mapperScannerConfigurer;
    }
}
