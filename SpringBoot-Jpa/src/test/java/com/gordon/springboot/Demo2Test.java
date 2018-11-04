package com.gordon.springboot;

import com.gordon.springboot.demo1.User;
import com.gordon.springboot.demo1.UserRepository;
import com.gordon.springboot.demo1.UserRepository2;
import com.gordon.springboot.demo1.UserSub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2Test {

    @Autowired
    private UserRepository2 userRepository2;

    @Test
    public void testJpa() {
        User save = userRepository2.save(new User(1,"aaa", "北京", 19));
        userRepository2.save(new User(2,"bbb","上海",25));
        userRepository2.save(new User(3,"ccc","广州",26));
        userRepository2.save(new User(4,"ddd","深圳",52));
        userRepository2.save(new User(5,"eee","杭州",18));

        List<UserSub> allUserSub = userRepository2.findAllUserSub();
        for (UserSub sub:allUserSub) {
            System.out.println(sub.getName() + " == " +
                    sub.getAddress() + " == " + sub.getNameAndAddress());
        }
        /*
            aaa == 北京 == aaa北京
            bbb == 上海 == bbb上海
            ccc == 广州 == ccc广州
            ddd == 深圳 == ddd深圳
            eee == 杭州 == eee杭州
         */

        // =========== 动态sql
        User user = new User();
        user.setName("aaa");
        user.setAge(19);
        List<User> sList = userRepository2.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                /* root源码：Root<T> root = query.from(T.class);表示sql中的主体,相当于 select * from table 中的 table
                            方法名from，意思是获取T 的Root，其实也就是个T 的包装对象
                           root创建出Expression

                   criteriaBuilder:表示构建sql的条件 xxx = xxx,yyy >= yyy,zzz in (x,y,z), ttt betwen x and y....
                           criteriaBuilder接收root创建的Expression，创建出Predicate

                   criteriaQuery:表示构建sql中的where,order by,having,group by...
                            criteriaQuery接收criteriaBuilder创建的Predicate，从而拼接完整的where子句

                */
                Predicate predicate = null;// 条件
                //if (user != null && !StringUtils.isEmpty(user.getName())){
                    predicate = criteriaBuilder.equal(root.get("name"), user.getName());
                //}
                Predicate predicate1 = null;// 条件
                //if (user != null && user.getAge() > 0){
                    predicate1 = criteriaBuilder.equal(root.get("age"), user.getAge());
                //}

                criteriaQuery.where(predicate,predicate1);//这里可以设置任意条查询条件
                //这种方式使用JPA的API设置了查询条件，所以不需要再返回查询条件Predicate给Spring Data Jpa，故最后return null
                return null;
            }
        });
        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i).getName() + " == " + sList.get(i).getAge());
        }

        // ===  很多条件时 最好使用list
        List<User> user1 = findUser(user);
        for (int i = 0; i < user1.size(); i++) {
            System.out.println(user1.get(i).getName()+"=================");
        }


    }


    private List<User> findUser(User user){

        return userRepository2.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> pList = new ArrayList<>();
                Predicate predicate = null;// 条件
                if (user != null && !StringUtils.isEmpty(user.getName())){
                    predicate = criteriaBuilder.equal(root.get("name"), user.getName());
                }
                Predicate predicate1 = null;// 条件
                if (user != null && user.getAge() > 0){
                    predicate1 = criteriaBuilder.equal(root.get("age"), user.getAge());
                }

                pList.add(predicate);
                pList.add(predicate1);
                pList.add(root.get("age").in(19));
                pList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"),18));// 大于等于

                Predicate[] pred = new Predicate[pList.size()];

                criteriaQuery.where(pList.toArray(pred));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("age"))
                        ,criteriaBuilder.desc(root.get("name")));

                return criteriaQuery.getRestriction();
            }
        });
    }




}
