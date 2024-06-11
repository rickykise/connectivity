package ai.fassto.connectivity.dataaccess.common.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(
        basePackages = {
                "ai.fassto.connectivity.dataaccess.customer.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.item.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.purchaseorder.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.salesorder.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.workorder.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.stock.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.parcel.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.common.repository.mybatis",
                "ai.fassto.connectivity.dataaccess.scheduler.repository.mybatis"
        },
        sqlSessionFactoryRef = "primaryDbSqlSessionFactory"
)
public class PrimaryDbConfig {
    @Primary
    @Bean(name = "primaryDbDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource primaryDbDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //Mybatis
    @Primary
    @Bean(name = "primaryDbSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml")
        );
        sqlSessionFactoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")
        );

        return sqlSessionFactoryBean.getObject();
    }
}
