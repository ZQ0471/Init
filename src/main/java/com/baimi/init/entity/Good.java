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
@ApiModel(value = "Good对象", description = "")
public class Good implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("商品名")
    private String goodName;

    @ApiModelProperty("商品描述")
    private String detail;

    @ApiModelProperty("价格")
    private Integer price;

    @ApiModelProperty("库存")
    private Integer count;

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

    @ApiModelProperty("shop.id")
    private Integer shopId;

    @ApiModelProperty("状态 0未上架，1上架，2已下架")
    private Byte status;

    @ApiModelProperty("0未删除，1已删除")
    private Byte isDeleted;

    @ApiModelProperty("图片")
    private String pic;

}
