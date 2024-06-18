package com.baimi.init.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShopQuery extends PageQuery {
    private String shopName;
    private Byte status;
    private Integer cate;
}
