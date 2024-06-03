package com.baimi.init.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
@Data
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("账户限定长度8位")
    private String account;

    @ApiModelProperty("密码建议10位，最少6位")
    private String password;

    @ApiModelProperty("权限")
    private String roles;

    @ApiModelProperty("头像 暂定链接，七牛云上传")
    private String avatar;

    @ApiModelProperty("电话号码")
    private String phone;

}
