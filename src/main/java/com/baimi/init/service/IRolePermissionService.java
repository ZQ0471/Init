package com.baimi.init.service;

import com.baimi.init.entity.Permission;
import com.baimi.init.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IRolePermissionService extends IService<RolePermission> {
    List<Permission> getPermissionListByRoleIdList(List<Integer> roleIdList);
}
