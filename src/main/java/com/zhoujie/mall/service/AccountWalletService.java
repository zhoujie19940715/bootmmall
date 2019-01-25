package com.zhoujie.mall.service;

import com.zhoujie.mall.pojo.AccountWallet;

public interface AccountWalletService {
    AccountWallet selectByOpenId(String openId);

    int updateAccountWallet(AccountWallet record);

}
