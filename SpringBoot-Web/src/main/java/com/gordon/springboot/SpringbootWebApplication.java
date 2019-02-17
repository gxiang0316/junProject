package com.gordon.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
// 此处将会加载MyBatisConfig中定义的PlatformTransactionManager
@EnableTransactionManagement
@MapperScan("com.gordon.springboot.mapper")
@SpringBootApplication
public class SpringbootWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
        System.out.println();
        System.out.println("        ***************************************************");
        System.out.println("        *                                                 *");
        System.out.println("        *               Welcome Gordon Web                *");
        System.out.println("        *                                                 *");
        System.out.println("        ***************************************************");
        System.out.println();
        System.out.println();
    }

    /**部署到外部tomcat，继承SpringBootServletInitializer，重写configure方法
     *  配置Edit configurations 注意
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpringbootWebApplication.class);
    }
}
