package com.baimi.init.common;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.dto.UserInfo;
import com.baimi.init.entity.Permission;
import com.baimi.init.entity.Role;
import com.baimi.init.entity.User;
import com.baimi.init.service.IRolePermissionService;
import com.baimi.init.service.IUserRoleService;
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
    /**
     * 更新用户权限状态
     * 最好每次操作权限或角色都删除一下session
     */
    public void updateUserStatement(User user){
        //更新用户角色信息
        UserInfo userInfo = new UserInfo(user);
        //使用userId获取role列表
        List<Role> roleList =  userRoleService.getRoleListByUserId(user.getId());
        //转为角色名称
        List<String> roles =  roleList.stream().map(Role::getName).collect(Collectors.toList());
        //更新Session信息
        userInfo.setRoles(roles);
        //使用roleId列表获取permission列表
        List<Integer> roleIdList = roleList.stream().map(Role::getId).collect(Collectors.toList());
        List<Permission> permissionList = rolePermissionService.getPermissionListByRoleIdList(roleIdList);
        //转为权限名称
        List<String> permissions =  permissionList.stream().map(Permission::getName).collect(Collectors.toList());
        userInfo.setPermissions(permissions);
        StpUtil.getSession().set("userInfo", userInfo);
    }
    public UserInfo getUserInfo(){
        return (UserInfo) StpUtil.getSession().get("userInfo");
    }
    public Integer getUserId(){
        return getUserInfo().getId();
    }
    public List<String> getRoleList(){
        return getUserInfo().getRoles();
    }
    public List<String> getPermissionList(){
        return getUserInfo().getPermissions();
    }
}