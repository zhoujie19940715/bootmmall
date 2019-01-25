package com.zhoujie.mall.service.impl;

import com.zhoujie.mall.dao.AccountWalletMapper;
import com.zhoujie.mall.pojo.AccountWallet;
import com.zhoujie.mall.service.AccountWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountWalletServiceImpl implements AccountWalletService {
    @Autowired
    AccountWalletMapper accountWalletMapper;
    public AccountWallet selectByOpenId(String openId) {
        // TODO Auto-generated method stub
        return accountWalletMapper.selectByOpenId(openId);
    }

    public int updateAccountWallet(AccountWallet record) {
        // TODO Auto-generated method stub
        return accountWalletMapper.updateAccountWallet(record);
    }

}
