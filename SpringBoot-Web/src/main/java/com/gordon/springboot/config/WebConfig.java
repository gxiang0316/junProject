package com.gordon.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * web.xml 配置
 * https://blog.csdn.net/IT_faquir/article/details/79521417
 */
@Configuration
public class WebConfig {

    /**
     * 配置跨域
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);// 允许写cookie
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config); // CORS 配置对所有接口都有效
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    /**
     * 注册filter
     * POST提交表单中文乱码解决方案。
     */
    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharacterEncodingFilter());
        registration.addUrlPatterns("/*"); //POST提交表单中文乱码解决方案。
        registration.addInitParameter("encoding", "UTF-8"); //
        registration.addInitParameter("forceEncoding", "true"); //
        registration.setName("characterEncodingFilter");
        registration.setOrder(1);
        return registration;
    }

//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//        //1、创建FastJson信息转换对象
//        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
//        //2、创建FastJsonConfig对象并设定序列化规则  序列化规则详见SerializerFeature类中，后面会讲
//        FastJsonConfig fastJsonConfig= new FastJsonConfig();
//        SerializerFeature writeMapNullValue = SerializerFeature.WriteMapNullValue;
//        SerializerFeature WriteNullStringAsEmpty = SerializerFeature.WriteNullStringAsEmpty;
//        SerializerFeature WriteNullNumberAsZero = SerializerFeature.WriteNullNumberAsZero;
//        SerializerFeature WriteNullListAsEmpty = SerializerFeature.WriteNullListAsEmpty;
//        fastJsonConfig.setSerializerFeatures(writeMapNullValue, WriteNullStringAsEmpty,
//                WriteNullNumberAsZero, WriteNullListAsEmpty);
//        //fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteNonStringKeyAsString);
//        //本人就坑在WriteNonStringKeyAsString 将不是String类型的key转换成String类型，否则前台无法将Json字符串转换成Json对象
//
//        //3、中文乱码解决方案
//        List<MediaType> fastMedisTypes = new ArrayList<MediaType>();
//        fastMedisTypes.add(MediaType.APPLICATION_JSON_UTF8);//设定Json格式且编码为utf-8
//        fastConverter.setSupportedMediaTypes(fastMedisTypes);
//        //4、将转换规则应用于转换对象
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        return new HttpMessageConverters(fastConverter);
//    }


//    @Bean
//    public FilterRegistrationBean authFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new AuthFilter());
//        registration.addUrlPatterns("/webapi/*"); //
//        registration.addInitParameter("paramName", "paramValue"); //
//        registration.setName("authFilter");
//        registration.setOrder(2);///执行顺序
//        return registration;
//    }

    /**
     * 注册Servlet
     */
//    @Bean
//    public static ServletRegistrationBean servletRegistrationBean(){
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
//        servletRegistrationBean.setServlet(new MyServlet2());
//        servletRegistrationBean.addUrlMappings("/myservlet2");
//        servletRegistrationBean.addInitParameter("paramName2", "myvalue2");
//        return servletRegistrationBean;
//    }

    /**
     * 注册Listener
     */
//    @Bean
//    public static ServletListenerRegistrationBean servletListenerRegistrationBean(){
//        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
//        servletListenerRegistrationBean.setListener(new MyServletRequestListener2());
//        return  servletListenerRegistrationBean;
//    }

    /**
     * 异常页面处理：
     *      https://blog.csdn.net/trusause/article/details/84299886
     *  Filter/servlet配置：https://www.cnblogs.com/jpfss/p/9045916.html
     *      AOP日志打印：https://blog.csdn.net/qq_22860341/article/details/81236256
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            public void customize(ConfigurableWebServerFactory container) {
                ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400.html");
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html");
                ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");

                container.addErrorPages(error400Page, error401Page,error403Page, error404Page, error500Page);
            }
        };
    }

    /**
     // 在主类采用bean方法注册
     @Bean
     public ServletRegistrationBean getServlet1(){
        return new ServletRegistrationBean(new MyServlet(),"/my1");
     }

     @Bean
     public ServletRegistrationBean getServlet2(){
        return new ServletRegistrationBean(new MyServlet2(),"/my2");
     }

     @Bean
     public FilterRegistrationBean getFilter(){
         // 过滤所有请求
         //	return new FilterRegistrationBean(new MyFilter());
         // 过滤指定的servlet
         return new FilterRegistrationBean(new MyFilter(),getServlet1());
     }

     @Bean
     public FilterRegistrationBean getFilter2(){
         // 过滤多个指定的servlet
         return new FilterRegistrationBean(new MyFilter2(),getServlet2(),getServlet1());
     }

     @Bean
     public ServletListenerRegistrationBean<Mylistener> getListener(){
        return new ServletListenerRegistrationBean<>(new Mylistener());
     }

     */

}
