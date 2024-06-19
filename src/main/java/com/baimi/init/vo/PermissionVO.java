package com.baimi.init.vo;

import com.baimi.init.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName PermisssionVO
 * Author zhang
 * DATE 2024/6/19 上午11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionVO {
    private String name;
    private String remark;
    public PermissionVO(Permission permission) {
        this.name = permission.getName();
        this.remark = permission.getRemark();
    }
}