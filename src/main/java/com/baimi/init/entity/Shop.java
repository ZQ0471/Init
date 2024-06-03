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
@ApiModel(value = "Shop对象", description = "")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺描述")
    private String descrip;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @ApiModelProperty("最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime modifyTime;

    @ApiModelProperty("客服电话")
    private String phone;

    @ApiModelProperty("营业状态(0:休息 1:营业)")
    private Byte status;

    @ApiModelProperty("是否已删除(0:未删除  1:已删除)")
    private Byte isDeleted;

    @ApiModelProperty("收发中心id---wd_receiving_and_sending_center.id")
    private Integer rscId;

    @ApiModelProperty("干洗中心id---wd_dry_cleaning_center.id")
    private Integer dccId;

}
