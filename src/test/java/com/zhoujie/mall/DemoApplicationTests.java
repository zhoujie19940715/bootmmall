package com.zhoujie.mall;

import com.zhoujie.mall.dao.AccountWalletMapper;
import com.zhoujie.mall.dao.UserMapper;
import com.zhoujie.mall.pojo.AccountWallet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AccountWalletMapper accountWalletMapper;

    @Test
    public void contextLoads() {
        System.out.println(userMapper.checkUsername("admin"));
    }
    @Test
    public void selectByOpenId() {
        System.out.println(accountWalletMapper.selectByOpenId("58899dcd-46b0-4b16-82df-bdfd0d953bfb"));
    }
    @Test
    public void selectList() {
       // List<AccountWallet> accountWallets = accountWalletMapper.selectList();
       // System.out.println(accountWallets);
    }
    @Test
    public void deleteByUserOpenId() {
       // System.out.println(accountWalletMapper.deleteByUserOpenId("333"));
    }

}

