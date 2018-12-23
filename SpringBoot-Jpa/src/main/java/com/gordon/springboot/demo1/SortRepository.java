package com.gordon.springboot.demo1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 测试sort排序
 */
public interface SortRepository extends JpaRepository<User,Long> {

    /*
        第一个参数定义排序规则，第二个参数是按哪个字段来排序(可以是多个，该参数是一个可变参数，
            如：new String[]{"id","age"}，这个方式是多个字段都是以同一种方式排序)
        Sort sort = new Sort(Sort.Direction.DESC,"age");

        还有一种构造方式可以多个字段不同方式排序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"age"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"name"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"address"));
        sortRepository.findxxxOrder(60,Sort.by(orders));

     */
    @Query("select u from User u where age < ?1")
    List<User> findxxxOrder(int age, Sort sort);


    // Modifying更新注解,@Modifying一定要配合@Transactional，否则会报错
    /*
        如果返回值不是void 或者 int/Integer类型 会报错，如下：
        org.springframework.dao.InvalidDataAccessApiUsageException:
            Modifying queries can only use void or int/Integer as return type!
          返回int:表示更新的记录数
     */
    @Modifying
    @Transactional
    @Query("update User u set u.age = ?1 where name = ?2")
    int updateUserByName(int age,String name);


    // 分页
    @Query("select u from User u where age < ?1")
    List<User> findUserByPage(int age, Pageable page);

    @Query("select u from User u where age < ?1")
    Page<User> findUserByPage2(int age, Pageable page);
}
