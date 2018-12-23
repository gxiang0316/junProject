package com.gordon.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid数据源配置 (application.properties方式)
 */
@Configuration
//@PropertySource("classpath:jdbc.properties")指定加载配置文件
public class DruidConfig {

    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.data-username}")
    private String username;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.data-password}")
    private String password;
    @Value("${spring.datasource.type}")
    private String type;
    @Value("${spring.datasource.druid.db-type}")
    private String dbType;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${spring.datasource.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    @Value("${spring.datasource.logAbandoned}")
    private boolean logAbandoned;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;
    @Value("${spring.datasource.druid.username}")
    private String druidLoginName;
    @Value("${spring.datasource.druid.password}")
    private String druidPassword;

    @Bean(name="dataSource",initMethod = "init",destroyMethod = "close")
    @Primary //一个接口下有多个子类时，确定用哪一个呢？@Primary注解的实例优先于其他实例被注入，也可以使用@Qualifier("xxx")
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        try {
            dataSource.setUrl(URL);
            dataSource.setDbType(dbType);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setDriverClassName(driverClassName);
            dataSource.setInitialSize(initialSize);
            dataSource.setValidationQuery(validationQuery);
            dataSource.setMinIdle(minIdle);
            dataSource.setMaxActive(maxActive);
            dataSource.setMaxWait(maxWait);
            dataSource.setTestOnBorrow(testOnBorrow);
            dataSource.setTestOnReturn(testOnReturn);
            dataSource.setTestWhileIdle(testWhileIdle);
            dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            dataSource.setRemoveAbandoned(removeAbandoned);
            dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            dataSource.setLogAbandoned(logAbandoned);
            dataSource.setPoolPreparedStatements(poolPreparedStatements);
            dataSource.setFilters(filters);
            if(connectionProperties != null && !"".equals(connectionProperties)){
                Properties connectProperties = new Properties();
                String[] propertiesList = connectionProperties.split(";");
                for(String propertiesTmp:propertiesList){
                    String[] obj = propertiesTmp.split("=");
                    String key = obj[0];
                    String value = obj[1];
                    connectProperties.put(key,value);
                }
                dataSource.setConnectProperties(connectProperties);
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("druid configuration initialization filter error",e);
        }

        return dataSource;
    }

    ///////////////////// druid 监控访问设置 http://IP:PROT/druid///////////////////////
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean sRegistBean = new ServletRegistrationBean();
        sRegistBean.setServlet(new StatViewServlet());// 设置druidServlet
        sRegistBean.addUrlMappings("/druid/*");// url配置
        sRegistBean.addInitParameter("allow","192.168.74.130,127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问，多个以逗号隔开)
        sRegistBean.addInitParameter("deny","192.168.74.5");//IP黑名单 (存在共同时，deny优先于allow)
        sRegistBean.addInitParameter("loginUsername",druidLoginName);// 控制台登录用户名
        sRegistBean.addInitParameter("loginPassword",druidPassword);// 密码
        sRegistBean.addInitParameter("resetEnable","false");// 禁用HTML页面上的“Reset All”功能
        return sRegistBean;
    }

    @Bean(name = "druidWebStatFilter")
    public FilterRegistrationBean druidWebFilter(){
        FilterRegistrationBean fRegistBean = new FilterRegistrationBean();
        fRegistBean.setFilter(new WebStatFilter());// 设置druid filter
        fRegistBean.addUrlPatterns("/*");
        fRegistBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");//忽略资源
        fRegistBean.addInitParameter("profileEnable","true");//配置profileEnable能够监控单个url调用的sql列表
        return fRegistBean;
    }

}
