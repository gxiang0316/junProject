package com.gordon.springboot.config;

import com.gordon.springboot.shiro.RetryLimitHashedCredentialsMatcher;
import com.gordon.springboot.shiro.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 权限框架配置
 *  https://www.cnblogs.com/chenliangcl/p/7346187.html
 *  https://www.cnblogs.com/asker009/p/9471712.html
 */
@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Value("${shiro.hashAlgorithmName}")
    private String hashAlgorithmName;
    @Value("${shiro.hashIterations}")
    private int hashIterations;
    @Value("${shiro.sessionTimeout}")
    private long sessionTimeout;
    @Value("${shiro.sessionValidationInterval}")
    private long sessionValidationInterval;
    @Value("${shiro.ehcache.xml}")
    private String ehcachePath;
    @Value("${shiro.session.id}")
    private String shiroSessionId;
    @Value("${shiro.cookieName}")
    private String cookieName;
    @Value("${shiro.cookieMaxAge}")
    private int cookieMaxAge;


    @Bean(name = "ehcacheManager")
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile(ehcachePath);
        return em;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     * @return
     */
//    @Bean(name = "RetryLimitCredentialsMatcher")
//    public HashedCredentialsMatcher hashedCredentialsMatcher(
//            @Qualifier("ehcacheManager") CacheManager cacheManager){
//        RetryLimitHashedCredentialsMatcher hashedCredentialsMatcher
//                = new RetryLimitHashedCredentialsMatcher(cacheManager);
//        //散列算法:这里使用SHA-256算法;
//        hashedCredentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
//        //散列的次数，比如散列两次，md5算法的话相当于 md5(md5(""));
//        hashedCredentialsMatcher.setHashIterations(hashIterations);
//        //true表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
//        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
//        return hashedCredentialsMatcher;
//    }

    @Bean(name = "userRealm")
    public UserRealm userRealm(
            @Qualifier("ehcacheManager")CacheManager cacheManager) {
        UserRealm realm = new UserRealm();
        //realm.setCacheManager(cacheManager);
        //realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    /**单机环境*/
    @Bean(name = "shiroSessionManager")
    public DefaultWebSessionManager sessionManager(
            @Qualifier("ehcacheManager") CacheManager cacheManager,
            @Qualifier("simpleCookie") SimpleCookie cookie){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(sessionTimeout);
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setSessionValidationInterval(sessionValidationInterval);
        sessionManager.setGlobalSessionTimeout(sessionTimeout);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    @Bean(name = "webSecurityManager")
    public DefaultWebSecurityManager securityManager(
            @Qualifier("ehcacheManager") CacheManager cacheManager,
            @Qualifier("userRealm") UserRealm userRealm,
            @Qualifier("cookieRememberMeManager") CookieRememberMeManager rememberMeManager,
            @Qualifier("shiroSessionManager") SessionManager sessionManager){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager(@Qualifier("simpleCookie") SimpleCookie cookie){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        // Shiro 记住密码采用的是AES加密，AES key length 需要是16位，该方法生成16位的key，参考ShiroCipKeyTest
        cookieRememberMeManager.setCipherKey(Base64.decode("R29yZG9uV2ViAAAAAAAAAA=="));
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }

    @Bean(name = "simpleCookie")
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie(cookieName);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieMaxAge);
        return cookie;
    }


    /**
     * 注册DelegatingFilterProxy（Shiro）
     * 集成Shiro有2种方法：
     * 1. 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
     * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
     * 2. 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
     * 默认拦截 /*）
     *
     * @return
     */
//  @Bean
//  public FilterRegistrationBean filterRegistrationBean() {
//      FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//      filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//      //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//      filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//      filterRegistration.setEnabled(true);
//      filterRegistration.addUrlPatterns("/*");// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
//      return filterRegistration;
//  }

    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/html/**", "anon");
//        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
//        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        filterChainDefinitionMap.put("/**", "authc");
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//
//        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 设置spring代理，或者使用下面那个方法
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * 在方法中 注入 securityManager,进行代理控制
     */
//    @Bean
//    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
//        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
//        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//        bean.setArguments(securityManager);
//        return bean;
//    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("webSecurityManager") DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 解决创建此类时导致@Value注解无法赋值： https://blog.csdn.net/wuxuyang_7788/article/details/70141812
     * Shiro生命周期处理器
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 解决@Configuration注解的类中无法使用 @Value属性赋值
     * @return
     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}
