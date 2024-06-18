package com.baimi.init.common;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.entity.Permission;
import com.baimi.init.entity.Role;
import com.baimi.init.service.IRolePermissionService;
import com.baimi.init.service.IUserRoleService;
import com.baimi.init.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName UserState
 * Author zhang
 * DATE 2024/6/18 下午5:03
 */
@Component
public class UserState {
    @Resource
    private IUserRoleService userRoleService;
    @Resource
    private IRolePermissionService rolePermissionService;
    @Resource
    private IUserService userService;
    /**
     * 更新用户权限状态
     * 最好每次操作权限或角色都更新一次
     */
    public void refreshUserStatement(){
        //更新用户角色信息
        Object loginId = StpUtil.getLoginId();
        String phone = (String) loginId;
        Integer userId = userService.getUserIdByPhone(phone);
        //使用userId获取role列表
        List<Role> roleList =  userRoleService.getRoleListByUserId(userId);
        //转为角色名称
        List<String> roles =  roleList.stream().map(Role::getName).collect(Collectors.toList());
        //更新Session信息
        StpUtil.getSession().set("roles", roles);
        //使用roleId列表获取permission列表
        List<Integer> roleIdList = roleList.stream().map(Role::getId).collect(Collectors.toList());
        List<Permission> permissionList = rolePermissionService.getPermissionListByRoleIdList(roleIdList);
        //转为权限名称
        List<String> permissions =  permissionList.stream().map(Permission::getName).collect(Collectors.toList());
        StpUtil.getSession().set("permissions", permissions);
    }
}
