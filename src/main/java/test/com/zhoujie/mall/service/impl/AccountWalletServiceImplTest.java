package test.com.zhoujie.mall.service.impl;

import com.zhoujie.mall.dao.AccountWalletMapper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AccountWalletServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 26, 2019</pre>
 */

public class AccountWalletServiceImplTest {
    @Autowired
    AccountWalletMapper accountWalletMapper;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: selectByOpenId(String openId)
     */
    @Test
    public void testSelectByOpenId() throws Exception {
        accountWalletMapper.selectByOpenId("58899dcd-46b0-4b16-82df-bdfd0d953bfb");
    }

    /**
     * Method: updateAccountWallet(AccountWallet record)
     */
    @Test
    public void testUpdateAccountWallet() throws Exception {
//TODO: Test goes here... 
    }


} 
