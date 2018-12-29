package com.gordon.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 自定义springmvc配置 ： https://blog.csdn.net/icarusliu/article/details/79477343
 * SpringBoot2.0版本使用WebMvcConfigurationSupport 来扩展SpringMvc的功能
 * 注意：关于扩展，有一个特殊的注解 @EnableWebMvc ，他的作用是全面接管SpringMvc的配置，
 *  也就是说SpringBoot的所有关于SpringMvc的自动配置都会失效（包括静态资源页的访问等等），而需要我们自己手动配置所有的类；
 *
 *  SpringBoot默认配置下，提供了以下几个静态资源目录：
 *      /static：classpath:/static/
 *      /public：classpath:/public/
 *      /resources：classpath:/resources/
 *      /META-INF/resources：classpath:/META-INF/resources/
 *      优先级顺序为：META/resources > resources > static > public
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurationSupport {






}
