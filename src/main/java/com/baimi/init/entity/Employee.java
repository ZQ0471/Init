package com.baimi.init.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author zhang
 * @since 2024-05-31
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 所属店铺
     */
    private Integer shopId;

    /**
     * 用户id---wd_user.id
     */
    private Integer userId;

    /**
     * 授权状态 1 已授权 2 已禁用
     */
    private Byte status;

    /**
     * 是否删除:0.未删除，1.已删除’,—默认为0
     */

}
