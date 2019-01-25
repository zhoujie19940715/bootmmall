package com.zhoujie.mall.dao;


import com.zhoujie.mall.pojo.AccountWallet;

public interface AccountWalletMapper {
    AccountWallet selectByOpenId(String openId);

    int updateAccountWallet(AccountWallet record);

}
