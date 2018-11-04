package com.gordon.springboot.demo1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // 提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@NoArgsConstructor // 无参构造器
@AllArgsConstructor // 全参构造器
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer age;

//    public User(){}

//    public User(Integer id,String name, String address, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.age = age;
//    }
}

