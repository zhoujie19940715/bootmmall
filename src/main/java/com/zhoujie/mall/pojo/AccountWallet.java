package com.zhoujie.mall.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountWallet {
    private Integer id;
    private BigDecimal userAmount;
    private String userOpenId;
    private Date createTime;
    private Date updateTime;
    private String payPassword;
    private Integer isOpen;
    private String checkKey;
    private Integer version;



}
