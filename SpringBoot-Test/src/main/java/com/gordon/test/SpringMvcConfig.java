package com.gordon.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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

//    @Value("${web.classpath}")
//    private String classpath;
//    @Value("${web.templates}")
//    private String templates;
//    @Value("${web.prefix}")
//    private String prefix;
//    @Value("${web.suffix}")
//    private String suffix;


    /**
     * 继承 WebMvcConfigurationSupport后 不需要配置DispatcherServlet，有默认的。
     */

    /**
     * 视图转换器
     * 配置ViewResolvers来将Controller返回的String类型的视图名称转换成视图
     * @param registry
     */
    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        //super.configureViewResolvers(registry);
        /**
         * 添加HTML的视图解析器
         * 本示例完成以下功能：将Controller返回的String类型的视图名称添加前缀及后缀；
         * 如返回的是a，那么处理后对应的视图将会是/a.html
         */
        //registry.viewResolver(new InternalResourceViewResolver("/",".html"));

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix(prefix);// 前缀
        viewResolver.setPrefix("classpath:/templates/");// 前缀
        viewResolver.setSuffix(".html");// 后缀
//        viewResolver.setSuffix(suffix);// 后缀
        registry.viewResolver(viewResolver);
    }

    /**
     * 配置静态资源访问的路径规则以及查找静态资源的路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //处理所有HTML的请求，到templates目录下查找对应的资源
//        registry.addResourceHandler("/**.html") //处理的路径规则
//            .addResourceLocations("classpath:/templates/"); //到哪些目录下去查找静态资源

        //处理所有静态的请求，到static目录下查找对应的资源
        registry.addResourceHandler("/statics/**") //处理的路径规则
            .addResourceLocations("classpath:/static/"); //到哪些目录下去查找静态资源

    }

    /**
     * 添加对象转换Convert和格式化Format，如设置日期的展示格式等。
     * @param registry
     */
    @Override
    protected void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * spring mvc 文件上传配置
     * MultipartResolver 的实现类有两个：
     *     CommonsMultipartResolver
     *     StandardServletMultipartResolver
     *
     * 两个的区别：
     *     第一个需要使用 Apache 的 commons-fileupload 等 jar 包支持，但它能在比较旧的 servlet 版本中使用。
     *     第二个不需要第三方 jar 包支持，它使用 servlet 内置的上传功能，但是只能在 Servlet 3 以上的版本使用。
     *
     * 第一个使用步骤：
     * CommonsMultipartResolver  上传用到的两个包
             *"commons-fileupload:commons-fileupload:1.3.1",
             *"commons-io:commons-io:2.4"
     * @return
     */
    @Bean(name= DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
    public StandardServletMultipartResolver initMultipartResolver(){
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setDefaultEncoding("UTF-8");
//        multipartResolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//        multipartResolver.setMaxInMemorySize(10*1024*1024);//低于此值，只保留在内存里，超过此阈值，生成硬盘上
//        multipartResolver.setMaxUploadSize(50*1024*1024);// 限制上传文件大小 50M

        return multipartResolver;
    }

    /**
     * 进行跨域访问相关配置
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/test1/**") //可以跨域访问的URL路规则
//            .allowedOrigins("http://localhost:8080/") //可以跨域访问的访问者
//            .allowedMethods("GET", "POST", "PUT", "DELETE") //可以跨域访问的方法
//            .allowedHeaders(""); //可以跨域访问的Headers
//    }


}
