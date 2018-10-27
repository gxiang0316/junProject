package com.gordon.springboot;

import com.gordon.springboot.demo1.SubUser;
import com.gordon.springboot.demo1.User;
import com.gordon.springboot.demo1.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1Test {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testJpa() {
        User save = userRepository.save(new User(1,"aaa", "北京", 19));
        userRepository.save(new User(2,"bbb","上海",25));
        userRepository.save(new User(3,"ccc","广州",26));
        userRepository.save(new User(4,"ddd","深圳",52));
        userRepository.save(new User(5,"eee","杭州",18));

        // 测试findAll
        Assert.assertEquals(5,userRepository.findAll().size());
        System.out.println("插入5条数据，是否全部找出：" + (userRepository.findAll().size() == 5));

        // 测试自定义的查询方法findByName
        User aaa = userRepository.findByName("aaa");
        System.out.println("aaa : "+aaa.toString());

        // 测试自定义的查询方法findByNameAndAge
        User bbb = userRepository.findByNameAndAge("bbb", 25);
        System.out.println("bbb : " + bbb.toString());

        // 测试findOne
        User user = new User();
        Example userExample = Example.of(user);
        userRepository.findOne(userExample);
        // findOne(id) 用 findById(id).orElse(null) 替换。





    }

}
