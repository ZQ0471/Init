package com.baimi.init.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShopQuery extends PageQuery {
    private String shopName;
    private Byte status;
    private Integer cate;
}
