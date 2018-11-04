package com.gordon.springboot.demo1;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository2 extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {

    /**
     * 对于字段很多的表，有时只想返回一部分想要的字段。
     * jpa的处理方式：声明一个接口类，然后直接使用这个接口类接收返回的数据，接口中定义方法看UserSub类
     * 当然不使用接口类也可以重新定义一个类，用重新定义的类接收，跟之前的方式一样。
     * 但是如果使用跟表一致的类来接收会造成查询时返回全部，对于大表而言且高并发情况下是非常不利的
     */
    @Query("select u.name as name ,u.address as address from User u")
    List<UserSub> findAllUserSub();
    // 需注意一下：select u.name ,u.address from User u不能这样写，否则跟UserSub对不上，
    // 每个字段必须写as xxx ，这个xxx必须和UserSub中定义的字段对上

    /*
        JPA中save()方法解读：
            我们平时对save()方法的理解，大多是等同于insert()，主要是指新增一条数据，
            而JPA的save()方法包含了merge()的概念，就是说，
            如果save的对象不存在primary key或者primary key值在database内不存在的时候会新添加一条数据，
            如果primary key 存在并且primary key已经在database中存在，
            那就会依据primary key对该条数据进行更新，这是我们乐意见到的。
     */



    // =========== 动态sql
    //List<User> findByUser(User user,Specification<User> specification);
    // 不能使用自定义方法，不知道为什么，只能使用JpaSpecificationExecutor中定义好的方法
    // 一直报找不到property User


}
