package com.zhoujie.mall.controller.portal;

import com.zhoujie.mall.common.ServerResponse;
import com.zhoujie.mall.pojo.AccountWallet;
import com.zhoujie.mall.service.AccountWalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wallet/")
public class WalletController {
    @Autowired
    private AccountWalletService accountWalletService;

    @PostMapping(value = "/add")
    public ServerResponse add(AccountWallet accountWallet) {

        int count = accountWalletService.insert(accountWallet);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("添加成功！");
        } else {
            return ServerResponse.createByErrorMessage("添加失败！");
        }
    }

    @PostMapping(value = "/delete")
    public ServerResponse delete(String opendId) {
        int count = accountWalletService.delete(opendId);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功！");
        } else {
            return ServerResponse.createByErrorMessage("删除失败！");
        }
    }

    @PostMapping(value = "/update")
    public ServerResponse update(AccountWallet accountWallet) {
        int count = accountWalletService.updateAccountWallet(accountWallet);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("更新成功！");
        } else {
            return ServerResponse.createByErrorMessage("更新失败！");
        }
    }

    @GetMapping(value = "/selectone")
    public ServerResponse<AccountWallet> selectOne(String opendId) {
        AccountWallet accountWallet = accountWalletService.selectByOpenId(opendId);
        return ServerResponse.createBySuccess(accountWallet);
    }

    @GetMapping(value = "/selectall")
    public ServerResponse<List<AccountWallet>> selectAll(AccountWallet accountWallet) {

        List<AccountWallet> accountWalletList = accountWalletService.accountWalletList();

        return ServerResponse.createBySuccess(accountWalletList);
    }
    @RequestMapping(value = "/hello")
    public Object hello(){
        String sentence = "hello docker!";
        System.out.println(sentence);
        return sentence;
    }

}
