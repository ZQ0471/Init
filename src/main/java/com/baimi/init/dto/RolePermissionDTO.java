package com.baimi.init.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang
 * @since 2024/7/1 下午4:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionDTO {
    private int roleId;
    private int permissionId;
}
