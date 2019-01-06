package com.gordon.test.config;

//import org.beetl.core.resource.ClasspathResourceLoader;
//import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
//import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://blog.csdn.net/jiandao230/article/details/81035374
 *
 * starter 自动处理以btl结尾的视图，模板根目录是Spring Boot默认的templates目录。如下配置可以修改beetl部分属性
 *     beetl-beetlsql.dev,默认为true，即自动检查模板变化
 *     beetl.enabled 默认为true，集成beetl。
 *     beetl.suffix 默认为btl，表示只处理视图后缀为btl的模板，
 *      比如controller里代码是“return /common/index.btl”,则能被Beetl处理，
 *      你写成"return /common/index",或者"/common/index.html",都会出现404错误。
 */
//@Configuration
public class BeetlConf {

    private Logger logger = LoggerFactory.getLogger(BeetlConf.class);

    @Value("${beetl.templatesPath}")
    private String templatesPath;

    @Value("${beetl.suffix}")
    private String suffix;

    // 1.1.70.RELEASE 没有这个属性
    @Value("${RESOURCE.autoCheck}")
    private boolean autoCheck;


//    @Bean(name = "beetlConfig")
//    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
//        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
//        //获取Spring Boot 的ClassLoader
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        if(loader==null){
//            loader = BeetlConf.class.getClassLoader();
//        }
////        beetlGroupUtilConfiguration.setConfigProperties(extProperties);//额外的配置，可以覆盖默认配置，一般不需要
//        ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader,
//                templatesPath);
//        beetlGroupUtilConfiguration.setResourceLoader(cploder);
//        beetlGroupUtilConfiguration.init();
//        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
//        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
//        return beetlGroupUtilConfiguration;
//    }

//    @Bean(name = "beetlViewResolver")
//    public BeetlSpringViewResolver getBeetlSpringViewResolver(
//            @Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
//        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
//        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
////        beetlSpringViewResolver.setPrefix("/templates/");
////        beetlSpringViewResolver.setSuffix(".html");
//        // WARN  org.beetl.ext.spring.BeetlSpringViewResolver- Beetl不建议使用使用spring前缀，
//        // 会导致include,layout找不到对应的模板，请使用beetl的配置RESOURCE.ROOT来配置模板根目录
//        beetlSpringViewResolver.setPrefix(templatesPath);
//        beetlSpringViewResolver.setSuffix(suffix);
//        beetlSpringViewResolver.setOrder(0);
//        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
//        return beetlSpringViewResolver;
//    }


}
