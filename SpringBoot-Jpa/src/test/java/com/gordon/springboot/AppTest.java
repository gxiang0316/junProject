package com.gordon.springboot;

import static org.junit.Assert.assertTrue;

import com.gordon.springboot.demo1.User;
import com.gordon.springboot.demo1.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest
{

    @Autowired
    private UserRepository userRepository;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

//    @Test
//    public void testJpa() {
//        userRepository.save(new User("aaa","北京",19));
//        userRepository.save(new User("bbb","上海",25));
//        userRepository.save(new User("ccc","广州",26));
//        userRepository.save(new User("ddd","深圳",52));
//        userRepository.save(new User("eee","杭州",18));
//
//
//        // 测试findAll
//        Assert.assertEquals(5,userRepository.findAll().size());
//
//    }


}
