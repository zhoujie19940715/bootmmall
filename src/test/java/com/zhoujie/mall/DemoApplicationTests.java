package com.zhoujie.mall;

import com.zhoujie.mall.dao.AccountWalletMapper;
import com.zhoujie.mall.dao.UserMapper;
import com.zhoujie.mall.pojo.AccountWallet;
import com.zhoujie.mall.util.SnowflakeIdFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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
        for (int i = 0;i< 1000000;i ++) {
            AccountWallet wallet = new AccountWallet();
            wallet.setUserAmount(new BigDecimal(333 + i * 11));
            wallet.setIsOpen(1);
            wallet.setPayPassword(Long.toString(System.currentTimeMillis()).substring(3, 9));
            wallet.setVersion(0);
            SnowflakeIdFactory idWorker = new SnowflakeIdFactory(1, 2);
            wallet.setUserOpenId(Long.toString(idWorker.nextId()));
            wallet.setCheckKey(Long.toString(idWorker.nextId()));
            accountWalletMapper.insert(wallet);
        }
    }


}

