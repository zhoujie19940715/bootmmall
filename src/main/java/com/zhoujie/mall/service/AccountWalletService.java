package com.zhoujie.mall.service;

import com.zhoujie.mall.pojo.AccountWallet;

import java.util.List;

public interface AccountWalletService {
    AccountWallet selectByOpenId(String openId);

    List<AccountWallet> accountWalletList();

    int updateAccountWallet(AccountWallet record);

    int insert(AccountWallet record);

    int delete(String openId);

}
