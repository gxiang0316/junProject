package com.gordon.springboot.demo1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    // JpaRepository<User,Long> : 第一个参数是实际储存的类型，第二个参数是主键

    User findByName(String name);

    User findByNameAndAge(String name,Integer age);

    /*
        这里Param的值和=:后面的参数匹配，但不需要和方法名对应的参数值对应
        ？加数字表示占位符，？1代表在方法参数里的第一个参数，区别于其他的index，这里从1开始
        =:加上变量名，这里是与方法参数中有@Param的值匹配的，而不是与实际参数匹配的
        JPQL的语法中，表名的位置对应Entity的名称，字段对应Entity的属性,详细语法见相关文档
        要使用原生SQL需要在@Query注解中设置nativeQuery=true，然后value变更为原生SQL即可
     */
    @Query("select u from User u where u.name =:name")// 不能少了 : 号，同时 : 号后面不允许有空格
    User findyyy(@Param("name") String name2);

    /*
        select u from User u where u.name =:name and age =:age 原生sql写法不能使用u作为结果
     */
    @Query(nativeQuery=true,value="select u.* from User u where u.name =:name and age =:age")
    User findxxx(@Param("name") String name1,@Param("age") int age2);

    // 当使用 @Query注解后方法名不受JPA规范约束，可以任意起名

    /**
    JpaRepository接口又继承PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>
     其中就已经有很多常用的增删改查方法，JpaRepository中定义的方法如下：
         List<T> findAll();
         List<T> findAll(Sort var1);
         List<T> findAllById(Iterable<ID> var1);
         <S extends T> List<S> saveAll(Iterable<S> var1);
         void flush();
         <S extends T> S saveAndFlush(S var1);
         void deleteInBatch(Iterable<T> var1);
         void deleteAllInBatch();
         T getOne(ID var1);
         <S extends T> List<S> findAll(Example<S> var1);
         <S extends T> List<S> findAll(Example<S> var1, Sort var2);

     PagingAndSortingRepository中定义的方法：
         Iterable<T> findAll(Sort var1);
         Page<T> findAll(Pageable var1);

     PagingAndSortingRepository又继承CrudRepository<T, ID>。CrudRepository中的方法：
         <S extends T> S save(S var1);
         <S extends T> Iterable<S> saveAll(Iterable<S> var1);
         Optional<T> findById(ID var1);
         boolean existsById(ID var1);
         Iterable<T> findAll();
         Iterable<T> findAllById(Iterable<ID> var1);
         long count();
         void deleteById(ID var1);
         void delete(T var1);
         void deleteAll(Iterable<? extends T> var1);
         void deleteAll();


     QueryByExampleExecutor<T> 中定义的方法如下：
         <S extends T> Optional<S> findOne(Example<S> var1);
         <S extends T> Iterable<S> findAll(Example<S> var1);
         <S extends T> Iterable<S> findAll(Example<S> var1, Sort var2);
         <S extends T> Page<S> findAll(Example<S> var1, Pageable var2);
         <S extends T> long count(Example<S> var1);
         <S extends T> boolean exists(Example<S> var1);


     那就是说UserRepository继承JpaRepository就拥有以上所有方法，足可应对常见的增删改查业务


     */



}
