package com.gordon.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 扩展Environment获取自定义properties中的值
 * 1、实现EnvironmentPostProcessor
 * 2、在resources目录下新建META-INF/spring.factories文件，并在文件中添加如下内容：
 *    org.springframework.boot.env.EnvironmentPostProcessor=com.gordon.springboot.config.CustomEnvironment
 *
 * Created by gordon on 2019/2/27.
 */
@Component
public class CustomEnvironment implements EnvironmentPostProcessor {

    // org.springframework.boot.context.logging.ClasspathLoggingApplicationListener -
    // Application failed to start with classpath: [file:/F:/WorkSpace/IDEA_Space/junProject/SpringBoot-Web/target/classes/]

    /**
     * // 方法1：获取文件或流
     * this.getClass().getResource("/")+fileName;
     * this.getClass().getResourceAsStream(failName);
     * // 方法2：获取文件
     * File file = org.springframework.util.ResourceUtils.getFile("classpath:test.txt");
     * // 方法3：获取文件或流
     * ClassPathResource classPathResource = new ClassPathResource("test.txt");
     * classPathResource .getFile();
     * classPathResource .getInputStream();
     *
     * // >>>>>>>>>>>>>>>> 下面方法可以读取jar包下文件
     * 假设resources目录下有一个test.txt文件，首先获得当前的类加载器，通过类加载器读取文件。
     *
     * // 方法1
     * InputStream io = Thread.currentThread().getContextClassLoader().getResourceAsStream("test
     * .txt");
     * // 方法2
     * InputStream io = getClass().getClassLoader().getResourceAsStream("test.txt");
     *
     */

    private static String[] propertList = new String[]{"sysParam.properties"};

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try{
            for(int i = 0 ; i < propertList.length ; i++) {
                InputStream inputStream = application.getClassLoader().getResourceAsStream(propertList[i]);
//            ResourceLoader resourceLoader = application.getResourceLoader();// resourceLoader 是 null
//            InputStream inputStream = resourceLoader.getResource("sysParam.properties").getInputStream();
                Properties properties = new Properties();
                properties.load(inputStream);
                PropertiesPropertySource propertiesPropertySource =
                        new PropertiesPropertySource(propertList[i].split("\\.")[0], properties);
                environment.getPropertySources().addLast(propertiesPropertySource);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
