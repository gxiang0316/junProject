package com.gordon.springboot.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 学习而已，生产环境肯定不会使用内置tomcat，连此项目都被改成这本地tomcat了。内置tomcat路径太深了，不好找
 * tomcat 配置 https://blog.csdn.net/wd2014610/article/details/79587161
 */
//@Configuration
public class TomcatConfig {

    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.session.timeout}")
    private String timeout;
    @Value("${server.servlet.context-path}")
    private String path;
    @Value("${server.tomcat.uri-encoding}")
    private String encoding;
    @Value("${server.tomcat.max-threads}")
    private String maxThreads;
    @Value("${server.tomcat.max-connections}")
    private String maxConnections;
    @Value("${server.tomcat.accept-count}")
    private String acceptCount;
    @Value("${server.tomcat.min-spare-threads}")
    private String minSpareThreads;
    @Value("${server.tomcat.max-spare-threads}")
    private String maxSpareThreads;
    @Value("${server.tomcat.connctionTimeout}")
    private String connctionTimeout;
    @Value("${server.MaxFileSize}")
    private String maxFileSize;
    @Value("${server.MaxRequestSize}")
    private String maxRequestSize;
    @Value("${server.protocol}")
    private String protocol;

    @Bean
    public ServletWebServerFactory initServletContainer(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(new GwsTomcatConnectionCustomizer());
        return tomcat;
    }

    @Bean
    public MultipartConfigElement initMultipartConfig(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(maxFileSize); // KB,MB
        // 总上传数据大小
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }

    /**
     * 默认http连接
     */
    public class GwsTomcatConnectionCustomizer implements TomcatConnectorCustomizer {
         public GwsTomcatConnectionCustomizer() {

         }
         @Override
         public void customize(Connector connector) {
             connector.setPort(Integer.valueOf(port));
             connector.setAttribute("connectionTimeout", connctionTimeout);
             connector.setAttribute("minSpareThreads", minSpareThreads);
             connector.setAttribute("maxSpareThreads", maxSpareThreads);
             connector.setAttribute("maxThreads", maxThreads);
             connector.setAttribute("maxConnections", maxConnections);
             connector.setAttribute("protocol", protocol);
//             connector.setAttribute("acceptorThreadCount", acceptorThreadCount);
//             connector.setAttribute("redirectPort", redirectPort);
//             connector.setAttribute("compression", compression);
         }
    }


}
