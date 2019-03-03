package com.zhoujie.mall.dao;

import com.zhoujie.mall.pojo.AccountWallet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountWalletMapper {

    int deleteByOpenId(String openId);

    int insert(AccountWallet record);


    AccountWallet selectByOpenId(String openId);

    List<AccountWallet> selectAll();

    int updateByPrimaryKeySelective(AccountWallet record);

    int updateByOpenId(AccountWallet record);

    int updateByPrimaryKey(AccountWallet record);
}