package com.baimi.init.service.impl;

import com.baimi.init.entity.RolePermission;
import com.baimi.init.mapper.RolePermissionMapper;
import com.baimi.init.service.IRolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Override
    public List<String> getPermissionListByRoleId(Integer roleId) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> rolePermissions = this.list(wrapper);
        return rolePermissions.stream().map(RolePermission::getPermission).collect(Collectors.toList());
    }
}
