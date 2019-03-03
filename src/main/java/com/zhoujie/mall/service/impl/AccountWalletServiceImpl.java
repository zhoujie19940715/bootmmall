package com.zhoujie.mall.service.impl;

import com.zhoujie.mall.dao.AccountWalletMapper;
import com.zhoujie.mall.pojo.AccountWallet;
import com.zhoujie.mall.service.AccountWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountWalletServiceImpl implements AccountWalletService {
    @Autowired
    AccountWalletMapper accountWalletMapper;

    @Override
    public AccountWallet selectByOpenId(String openId) {
        return accountWalletMapper.selectByOpenId(openId);
    }

    @Override
    public List<AccountWallet> accountWalletList() {

        return accountWalletMapper.selectAll();
    }

    @Override
    public int updateAccountWallet(AccountWallet record) {
        return accountWalletMapper.updateByOpenId(record);
    }

    @Override
    public int insert(AccountWallet record) {
       return accountWalletMapper.insert(record);
    }

    @Override
    public int delete(String openId) {
        return accountWalletMapper.deleteByOpenId(openId);
    }
}
