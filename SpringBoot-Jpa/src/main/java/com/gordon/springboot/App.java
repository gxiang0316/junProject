package com.gordon.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
    // SpringBoot JPA 系列文章：http://www.spring4all.com/article/500
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
