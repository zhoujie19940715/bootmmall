package com.zhoujie.mall;

import com.zhoujie.mall.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        userMapper.checkUsername("admin");
    }

}

