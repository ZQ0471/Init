package com.baimi.init.service;

import com.baimi.init.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
public interface IRolePermissionService extends IService<RolePermission> {
    List<String> getPermissionsByRoleId(Integer roleId);

    void updateRole(Integer roleId, List<String> permissions);
}
