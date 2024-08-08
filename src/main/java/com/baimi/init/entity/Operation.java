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
 * @author zhangqi
 * @since 2024-06-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime modifyTime;

    /**
     * 操作者
     */
    private String username;

    /**
     * 操作类型 1：信息管理 2：业务流程
     */
    private String type;

    /**
     * 描述说明
     */
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String remark;

    private String path;

    private String method;

    private String params;

    public Operation(String username, String type, String remark, String path, String method, String params) {
        this.username = username;
        this.type = type;
        this.remark = remark;
        this.path = path;
        this.method = method;
        this.params = params;
    }

}
