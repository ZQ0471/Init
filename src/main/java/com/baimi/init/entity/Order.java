package com.baimi.init.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
@Data
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime modifyTime;

    @ApiModelProperty("店铺id---wd_shop.id")
    private Integer shopId;

    @ApiModelProperty("商品id---wd_goods.id")
    private Integer goodsId;

    @ApiModelProperty("购买数量")
    private Integer num;

    @ApiModelProperty("金额(单位：分)")
    private Integer amount;

    @ApiModelProperty("实付金额(单位：分)")
    private Integer realAmount;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("订单状态( 1:待支付  2:待揽收(已支付)  3:收发中心已揽收  4:收发中心已发出  5:干洗中心清洗中 6:干洗中心已发出 7:收发中心待配送 8:收发中心已送达 9:已完成 10:已取消 11:已关闭)")
    private Boolean status;

    @ApiModelProperty("退款状态 0未退款 1.申请中 2.已退款 3退款失败")
    private Boolean refundStatus;

    @ApiModelProperty("是否已删除(0:未删除  1:已删除)")
    private Byte isDeleted;

}
