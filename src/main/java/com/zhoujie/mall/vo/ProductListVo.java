package com.zhoujie.mall.vo;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Created by geely
 */
@Data
public class ProductListVo {

    private Integer id;
    private Integer categoryId;

    private String name;
    private String subtitle;
    private String mainImage;
    private BigDecimal price;

    private Integer status;

    private String imageHost;

}
