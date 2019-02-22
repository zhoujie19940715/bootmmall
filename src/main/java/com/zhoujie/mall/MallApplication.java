package com.zhoujie.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务管理
@MapperScan("com.zhoujie.mall.dao")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
public class MallApplication {
    /*
        springBoot 会自动扫描@SpringBootApplication同级包，以及下级包所有的bean
     */
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}

