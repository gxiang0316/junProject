package com.gordon.springboot.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data // 提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@NoArgsConstructor // 无参构造器
@AllArgsConstructor // 全参构造器
@Entity
public class Book {

    // no identifier specified for entity
    // 没有为实体指定标识符，导致Error creating bean with name 'entityManagerFactory'
    // 因此@Entity是不够的，必须指定@Id的字段，其他的注解还不行
    @Id
    private int id;
    //@Column(nullable = true)
    private int userId;
    private String name;
    private String price;


}
