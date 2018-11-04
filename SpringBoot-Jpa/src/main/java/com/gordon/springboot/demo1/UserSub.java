package com.gordon.springboot.demo1;

import org.springframework.beans.factory.annotation.Value;

/**
 * 对于字段很多的表，有时只想返回一部分想要的字段
 */
public interface UserSub {
    //这里声明的方式是可以直接通过get+属性名，这是普通的字段
    String getName();

    String getAddress();

    // 使用@Value()可以组合多个字段合并成一个字段
    @Value("#{target.name + '' + target.address}")
    String getNameAndAddress();



}
