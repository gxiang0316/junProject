package com.gordon.springboot;

import com.gordon.springboot.demo1.SortRepository;
import com.gordon.springboot.demo1.User;
import com.gordon.springboot.demo1.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1Test {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SortRepository sortRepository;

    @Test
    public void testJpa() {
        User save = userRepository.save(new User(1,"aaa","北京",19));
        userRepository.save(new User(2,"bbb","上海",25));
        userRepository.save(new User(3,"ccc","广州",26));
        userRepository.save(new User(4,"ddd","深圳",52));
        userRepository.save(new User(5,"eee","杭州",18));

/**
        // 测试findAll
        Assert.assertEquals(5,userRepository.findAll().size());
        System.out.println("插入5条数据，是否全部找出：" + (userRepository.findAll().size() == 5));

        // 测试自定义的查询方法findByName
        User aaa = userRepository.findByName("aaa");
        System.out.println("findByName aaa : "+aaa.toString());

        // 测试自定义的查询方法findByNameAndAge
        User bbb = userRepository.findByNameAndAge("bbb", 25);
        System.out.println("findByNameAndAge bbb : " + bbb.toString());

        // 测试findOne
        User user = new User(5,"eee","杭州",18);
        Example userExample = Example.of(user);
        Optional one = userRepository.findOne(userExample);
        // 版本在2.0以后 findOne(id) 用 findById(id).orElse(null) 替换。
        System.out.println("findOne example : " + one.get().toString());
*/
        // 测试findUser
        User ddd = userRepository.findyyy("ddd");
        System.out.println("findUser ddd : " + ddd.toString());


        // 测试原生sql查询
        User eee = userRepository.findxxx("eee", 18);
        System.out.println("findUserByNameAndAge eee : " + eee.toString());

        // 测试 sql中的 ?1 方式
        User eee1 = userRepository.findqqq("eee");
        System.out.println("?1方式测试： " + eee1.toString());


        // ======================================================
        // 测试排序

        // 第一个参数定义排序规则，第二个参数是按哪个字段来排序(可以是多个，该参数是一个可变参数，如：new String[]{"id","age"})
        Sort sort = new Sort(Sort.Direction.DESC,"age");
        List<User> users = sortRepository.findxxxOrder(60, sort);
        for (int i = 0; i < users.size(); i++) {
            System.out.println("第"+i+"个： " + users.get(i).getName());
        }

        // 按age降序 name升序 address降序
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"age"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"name"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"address"));
        sortRepository.findxxxOrder(60,Sort.by(orders));


        // update 更新
        int ccc = sortRepository.updateUserByName(23, "ccc");
        System.out.println("ccc update : " + ccc);


        // pageable 分页
        Pageable page = new PageRequest(0,2,Sort.Direction.DESC,"age");
        List<User> userByPage = sortRepository.findUserByPage(69, page);
        for (int i = 0; i < userByPage.size(); i++) {
            System.out.println("分页查询："+ userByPage.get(i).getName());
        }

        Page<User> userByPage2 = sortRepository.findUserByPage2(69, page);
        System.out.println("总记录数 : " + userByPage2.getTotalElements());
        System.out.println("总页数 ： " + userByPage2.getTotalPages());
        System.out.println(" getNumber() :" + userByPage2.getNumber());
        System.out.println(" getSize() :" + userByPage2.getSize());
        System.out.println(" getNumberOfElements() :" + userByPage2.getNumberOfElements());

        List<User> content = userByPage2.getContent();

        Pageable page1 = new PageRequest(1,2,Sort.Direction.DESC,"age");
        Page<User> userByPage3 = sortRepository.findUserByPage2(69, page1);
        System.out.println("总记录数 : " + userByPage3.getTotalElements());
        System.out.println("总页数 ： " + userByPage3.getTotalPages());
        System.out.println(" getNumber() :" + userByPage3.getNumber());
        System.out.println(" getSize() :" + userByPage3.getSize());
        System.out.println(" getNumberOfElements() :" + userByPage3.getNumberOfElements());

        Pageable page2 = new PageRequest(2,2,Sort.Direction.DESC,"age");
        Page<User> userByPage43 = sortRepository.findUserByPage2(69, page2);
        System.out.println("总记录数 : " + userByPage43.getTotalElements());
        System.out.println("总页数 ： " + userByPage43.getTotalPages());
        System.out.println(" getNumber() :" + userByPage43.getNumber());
        System.out.println(" getSize() :" + userByPage43.getSize());
        System.out.println(" getNumberOfElements() :" + userByPage43.getNumberOfElements());

        Pageable page3 = new PageRequest(3,2,Sort.Direction.DESC,"age");
        Page<User> userByPage53 = sortRepository.findUserByPage2(69, page3);
        System.out.println("总记录数 : " + userByPage53.getTotalElements());
        System.out.println("总页数 ： " + userByPage53.getTotalPages());
        System.out.println(" getNumber() :" + userByPage53.getNumber());
        System.out.println(" getSize() :" + userByPage53.getSize());
        System.out.println(" getNumberOfElements() :" + userByPage53.getNumberOfElements());
        /* 以上分页执行结果： 结论，分页的页码是从 0 开始
            总记录数 : 5
            总页数 ： 3
             getNumber() :0
             getSize() :2
             getNumberOfElements() :2  // 当前页的查询结果数
            Hibernate: select user0_.id as id1_0_, user0_.address as address2_0_, user0_.age as age3_0_, user0_.name as name4_0_ from user user0_ where user0_.age<? order by user0_.age desc limit ?, ?
            Hibernate: select count(user0_.id) as col_0_0_ from user user0_ where user0_.age<?
            总记录数 : 5
            总页数 ： 3
             getNumber() :1
             getSize() :2
             getNumberOfElements() :2
            Hibernate: select user0_.id as id1_0_, user0_.address as address2_0_, user0_.age as age3_0_, user0_.name as name4_0_ from user user0_ where user0_.age<? order by user0_.age desc limit ?, ?
            总记录数 : 5
            总页数 ： 3
             getNumber() :2
             getSize() :2
             getNumberOfElements() :1
            Hibernate: select user0_.id as id1_0_, user0_.address as address2_0_, user0_.age as age3_0_, user0_.name as name4_0_ from user user0_ where user0_.age<? order by user0_.age desc limit ?, ?
            Hibernate: select count(user0_.id) as col_0_0_ from user user0_ where user0_.age<?
            总记录数 : 5
            总页数 ： 3
             getNumber() :3
             getSize() :2
             getNumberOfElements() :0
         */

    }

}
