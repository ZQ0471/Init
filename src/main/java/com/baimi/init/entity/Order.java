package com.baimi.init.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime modifyTime;

    private Integer shopId;

    private Integer goodsId;

    private Integer num;

    private Integer amount;

    private Integer realAmount;

    private Integer userId;

    private String remark;

    /*
    * 订单状态( 1:待支付  2:待揽收(已支付)  3:收发中心已揽收  4:收发中心已发出
    * 5:干洗中心清洗中 6:干洗中心已发出 7:收发中心待配送 8:收发中心已送达 9:已完成 10:已取消 11:已关闭)*/
    private Boolean status;

    /*
    * 退款状态 0未退款 1.申请中 2.已退款 3退款失败*/
    private Boolean refundStatus;

    /*
    * 是否已删除(0:未删除  1:已删除)*/
    private Byte isDeleted;

}
