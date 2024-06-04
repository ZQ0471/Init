package com.baimi.init.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "店铺查询条件实体")
public class ShopQuery extends PageQuery {
    private String shopName;
    private Byte status;
    private Integer cate;
}
