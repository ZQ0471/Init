package com.baimi.init.vo;

import com.baimi.init.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName RoleVo
 * Author zhang
 * DATE 2024/6/19 上午10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {
    private String name;
    private String remark;
    public RoleVO(Role role) {
        this.name = role.getName();
        this.remark = role.getRemark();
    }
}
