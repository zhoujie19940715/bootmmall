package com.zhoujie.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountWallet {
    private Integer id;
    private String userOpenId;
    private BigDecimal userAmount;
    private Date createTime;
    private Date updateTime;
    private String payPassword;
    private Integer isOpen;
    private String checkKey;
    private Integer version;
}
