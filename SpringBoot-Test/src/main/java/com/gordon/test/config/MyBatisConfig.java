package com.gordon.test.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
//@MapperScan("com.gordon.springboot.mapper")//指定扫描的mapper接口所在的包，也可以直接在启动类上用此注解
public class MyBatisConfig {

    private Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    /**在DruidConfig中配置的数据源*/
    @Autowired
    private DataSource dataSource;

    @Value("${mybatis.config-location}")
    private String configLocation;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis.configuration.cache-enabled}")
    private boolean cacheEnabled;
    @Value("${mybatis.configuration.lazy-loading-enabled}")
    private boolean lazyLoadingEnabled;
    @Value("${mybatis.configuration.aggressive-lazy-loading}")
    private boolean aggressiveLazyLoading;
    @Value("${mybatis.configuration.multiple-result-sets-enabled}")
    private boolean multipleResultSetsEnabled;
    @Value("${mybatis.configuration.use-column-label}")
    private boolean useColumnLabel;
    @Value("${mybatis.configuration.use-generated-keys}")
    private boolean useGeneratedKeys;
    @Value("${mybatis.configuration.auto-mapping-behavior}")
    private String autoMappingBehavior;
    @Value("${mybatis.configuration.default-executor-type}")
    private String defaultExecutorType;
    @Value("${mybatis.configuration.map-underscore-to-camel-case}")
    private boolean mapUnderscoreToCamelCase;
    @Value("${mybatis.configuration.local-cache-scope}")
    private String localCacheScope;
    @Value("${mybatis.configuration.jdbc-type-for-null}")
    private String jdbcTypeForNull;
    @Value("${mybatis.configuration.default-statement-timeout}")
    private int defaultStatementTimeout;
    @Value("${mybatis.configuration.call-setters-on-nulls}")
    private boolean callSettersOnNulls;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory buildSessionFactory(){
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

            org.apache.ibatis.session.Configuration configuration =
                    new org.apache.ibatis.session.Configuration();
            configuration.setCacheEnabled(cacheEnabled);
            configuration.setLazyLoadingEnabled(lazyLoadingEnabled);
            configuration.setAggressiveLazyLoading(aggressiveLazyLoading);
            configuration.setMultipleResultSetsEnabled(multipleResultSetsEnabled);
            configuration.setUseColumnLabel(useColumnLabel);
            configuration.setUseGeneratedKeys(useGeneratedKeys);
            configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
            configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
            configuration.setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
            configuration.setLocalCacheScope(LocalCacheScope.SESSION);
            configuration.setJdbcTypeForNull(JdbcType.NULL);
            configuration.setDefaultStatementTimeout(defaultStatementTimeout);
            configuration.setCallSettersOnNulls(callSettersOnNulls);

            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            sessionFactoryBean.setConfigLocation(resourcePatternResolver.getResource(configLocation));
            sessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));

            // 添加分页插件 配置多数据源时使用这种方式
            //PageInterceptor pageInterceptor = this.initPageInterceptor();
            //sessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("MyBatis configuration initialization error",new Throwable(e));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MyBatis configuration initialization error",new Throwable(e));
            return null;
        }
    }

    /**
     *  SqlSessionTemplate 是 MyBatis-Spring 的核心。 这个类负责管理 MyBatis 的 SqlSession,
     *      调用 MyBatis 的 SQL 方法, 翻译异常。 SqlSessionTemplate 是线程安全的, 可以被多个 DAO 所共享使用。
     *
     * 当调用 SQL 方法时, 包含从映射器 getMapper()方法返回的方法, SqlSessionTemplate 将会保证使用的
     *      SqlSession 是和当前 Spring 的事务相关的。此外,它管理 session 的生命 周期,包含必要的关闭,提交或回滚操作。
     *
     * 具体参考：http://www.mybatis.org/spring/zh/sqlsession.html
     */
    @Bean
    public SqlSessionTemplate buildSessionTemplate(
            @Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 指定事务管理器，并在启动类中添加@EnableTransactionManagement注解
     * 使用：在service类上或方法上添加@Transactional，如果加在类上表示所有方法支持事务
     * 具体参考：https://www.cnblogs.com/softidea/p/5877546.html
     */
    @Bean
    public PlatformTransactionManager buildTransactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 多数据源分页配置
     * https://juejin.im/entry/5b33616f51882574ec30acfa
     * @return
     */
    public PageInterceptor initPageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "false");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    /**
     * 分页插件配置
     *   如果配置了多数据源，就要用到上面的方式然后在每个sessionFactoryBean里面添加
     *   https://www.cnblogs.com/xuwujing/p/8964927.html
     * @return
     */
    @Bean
    public PageHelper initPageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
