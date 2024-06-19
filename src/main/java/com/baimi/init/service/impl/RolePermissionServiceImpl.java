package com.baimi.init.service.impl;

import com.baimi.init.entity.Permission;
import com.baimi.init.entity.RolePermission;
import com.baimi.init.mapper.RolePermissionMapper;
import com.baimi.init.service.IPermissionService;
import com.baimi.init.service.IRolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * className RolePermissionServiceImpl
 * @author zhang
 * @since  2024/6/18 下午3:44
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Resource
    private IPermissionService permissionService;
    @Override
    public List<Permission> getPermissionListByRoleIdList(List<Integer> roleIdList) {
        //使用Set集合去重
        Set<RolePermission> rolePermissions = new HashSet<>();
        for (Integer roleId : roleIdList) {
            LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(RolePermission::getRoleId, roleId);
            List<RolePermission> rolePermissionList = this.list(wrapper);
            rolePermissions.addAll(rolePermissionList);
        }
        return rolePermissions.stream()
                .map(rolePermission -> permissionService.getById(rolePermission.getPermissionId()))
                .collect(Collectors.toList());
    }
}
